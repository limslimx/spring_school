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
public class ScheduleService implements IScheduleService {

	@Resource(name="ScheduleMapper")
	private IScheduleMapper scheduleMapper;
	
	private Logger log=Logger.getLogger(getClass());
	
	@Override
	public int getScheduleInfoFromWEB() throws Exception {
		
		log.info(this.getClass().getName()+".getScheduleInfoFromWEB start!");
		
		int res=0;
		
		String url="https://www.goal.com/kr/%ED%94%84%EB%A6%AC%EB%AF%B8%EC%96%B4-%EB%A6%AC%EA%B7%B8/%EC%9D%BC%EC%A0%95-%EA%B2%B0%EA%B3%BC/2kwbbcootiqqgmrzs6o5inle5";
		
		Document doc=null;
		
		doc=Jsoup.connect(url).get();
		
		Elements element=doc.select("div.main-content");
		
		Iterator<Element> match_time=element.select("time").iterator();
		Iterator<Element> team1=element.select("span.team-name").iterator();
		Iterator<Element> team2=element.select("span.team-name").iterator();
		Iterator<Element> score1=element.select("span.goals").iterator();
		Iterator<Element> score2=element.select("span.goals").iterator();
		
		ScheduleDTO pDTO=null;
		
		while(match_time.hasNext()) {
			pDTO=new ScheduleDTO();
			
			pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMdd24hmmss"));
			
			String time=CmmUtil.nvl(match_time.next().text()).trim();
			pDTO.setMatch_time(time.substring(3,time.length()));
			
			pDTO.setTeam1(CmmUtil.nvl(team1.next().text()).trim());
			
			pDTO.setTeam2(CmmUtil.nvl(team2.next().text()).trim());
			
			pDTO.setScore1(CmmUtil.nvl(score1.next().text().trim()));
			
			pDTO.setScore2(CmmUtil.nvl(score2.next().text().trim()));
			
			pDTO.setReg_id("admin");
			
			res+=scheduleMapper.FindSoccerSchedule(pDTO);
		}
		
		log.info(this.getClass().getName()+".getScheduleInfoFromWEB end!");
		return res;
	}
	
}
