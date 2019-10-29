package poly.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.dto.NotiDTO;
import poly.dto.UserDTO;
import poly.service.impl.AdminService;

@Controller
public class AdminController {

	private Logger log=Logger.getLogger(getClass());

	@Resource(name="AdminService")
	private AdminService adminService;
	
	@RequestMapping(value="userInfoList")
	public String UserInfoAdmin(ModelMap model) throws Exception{
		log.info(this.getClass().getName()+".userInfoList start!");
		
		
		List<UserDTO> uList=new ArrayList<>();
		
		try {
			uList=adminService.getUserList();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			log.info(this.getClass().getName()+".userInfoList end!");
			model.addAttribute("uList", uList);
		}	
		
		return "/admin/userInfo/userInfoList";
	}
	
	@RequestMapping(value="userInfoDetail")
	public String UserInfoDetail(HttpServletRequest request, ModelMap model) throws Exception{
		log.info(this.getClass().getName()+".userInfoDetail start!");
		
		UserDTO uDTO=new UserDTO();
		
		String user_id=request.getParameter("user_id");
		log.info(user_id);
		
		try {
			uDTO.setUser_id(user_id);
			
			uDTO=adminService.getUserDetail(uDTO);
			
			model.addAttribute("uDTO", uDTO);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			log.info(this.getClass().getName()+".userInfoDetail end!");
			uDTO=null;
		}
		
		return "/admin/userInfo/userInfoDetail";
	}
	
	@RequestMapping(value="userInfoDelete")
	public String UserInfoDelete(HttpServletRequest request, ModelMap model) throws Exception{
		log.info(this.getClass().getName()+".userInfoDelete start!");
		
		int res=0;
		
		UserDTO uDTO=new UserDTO();
		
		String user_id=request.getParameter("user_id");
	
		try {
			uDTO.setUser_id(user_id);
			
			res=adminService.userInfoDelete(uDTO);
			
			if(res>0) {
				model.addAttribute("url", "/userInfoList.do");
				model.addAttribute("msg", "회원탈퇴에 성공하였습니다.");
			} else {
				model.addAttribute("url", "/userInfoList.do");
				model.addAttribute("msg", "회원탈퇴에 실패하였습니다.");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			log.info(this.getClass().getName()+".userInfoDelete end!");
			uDTO=null;
		}
		
		return "/redirect";
	}
	
	@RequestMapping(value="notiInfoList")
	public String NotiInfoList(ModelMap model) throws Exception{
		log.info(this.getClass().getName()+".notiInfoList start!");
		
		List<NotiDTO> nList=new ArrayList<>();
		
		try {
			nList=adminService.getNotiList();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			log.info(this.getClass().getName()+".notiInfoList end!");
			model.addAttribute("nList", nList);
		}
		
		return "/admin/notiInfo/notiInfoList";
	}
	
	@RequestMapping(value="notiInfoReg")
	public String NotiInfoReg() throws Exception{
		log.info(this.getClass().getName());
		
		return "/admin/notiInfo/notiInfoReg";
	}
	
	@RequestMapping(value="notiInfoRegProc")
	public String NotiInfoRegProc(HttpSession session, HttpServletRequest request, ModelMap model) throws Exception{
		log.info(this.getClass().getName()+".notiInfoRegProc start!");
		
		int res=0;
		
		NotiDTO nDTO=new NotiDTO();
		
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		String reg_id=(String)session.getAttribute("user_id");
		
		try {
			nDTO.setTitle(title);
			nDTO.setContent(content);
			nDTO.setReg_id(reg_id);
			
			res=adminService.insertNotiInfo(nDTO);
			
			if(res>0) {
				model.addAttribute("url", "/notiInfoList.do");
				model.addAttribute("msg", "공지사항 등록에 성공하였습니다.");
			} else {
				model.addAttribute("url", "/notiInfoList.do");
				model.addAttribute("msg", "공지사항 등록에 실패하였습니다.");
			}
				
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			log.info(this.getClass().getName()+".notiInfoRegProc end!");
			nDTO=null;
		}
		
		return "/redirect";
	}
	
	@RequestMapping(value="notiInfoDetail")
	public String NotiInfoDetail(HttpServletRequest request, ModelMap model) throws Exception{
		log.info(this.getClass().getName()+".notiInfoDetail start!");
		
		NotiDTO nDTO=new NotiDTO();
		
		String seq=request.getParameter("seq");
		log.info(seq);
		
		try {
			nDTO.setSeq(seq);
			
			nDTO=adminService.getNotiDetail(nDTO);
			
			model.addAttribute("nDTO", nDTO);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			log.info(this.getClass().getName()+".notiInfoDetail end!");
			nDTO=null;
		}
		
		return "/admin/notiInfo/notiInfoDetail";
	}
	
	@RequestMapping(value="notiInfoModify")
	public String NotiInfoModify(HttpServletRequest request, ModelMap model) throws Exception{
		log.info(this.getClass().getName()+".notiInfoModify start!");
		
		NotiDTO nDTO=new NotiDTO();
		
		String seq=request.getParameter("seq");
		
		try {
			nDTO.setSeq(seq);
			
			nDTO=adminService.getNotiDetail(nDTO);
			
			model.addAttribute("nDTO", nDTO);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			log.info(this.getClass().getName()+".notiInfoModify end!");
			nDTO=null;
		}
		
		return "/admin/notiInfo/notiInfoModify";
	}
	
	@RequestMapping(value="notiInfoModifyProc")
	public String NotiInfoModifyProc(HttpServletRequest request, ModelMap model) throws Exception{
		log.info(this.getClass().getName()+".notiInfoModifyProc start!");
		
		int res=0;
		
		NotiDTO nDTO=new NotiDTO();
		
		String seq=request.getParameter("seq");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		
		try {
			nDTO.setSeq(seq);
			nDTO.setTitle(title);
			nDTO.setContent(content);
			
			res=adminService.updateNotiInfo(nDTO);
			
			if(res>0) {
				model.addAttribute("url", "/notiInfoDetail.do?seq="+seq);
				model.addAttribute("msg", "공지사항 수정에 성공하였습니다.");
			} else {
				model.addAttribute("url", "/notiInfoDetail.do?seq="+seq);
				model.addAttribute("msg", "공지사항 수정에 실패하였습니다.");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			log.info(this.getClass().getName()+".notiInfoModifyProc end!");
			nDTO=null;
		}
		
		return "/redirect";
	}
	
	@RequestMapping(value="notiInfoDelete")
	public String NotiInfoDelete(HttpServletRequest request, ModelMap model) throws Exception{
		log.info(this.getClass().getName()+".notiInfoDelete start!");
		
		int res=0;
		
		NotiDTO nDTO=new NotiDTO();
		
		String seq=request.getParameter("seq");
		
		try {
			nDTO.setSeq(seq);
			
			res=adminService.deleteNotiInfo(nDTO);
			
			if(res>0) {
				model.addAttribute("url", "/notiInfoList.do");
				model.addAttribute("msg", "공지사항 삭제에 성공하였습니다.");
			} else {
				model.addAttribute("url", "/notiInfoList.do");
				model.addAttribute("msg", "공지사항 삭제에 실패하였습니다.");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			log.info(this.getClass().getName()+".notiInfoDelete end!");
			nDTO=null;
		}
		
		return "/redirect";
	}
	
	@RequestMapping(value="freeNotiInfoList")
	public String FreeNotiInfoList(ModelMap model) throws Exception{
		log.info(this.getClass().getName()+".freeNotiInfoList start!");
		
		return null;
	}
}
