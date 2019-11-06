package poly.service.impl;

import java.util.Iterator;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import poly.dto.ScheduleDTO;
import poly.persistance.mapper.IScheduleMapper;
import poly.service.IScheduleService;
import poly.util.CmmUtil;
import poly.util.DateUtil;

@Service("ScheduleService")
public class ScheduleService implements IScheduleService{

	private Logger log=Logger.getLogger(getClass());
	
	@Resource(name="ScheduleMapper")
	private IScheduleMapper scheduleMapper;
	
	@Override
	public int getScheduleInfo() throws Exception {
		
		log.info(this.getClass().getName()+".getScheduleInfo start!");

		int res=0;
		
		String url="https://www.goal.com/kr/%ED%94%84%EB%A6%AC%EB%AF%B8%EC%96%B4-%EB%A6%AC%EA%B7%B8/%EC%9D%BC%EC%A0%95-%EA%B2%B0%EA%B3%BC/2kwbbcootiqqgmrzs6o5inle5";
		Document doc=null;
		
		doc=Jsoup.connect(url).get();
		
		Elements element=doc.select("div.widget-competition-matches");
		
		String day=element.select("div.nav-switch:nth-child(1) div.nav-switch__label time").text();
		Iterator<Element> time=element.select("div.match-row div.match-status time").iterator();
		Iterator<Element> team_home=element.select("div.match-row div.team-home span.team-name").iterator();
		Iterator<Element> team_away=element.select("div.match-row div.team-away span.team-name").iterator();
		Iterator<Element> score_home=element.select("div.match-row div.team-home span.goals").iterator();
		Iterator<Element> score_away=element.select("div.match-row div.team-away span.goals").iterator();
		
		ScheduleDTO pDTO=null;
		
		pDTO=new ScheduleDTO();
		
		pDTO.setDay(CmmUtil.nvl(day).trim());
		
		while(team_home.hasNext()) {
			
			pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMdd24hmmss"));
			
			pDTO.setTime(CmmUtil.nvl(time.next().text()).trim());
			pDTO.setTeam_home(CmmUtil.nvl(team_home.next().text()).trim());
			pDTO.setTeam_away(CmmUtil.nvl(team_away.next().text()).trim());
			pDTO.setScore_home(CmmUtil.nvl(score_home.next().text()).trim());
			pDTO.setScore_away(CmmUtil.nvl(score_away.next().text()).trim());
			
			log.info("##################################################"+pDTO.getDay());
			log.info("##################################################"+pDTO.getTime());
			log.info("##################################################"+pDTO.getTeam_home());
			log.info("##################################################"+pDTO.getTeam_away());
			log.info("##################################################"+pDTO.getScore_home());
			log.info("##################################################"+pDTO.getScore_away());
			
			res+=scheduleMapper.InsertScheduleInfo(pDTO);
		}
		log.info(this.getClass().getName()+".getScheduleInfo end!");
		
		return res;
	}

}
