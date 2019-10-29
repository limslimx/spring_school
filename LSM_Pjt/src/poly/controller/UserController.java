package poly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public String Index(HttpSession session) {
		log.info(this.getClass());
		
		String user_id=(String)session.getAttribute("user_id");
		log.info("user_id: "+user_id);
		
		if(user_id==null) {
			return "/user/signIn";
		} else if(user_id.equals("admin")){
			return "/admin/homeAdmin";
		} else {
			return "/home";
		}
		
	}
	
	@RequestMapping(value="home")
	public String Home() {
		log.info(this.getClass());
		
		return "/home";
	}
	
	@ResponseBody
	@RequestMapping(value="/idCheck.do", method = RequestMethod.POST)
	public int idCheck(HttpServletRequest request) throws Exception {
		log.info("idCheck");

		String user_id = request.getParameter("id");
		log.info(user_id);
		UserDTO uDTO = userService.idCheck(user_id);

		int result = 0;

		if (uDTO != null) {
			result = 1;
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/emailCheck.do", method = RequestMethod.POST)
	public int emailCheck(HttpServletRequest request) throws Exception {
		log.info("emailCheck");

		String email = EncryptUtil.encAES128CBC(request.getParameter("email"));
		log.info(email);
		UserDTO uDTO = userService.emailCheck(email);

		int result = 0;

		if (uDTO != null) {
			result = 1;
		}
		return result;
	}
	
	@RequestMapping(value="signUp")
	public String signUp() {
		log.info(this.getClass().getName()+".signUp start!");
		
		return "/user/signUp";
	}
	
	@RequestMapping(value="userRegProc")
	public String insertUserInfo(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception{
		
		log.info(this.getClass().getName()+".insertUserInfo start!");
		
		String msg="";
		String url="";
		
		UserDTO pDTO=null;
		
		try {
			String user_id=CmmUtil.nvl(request.getParameter("id"));
			String user_name=CmmUtil.nvl(request.getParameter("user_name"));
			String password=CmmUtil.nvl(request.getParameter("pw"));
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
				url="/signUp.do";
			} else {
				msg="오류로 인해 회원가입이 실패하였습니다.";
				url="/signUp.do";
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
	
	@RequestMapping(value="signIn")
	public String signIn() {
		log.info(this.getClass().getName()+".signIn start!");
		
		return "/user/signIn";
	}
	
	@RequestMapping(value="user/signInCheck")
	public String signInCheck(HttpSession session, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
		log.info(this.getClass().getName()+".signInCheck start!");
		
		int res=0;
		
		UserDTO uDTO=null;
		
		try {
			String user_id=CmmUtil.nvl(request.getParameter("user_id"));
			String password=CmmUtil.nvl(request.getParameter("password"));
			
			log.info("user_id: "+user_id);
			log.info("password: "+password);
			
			uDTO=new UserDTO();
			uDTO.setUser_id(user_id);
			uDTO.setPassword(EncryptUtil.encHashSHA256(password));
			
			res=userService.signInCheck(uDTO);
			
			if(res==1) {
				session.setAttribute("user_id", user_id);
				model.addAttribute("msg","로그인 완료");
				model.addAttribute("url","/index.do");
			} else {
				model.addAttribute("msg","로그인 실패");
				model.addAttribute("url","/signIn.do");
			}
		} catch(Exception e) {
			res=2;
			log.info(e.toString());
			e.printStackTrace();
		} finally {
			log.info(this.getClass().getName()+".signInCheck end!");
			model.addAttribute("res", String.valueOf(res));
			uDTO=null;
			log.info("user_id: "+(String)session.getAttribute("user_id"));
		}
		
		return "/redirect";
		
	}
	
	@RequestMapping(value="logout")
	public String logout(HttpSession session, ModelMap model) throws Exception{
		
		session.invalidate();
		model.addAttribute("msg", "로그아웃했습니다.");
		model.addAttribute("url", "/index.do");
		return "/redirect";
	}
	
	@RequestMapping(value="findUserId")
	public String FindUserId() {
		log.info(this.getClass());
		
		return "/user/findUserId";
	}
	
	@RequestMapping(value="findUserIdProc")
	public String FindUserIdProc(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		log.info(this.getClass().getName()+".findUserIdProc start!");
		
		UserDTO pDTO = null;
		String id="";
		
		try {
			String email=CmmUtil.nvl(request.getParameter("email"));
			log.info(email);
			
			pDTO = new UserDTO();
			pDTO.setEmail(EncryptUtil.encAES128CBC(email));
			
			id=userService.findUserId(pDTO);
			log.info(id);
			
			if(id!=null) {
				model.addAttribute("msg","찾으시는 아이디는 "+id+"입니다.");
				model.addAttribute("url","/signIn.do");
			} else {
				model.addAttribute("msg","입력하신 이메일에 일치하는 id가 없습니다.");
				model.addAttribute("url","/signIn.do");
			}
			
		} catch(Exception e) {
			log.info(e.toString());
		} finally {
			log.info(this.getClass().getName()+".findUserIdProc end!");
			pDTO=null;
			log.info(id);
		}
		return "/redirect"; 
	}
	
	@RequestMapping(value="findUserPw")
	public String FindUserPw() {
		log.info(this.getClass());
		
		return "/user/findUserPw";
	}
	
	@RequestMapping(value="findUserPwProc")
	public String FindUserPwProc(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		log.info(this.getClass().getName()+".findUserPwProc start!");
		
		char[] charSet=new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		
		int idx=0;
		StringBuffer sb=new StringBuffer();
		
		for(int i=0;i<8;i++) {
			idx=(int)(charSet.length*Math.random());
			sb.append(charSet[idx]);
		}
		
		UserDTO pDTO = null;
		
		int res=0;
		
		String pw=sb.toString();
		try {
			
			//passwd는 랜덤함수를 이용한 임시비밀번호를 암호화하기 전의 비밀번호를 나타냄
			//password는 랜덤함수를 이용한 임시비밀번호를 암호화한 후의 비밀번호를 나타냄
			String user_id=CmmUtil.nvl(request.getParameter("id"));
			String passwd=CmmUtil.nvl(pw);
			String password=CmmUtil.nvl(EncryptUtil.encHashSHA256(pw));
			log.info(user_id);
			log.info(password);
			
			pDTO = new UserDTO();
			pDTO.setUser_id(user_id);
			pDTO.setPasswd(passwd);
			pDTO.setPassword(password);
			
			res=userService.findUserPw(pDTO);
			log.info(res);
			
			if(res==0) {
				model.addAttribute("msg","입력하신 Id는 회원이 아닙니다.");
				model.addAttribute("url","/findUserPw.do");
			} else {
				model.addAttribute("msg","이메일로 임시비밀번호를 보내드렸습니다.");
				model.addAttribute("url","/signIn.do");
			}
			
		} catch(Exception e) {
			log.info(e.toString());
		} finally {
			log.info(this.getClass().getName()+".findUserPwProc end!");
			pDTO=null;
			
		}
		return "/redirect"; 
	}
	
	@RequestMapping(value="mypage")
	public String MyPage(HttpSession session, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		log.info(this.getClass());
		
		String user_id=(String)session.getAttribute("user_id");
		
		UserDTO pDTO=new UserDTO();
		pDTO.setUser_id(user_id);
		
		UserDTO uDTO=new UserDTO();
		uDTO=userService.getMypage(pDTO);
		
		model.addAttribute("user_name", uDTO.getUser_name());
		model.addAttribute("email", EncryptUtil.decAES128CBC(uDTO.getEmail()));
		
		return "/user/mypage";
	}
	
	@RequestMapping(value="modifyUserInfo")
	public String ModifyUserInfo(HttpSession session, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		log.info(this.getClass().getName());
		
		String user_id=(String)session.getAttribute("user_id");
		
		UserDTO pDTO=new UserDTO();
		pDTO.setUser_id(user_id);
		
		UserDTO uDTO=new UserDTO();
		uDTO=userService.getMypage(pDTO);
		
		model.addAttribute("user_name", uDTO.getUser_name());
		model.addAttribute("email", EncryptUtil.decAES128CBC(uDTO.getEmail()));
		
		return "/user/modifyUserInfo";
	}

	@RequestMapping(value="changeName")
	public String ChangeName() throws Exception {
		log.info(this.getClass().getName());
		
		return "/user/changeName";
	}
	
	@RequestMapping(value="changeNameProc")
	public String ChangeNameProc(HttpSession session, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception{
		log.info(this.getClass().getName()+".changeNameProc start!");
		
		int res=0;
		
		String user_id=(String)session.getAttribute("user_id");
		String user_name=request.getParameter("user_name");
		
		UserDTO uDTO=new UserDTO();
		
		try {
			uDTO.setUser_name(user_name);
			uDTO.setUser_id(user_id);
			
			res=userService.changeName(uDTO);
			
			if(res>0) {
				model.addAttribute("url", "/modifyUserInfo.do");
				model.addAttribute("msg", "이름 수정에 성공하였습니다.");
			} else {
				model.addAttribute("url", "/modifyUserInfo.do");
				model.addAttribute("msg", "이름 수정에 실패하였습니다.");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			log.info(this.getClass().getName()+".changeNameProc end!");
			uDTO=null;
		}
		
		return "/redirect";
	}
	
	@RequestMapping(value="changeEmail")
	public String ChangeEmail() throws Exception{
		log.info(this.getClass().getName());
		
		return "/user/changeEmail";
	}
	
	@RequestMapping(value="changeEmailProc")
	public String ChangeEmailProc(HttpSession session, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception{
		log.info(this.getClass().getName()+".changeEmailProc start!");
		
		int res=0;
		
		UserDTO uDTO=new UserDTO();
		
		String user_id=(String)session.getAttribute("user_id");
		String email=EncryptUtil.encAES128CBC(request.getParameter("email"));
		
		try {
			uDTO.setUser_id(user_id);
			uDTO.setEmail(email);
			
			res=userService.changeEmail(uDTO);
			
			if(res>0) {
				model.addAttribute("url", "/modifyUserInfo.do");
				model.addAttribute("msg", "이메일 수정에 성공하였습니다.");
			} else {
				model.addAttribute("url", "/modifyUserInfo.do");
				model.addAttribute("msg", "이메일 수정에 실패하였습니다.");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			log.info(this.getClass().getName()+".changeEmailProc end!");
			uDTO=null;			
		}
		
		return "/redirect";
	}
	
	@RequestMapping(value="checkPw")
	public String CheckPw() {
		log.info(this.getClass().getName());
		
		return "/user/checkPw";
	}
	
	@RequestMapping(value="checkPwProc")
	public String CheckPwProc(HttpSession session, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception{
		log.info(this.getClass().getName()+".checkPwProc start!");
		
		UserDTO uDTO=new UserDTO();
		
		String user_id=(String)session.getAttribute("user_id");
		String password=EncryptUtil.encHashSHA256(request.getParameter("passwd"));
		
		try {
			uDTO.setUser_id(user_id);
			uDTO.setPassword(password);
			
			uDTO=userService.pwCheck(uDTO);
			
			if(uDTO.getPassword().equals(password)) {
				model.addAttribute("url", "/mypage.do");
				model.addAttribute("msg", "해당 회원정보페이지로 이동합니다.");
			} else {
				model.addAttribute("url", "/checkPw.do");
				model.addAttribute("msg", "입력하신 비밀번호에 해당하는 회원정보가 존재하지 않습니다.");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			log.info(this.getClass().getName()+".checkPwProc end!");
			uDTO=null;
		}
		
		return "/redirect";
	}
	
	@RequestMapping(value="changePw")
	public String ChangePw() throws Exception {
		log.info(this.getClass().getName());
		
		return "/user/changePw";
	}
	
	@RequestMapping(value="changePwProc")
	public String ChangePwProc(HttpSession session, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		log.info(this.getClass().getName()+".changePwProc start!");
	
		int res=0;
		
		UserDTO uDTO=new UserDTO();
		
		String user_id=(String)session.getAttribute("user_id");
		String password=EncryptUtil.encHashSHA256(request.getParameter("passwd"));
		
		try {
			uDTO.setUser_id(user_id);
			uDTO.setPassword(password);
			res=userService.changePw(uDTO);
			
			if(res>0) {
				model.addAttribute("url", "/index.do");
				model.addAttribute("msg", "비밀번호가 변경되었습니다.");
			} else {
				model.addAttribute("url", "/modifyUserInfo.do");
				model.addAttribute("msg", "비밀번호 변경에 실패하였습니다.");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			log.info(this.getClass().getName()+".changePwProc end!");
			uDTO=null;
		}
		
		return "/redirect";
	}
	
	@RequestMapping(value="deleteUserInfo")
	public String DeleteUserInfo(HttpSession session, ModelMap model) throws Exception{
		log.info(this.getClass().getName()+".deleteUserInfo start!");
		
		int res=0;
		
		UserDTO uDTO=new UserDTO();
		
		String user_id=(String)session.getAttribute("user_id");
		
		try {
			uDTO.setUser_id(user_id);
			
			res=userService.deleteUserInfo(uDTO);
			
			if(res>0) {
				session.invalidate();
				model.addAttribute("url", "/index.do");
				model.addAttribute("msg", "회원탈퇴에 성공하였습니다.");
			} else {
				model.addAttribute("url", "/mypage.do");
				model.addAttribute("msg", "회원탈퇴에 실패하였습니다.");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			log.info(this.getClass().getName()+".deleteUserInfo end!");
			uDTO=null;
		}
		
		return "/redirect";
	}
}
