package poly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.service.IScheduleService;

@Controller
public class ScheduleController {

	private Logger log=Logger.getLogger(getClass());
	
	@Resource(name="ScheduleService")
	private IScheduleService scheduleService;
	
	@RequestMapping(value="getScheduleInfoFromWEB")
	public String getScheduleInfoFromWEB(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception{
		
		log.info(this.getClass().getName()+".getScheduleInfoFromWEB start!");
		
		int res=scheduleService.getScheduleInfoFromWEB();
		
		model.addAttribute("res", String.valueOf(res));
		
		log.info(this.getClass().getName()+".getScheduleInfoFromWEB end!");
		
		return "/schedule/test";
	}
	
}
