package poly.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NotiController {

	private Logger log=Logger.getLogger(this.getClass().getName());
	@RequestMapping(value="noti/notiList.do")
	public String notiList() {
		log.info(this.getClass().getName()+".notiList start!");
		
		return "/noti/notiList";
	}
}
