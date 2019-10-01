//package poly.controller;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.apache.log4j.Logger;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import poly.dto.UserDTO;
//import poly.service.IUserService;
//import poly.util.CmmUtil;
//import poly.util.EncryptUtil;
//
//@Controller
//public class UserInfoController {
//
//	private Logger log = Logger.getLogger(getClass());
//	
//	@Resource(name="UserInfoService")
//	private IUserService userInfoService;
//	
//	@RequestMapping(value="user/userRegForm")
//	public String userRegForm() {
//		log.info(this.getClass().getName()+".user/userRegForm ok!");
//		
//		return "/user/userRegForm";
//	}
//	
//	@RequestMapping(value="user/insertUserInfo")
//	public String insertUserInfo(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
//		log.info(this.getClass().getName()+".insertUserInfo start!");
//		
//		String msg="";
//		
//		UserDTO pDTO=null;
//		
//		try {
//			String user_id=CmmUtil.nvl(request.getParameter("user_id"));
//			String user_name=CmmUtil.nvl(request.getParameter("user_name"));
//			String password=CmmUtil.nvl(request.getParameter("password"));
//			String email=CmmUtil.nvl(request.getParameter("email"));
//			String addr1=CmmUtil.nvl(request.getParameter("addr1"));
//			String addr2=CmmUtil.nvl(request.getParameter("addr2"));
//			
//			log.info("user_id: "+user_id);
//			log.info("user_name: "+user_name);
//			log.info("password: "+password);
//			log.info("email: "+email);
//			log.info("addr1: "+addr1);
//			log.info("addr2: "+addr2);
//			
//			pDTO=new UserDTO();
//			
//			pDTO.setUser_id(user_id);
//			pDTO.setUser_name(user_name);
//			//비밀번호는 절대로 복호화되지 않도록 해시 알고리즘으로 암호화함
//			pDTO.setPassword(EncryptUtil.encHashSHA256(password));
//			//이메일 정보는 복호화가 가능하도록 AES128-CBC로 암호화함
//			pDTO.setEmail(EncryptUtil.encAES128CBC(email));
//			pDTO.setAddr1(addr1);
//			pDTO.setAddr2(addr2);
//			
//			int res=userInfoService.insertUserInfo(pDTO);
//			
//			if(res==1) {
//				msg="회원가입되었습니다.";
//			} else if(res==2) {
//				msg="이미 가입된 이메일 주소입니다.";
//			} else {
//				msg="오류로 인해 회원가입이 실패했습니다.";
//			}
//			
//		} catch(Exception e) {
//			msg="�����Ͽ����ϴ�. "+e.toString();
//			log.info(e.toString());
//			e.printStackTrace();
//			
//		} finally {
//			log.info(this.getClass().getName()+".insertUserInfo end!");
//			
//			model.addAttribute("msg", msg);
//			model.addAttribute("pDTO", pDTO);
//			
//			pDTO=null;
//		}
//		
//		return "/user/Msg";
//	}
//	
//	@RequestMapping(value="user/loginForm")
//	public String loginForm() {
//		log.info(this.getClass().getName()+".user/loginForm ok!");
//		
//		return "/user/LoginForm";
//	}
//	
//	@RequestMapping(value="user/getUserLoginCheck")
//	public String getUserLoginCheck(HttpSession session, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception{
//		log.info(this.getClass().getName()+".getUserLoginCheck start!");
//		
//		int res=0;
//		
//		UserDTO pDTO=null;
//		
//		try {
//			String user_id=CmmUtil.nvl(request.getParameter("user_id"));
//			String password=CmmUtil.nvl(request.getParameter("password"));
//			
//			log.info("user_id: "+user_id);
//			log.info("password: "+password);
//			
//			pDTO=new UserDTO();
//			pDTO.setUser_id(user_id);
//			pDTO.setPassword(EncryptUtil.encHashSHA256(password));
//			
//			res=userInfoService.getUserLoginCheck(pDTO);
//			
//			if(res==1) {
//				session.setAttribute("SS_USER_ID", user_id);
//			}
//		} catch(Exception e) {
//			res=2;
//			log.info(e.toString());
//			e.printStackTrace();
//		} finally {
//			log.info(this.getClass().getName()+".insertUserInfo end!");
//			
//			model.addAttribute("res", String.valueOf(res));
//			
//			pDTO=null;
//		}
//		return "/user/LoginResult";
//	}
//	
//	@RequestMapping(value="user/findUserId")
//	public String findUserId(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception{
//		
//		log.info(this.getClass().getName()+".findUserId start!");
//		
//		
//		return "/user/findUserId";
//	}
//	
//	@RequestMapping(value="user/findUserIdProc")
//	public String findUserIdProc(HttpSession session, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception{
//		
//		log.info(this.getClass().getName()+".findUserIdProc start!");
//		
//		int res=0;
//		
//		UserDTO pDTO=null;
//		
//		try {
//			String user_name=request.getParameter("user_name");
//			
//			log.info("user_name: "+user_name);
//			
//			pDTO=new UserDTO();
//			pDTO.setUser_name(user_name);
//			
//			String user_id=userInfoService.findUserId(pDTO);
//			
//			log.info("user_id: "+user_id);
//			
//			session.setAttribute("user_id", user_id);
//			
//			
//			
//		} catch(Exception e) {
//			res=2;
//			log.info(e.toString());
//			e.printStackTrace();
//		} finally {
//			log.info(this.getClass().getName()+".findUserIdProc end!");
//			
//		}
//		if(res!=1) {
//			model.addAttribute("msg", "id찾기 성공");
//			model.addAttribute("url", "/user/findUserIdForm.do");
//		} else {
//			model.addAttribute("msg", "id찾기 실패");
//			model.addAttribute("url", "/user/findUserId.do");
//		}
//		
//		return "/redirect";
//	}
//	
//	@RequestMapping(value="user/findUserIdForm")
//	public String findUserIdForm() {
//		log.info(this.getClass().getName()+".findUserIdForm start!");
//
//		return "/user/findUserIdForm";
//	}
//}
