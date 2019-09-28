package poly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import poly.dto.MailDTO;
import poly.service.IMailService;
import poly.util.CmmUtil;

@Controller
public class MailController {

	private Logger log=Logger.getLogger(this.getClass());
	
	@Resource(name="MailService")
	private IMailService mailService;
	
	@RequestMapping(value="mail/sendMailForm")
	public String sendMailForm() {
		log.info(this.getClass().getName()+"mail.sendMailForm start!");
		
		return "/mail/sendMailForm";
	}
	
	@RequestMapping(value="mail/sendMail")
	public String sendMail(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception{
		log.info(this.getClass().getName()+"mail.sendMail start!");
		
		String toMail=request.getParameter("toMail");
		String title=request.getParameter("title");
		String contents=request.getParameter("contents");
		
		MailDTO pDTO=new MailDTO();
		
		pDTO.setToMail(toMail);
		pDTO.setTitle(title);
		pDTO.setContents(contents);
		
		int res=mailService.doSendMail(pDTO);
		
		if(res==1) {
			log.info(this.getClass().getName()+"mail.sendMail success!");
			
			model.addAttribute("msg", toMail+"로 메일 전송이 성공하였습니다.");
			model.addAttribute("url", "/mail/sendMailForm.do");
		} else {
			log.info(this.getClass().getName()+"mail.sendMail fail!");
			
			model.addAttribute("msg", toMail+"로 메일 전송이 실패하였습니다.");
			model.addAttribute("url", "/mail/sendMailForm.do");
		}
		
		

		log.info(this.getClass().getName()+"mail.sendMail end!");
		
		return "/redirect";
	}
}
