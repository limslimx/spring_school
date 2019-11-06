package poly.service.impl;

import java.util.Iterator;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import poly.dto.ResultDTO;
import poly.persistance.mapper.IResultMapper;
import poly.service.IResultService;
import poly.util.CmmUtil;
import poly.util.DateUtil;

@Service("ResultService")
public class ResultService implements IResultService {

	private Logger log=Logger.getLogger(getClass());
	
	@Resource(name="ResultMapper")
	private IResultMapper resultMapper;

	@Override
	public int getResultInfo() throws Exception {

		log.info(this.getClass().getName()+".getResultInfo start!");
		
		int res=0;
		
		String url="https://www.goal.com/kr/%ED%94%84%EB%A6%AC%EB%AF%B8%EC%96%B4-%EB%A6%AC%EA%B7%B8/%EC%88%9C%EC%9C%84/2kwbbcootiqqgmrzs6o5inle5";
		
		Document doc=null;
		
		doc=Jsoup.connect(url).get();
		
		Elements element=doc.select("table.p0c-competition-tables__table");
		
		Iterator<Element> rank=element.select("tbody tr td:nth-child(1)").iterator();
		Iterator<Element> team=element.select("tbody td.p0c-competition-tables__team span").iterator();
		Iterator<Element> total_match=element.select("tbody td.p0c-competition-tables__matches-played").iterator();
		Iterator<Element> won_match=element.select("tbody td.p0c-competition-tables__matches-won").iterator();
		Iterator<Element> draw_match=element.select("tbody td.p0c-competition-tables__matches-drawn").iterator();
		Iterator<Element> lost_match=element.select("tbody td.p0c-competition-tables__matches-lost").iterator();
		Iterator<Element> goal=element.select("tbody td.p0c-competition-tables__goals-for").iterator();
		Iterator<Element> lost=element.select("tbody td.p0c-competition-tables__goals-against").iterator();
		Iterator<Element> diff=element.select("tbody td.p0c-competition-tables__goals-diff").iterator();
		Iterator<Element> point=element.select("tbody td.p0c-competition-tables__pts").iterator();
		Iterator<Element> match=element.select("tbody td.p0c-competition-tables__last5").iterator();
		
		ResultDTO pDTO=null;
		
		while(rank.hasNext()) {
			
			pDTO=new ResultDTO();
			
			pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMdd24hmmss"));
			
			pDTO.setRank(CmmUtil.nvl(rank.next().text()).trim());
			pDTO.setTeam(CmmUtil.nvl(team.next().text()).trim());
			pDTO.setTotal_match(CmmUtil.nvl(total_match.next().text()).trim());
			pDTO.setWon_match(CmmUtil.nvl(won_match.next().text()).trim());
			pDTO.setDraw_match(CmmUtil.nvl(draw_match.next().text()).trim());
			pDTO.setLost_match(CmmUtil.nvl(lost_match.next().text()).trim());
			pDTO.setGoal(CmmUtil.nvl(goal.next().text()).trim());
			pDTO.setLost(CmmUtil.nvl(lost.next().text()).trim());
			pDTO.setDiff(CmmUtil.nvl(diff.next().text()).trim());
			pDTO.setPoint(CmmUtil.nvl(point.next().text()).trim());
			pDTO.setMatch(CmmUtil.nvl(match.next().text()).trim());
			pDTO.setReg_id("admin");
			
			log.info("##################################################"+pDTO.getRank());
			log.info("##################################################"+pDTO.getTeam());
			log.info("##################################################"+pDTO.getTotal_match());
			log.info("##################################################"+pDTO.getWon_match());
			log.info("##################################################"+pDTO.getDraw_match());
			log.info("##################################################"+pDTO.getLost_match());
			log.info("##################################################"+pDTO.getGoal());
			log.info("##################################################"+pDTO.getLost());
			log.info("##################################################"+pDTO.getDiff());
			log.info("##################################################"+pDTO.getPoint());
			log.info("##################################################"+pDTO.getMatch());
			
			res+=resultMapper.insertResultInfo(pDTO);
		}
		log.info(this.getClass().getName()+".getResultInfo end!");
		
		return res;
	}
	
	
}
