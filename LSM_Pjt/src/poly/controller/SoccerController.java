package poly.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.dto.ResultDTO;
import poly.service.IResultService;
import poly.service.IScheduleService;
import poly.service.ISoccerService;

@Controller
public class SoccerController {

	private Logger log=Logger.getLogger(getClass());
	
	@Resource(name="ScheduleService")
	private IScheduleService scheduleService;
	
	@Resource(name="ResultService")
	private IResultService resultService;
	
	@Resource(name="SoccerService")
	private ISoccerService soccerService;
	
	//축구 일정 관련 controller
	@RequestMapping(value="getScheduleInfo")
	public String GetScheduleInfo(ModelMap model) throws Exception{
		
		log.info(this.getClass().getName()+".GetScheduleInfo start!");
		
		int res=scheduleService.getScheduleInfo();
		
		model.addAttribute("res", String.valueOf(res));
		
		log.info(this.getClass().getName()+"GetScheduleInfo end!");
		
		return "/schedule/schedule";
	}
	
	//축구 결과 관련 controller
	@RequestMapping(value="getResultInfo")
	public String GetResultInfo(ModelMap model) throws Exception{
		
		log.info(this.getClass().getName()+".GetResultInfo start!");
		
		int res=resultService.getResultInfo();
		
		model.addAttribute("res", String.valueOf(res));
				
		log.info(this.getClass().getName()+".GetResultInfo end!");
		
		return "/crawlling/crawl";
	}
	
	@RequestMapping(value="/soccer/schedule")
	public String Schedule(ModelMap model) throws Exception{
		
		log.info(this.getClass().getName()+".Schedule start!");
		
		ResultDTO pDTO=new ResultDTO();
		
		int res=0;
		
		res=soccerService.updateResultInfo(pDTO);
		
		if(res>0) {
			model.addAttribute("rList", soccerService.getSoccerResult());
		} 
		log.info(this.getClass().getName()+".Schedule end!");
		
		return "/soccer/schedule";
	}
}
