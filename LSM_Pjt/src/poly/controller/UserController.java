package poly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.dto.UserDTO;
import poly.service.IUserService;
import poly.util.CmmUtil;
import poly.util.EncryptUtil;

@Controller
public class UserController {

	private Logger log=Logger.getLogger(getClass());
	
	@Resource(name="UserService")
	private IUserService userService;
	
	@RequestMapping(value="index")
	public String Index() {
		log.info(this.getClass());
		
		return "/index";
	}
	
	@RequestMapping(value="user/signUp")
	public String signUp() {
		log.info(this.getClass().getName()+".signUp start!");
		
		return "/user/signUp";
	}
	
	@RequestMapping(value="user/insertUserInfo")
	public String insertUserInfo(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception{
		
		log.info(this.getClass().getName()+".insertUserInfo start!");
		
		String msg="";
		String url="";
		
		UserDTO pDTO=null;
		
		try {
			String user_id=CmmUtil.nvl(request.getParameter("user_id"));
			String user_name=CmmUtil.nvl(request.getParameter("user_name"));
			String password=CmmUtil.nvl(request.getParameter("password"));
			String email=CmmUtil.nvl(request.getParameter("email"));
			
			log.info("user_id: "+user_id);
			log.info("user_name: "+user_name);
			log.info("password: "+password);
			log.info("email: "+email);
			
			pDTO=new UserDTO();
			pDTO.setUser_id(user_id);
			pDTO.setUser_name(user_name);
			pDTO.setPassword(EncryptUtil.encHashSHA256(password));
			pDTO.setEmail(EncryptUtil.encAES128CBC(email));
			
			int res=userService.insertUserInfo(pDTO);
			
			if(res==1) {
				msg="회원가입되었습니다.";
				url="/index.do";
			} else if(res==2) {
				msg="이미 가입된 이메일 주소입니다.";
				url="/user/signUp.do";
			} else {
				msg="오류로 인해 회원가입이 실패하였습니다.";
				url="/user/signUp.do";
			}
			
		} catch(Exception e) {
			msg="실패하였습니다. "+e.toString();
			log.info(e.toString());
			e.printStackTrace();
			
		} finally {
			log.info(this.getClass().getName()+".insertUserInfo end!");
			
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
			
			
			pDTO=null;
		}
		
		return "/redirect";
	}
	
	@RequestMapping(value="user/signIn")
	public String signIn() {
		log.info(this.getClass().getName()+".signIn start!");
		
		return "/user/signIn";
	}
	
	@RequestMapping(value="user/signInCheck")
	public String signInCheck(HttpSession session, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
		log.info(this.getClass().getName()+".signInCheck start!");
		
		int res=0;
		
		UserDTO pDTO=null;
		
		try {
			String user_id=CmmUtil.nvl(request.getParameter("user_id"));
			String password=CmmUtil.nvl(request.getParameter("password"));
			
			log.info("user_id: "+user_id);
			log.info("password: "+password);
			
			pDTO=new UserDTO();
			pDTO.setUser_id(user_id);
			pDTO.setPassword(EncryptUtil.encHashSHA256(password));
			
			res=userService.signInCheck(pDTO);
			
			if(res==1) {
				session.setAttribute("SS_USER_ID", user_id);
				model.addAttribute("msg","로그인 완료");
				model.addAttribute("url","/index.do");
			} else {
				model.addAttribute("msg","로그인 실패");
				model.addAttribute("url","/user/signIn.do");
			}
		} catch(Exception e) {
			res=2;
			log.info(e.toString());
			e.printStackTrace();
		} finally {
			log.info(this.getClass().getName()+".signInCheck end!");
			model.addAttribute("res", String.valueOf(res));
			pDTO=null;
			log.info("user_id: "+session.getAttribute("SS_USER_ID"));
		}
		
		return "/redirect";
		
	}
	
	
}
