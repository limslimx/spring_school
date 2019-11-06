package poly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.dto.MailDTO;
import poly.dto.UserDTO;
import poly.service.impl.ContactService;
import poly.service.impl.MailService;
import poly.util.EncryptUtil;

@Controller
public class ContactController {

	private Logger log=Logger.getLogger(getClass());
	
	@Resource(name="ContactService")
	private ContactService contactService;
	
	@Resource(name="MailService")
	private MailService mailService;
	
	@RequestMapping(value="contactForm")
	public String ContactForm() throws Exception{
		log.info(this.getClass().getName());
		
		return "/contact/contactForm";
	}
	
	@RequestMapping(value="contactFormProc")
	public String ContactFormProc(HttpSession session, HttpServletRequest request, ModelMap model) throws Exception{
		log.info(this.getClass().getName()+".contactFormProc start!");
		
		int res=0;
		
		UserDTO uDTO=new UserDTO();
		
		MailDTO mDTO=new MailDTO();
		
		String user_id=(String)session.getAttribute("user_id");
		String title=request.getParameter("title");
		String inquiry=request.getParameter("inquiry");
		
		try {
			uDTO.setUser_id(user_id);
			
			uDTO=contactService.getUserInfo(uDTO);
			
			mDTO.setTitle(title);
			mDTO.setContents(inquiry);
			mDTO.setToMail("tonylim1999@naver.com");
			
			res=mailService.doSendMail(mDTO);
			
			if(res>0) {
				model.addAttribute("url", "/index.do");
				model.addAttribute("msg", "문의 메일이 전송되었습니다.");
			} else {
				model.addAttribute("url", "/index.do");
				model.addAttribute("msg", "문의메일 전송에 실패하였습니다.");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			log.info(this.getClass().getName()+".contactFormProc end!");
			mDTO=null;
		}
		
		return "/redirect";
	}
}
