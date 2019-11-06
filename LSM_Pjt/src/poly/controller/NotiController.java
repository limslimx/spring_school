package poly.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.dto.NotiDTO;
import poly.service.INotiService;

@Controller
public class NotiController {

	private Logger log=Logger.getLogger(this.getClass().getName());

	@Resource(name="NotiService")
	private INotiService notiService;
	
	@RequestMapping(value="noti/notiList")
	public String notiList(ModelMap model) throws Exception {
		log.info(this.getClass().getName()+".notiList start!");
		
		List<NotiDTO> nList=new ArrayList<>();
		
		try {
			nList=notiService.getNotiList();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			log.info(this.getClass().getName()+".notiList end!");
			model.addAttribute("nList", nList);
		}
		return "/noti/notiList";
	}
//	
//	@RequestMapping(value="noti/notiReg")
//	public String NotiReg() {
//		log.info(this.getClass());
//		
//		return "/noti/notiReg";
//	}
//	
//	@RequestMapping(value="noti/notiRegProc")
//	public String NotiRegProc(HttpSession session, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
//		log.info(this.getClass().getName()+".notiRegProc start!");
//		
//		NotiDTO nDTO=new NotiDTO();
//
//		int res=0;
//		
//		String title=request.getParameter("title");
//		String content=request.getParameter("content");
//		String reg_id=(String)session.getAttribute("user_id");
//
//		try {
//			
//			nDTO.setTitle(title);
//			nDTO.setContent(content);
//			nDTO.setReg_id(reg_id);
//			
//			res=notiService.insertNotiInfo(nDTO);
//			
//			log.info(res);
//		} catch(Exception e) {
//			e.printStackTrace();
//		} finally {
//			log.info(this.getClass().getName()+".notiRegProc end!");
//			if(res>0) {
//				model.addAttribute("url", "/noti/notiList.do");
//				model.addAttribute("msg", "글이 등록되었습니다.");
//			} else {
//				model.addAttribute("url", "/noti/notiReg.do");
//				model.addAttribute("msg", "글등록이 실패되었습니다.");
//			}
//		}
//		
//		return "/redirect";
//	}
	
	@RequestMapping(value="noti/notiDetail")
	public String NotiDetail(HttpServletRequest request, ModelMap model) throws Exception {
		log.info(this.getClass().getName()+".notiDetail start!");
		
		NotiDTO nDTO=new NotiDTO();
		
		String seq=request.getParameter("seq");
		
		try {
			nDTO=notiService.getNotiDetail(seq);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			log.info(this.getClass().getName()+".notiDetail end!");
			model.addAttribute("nDTO", nDTO);
			nDTO=null;
		}
		
		return "/noti/notiDetail";
	}
//	
//	@RequestMapping(value="noti/notiModify")
//	public String NotiModify(HttpServletRequest request, ModelMap model) {
//		log.info(this.getClass().getName()+".notiModify start!");
//		
//		NotiDTO nDTO=new NotiDTO();
//		
//		String seq=request.getParameter("seq");
//		
//		try {
//			nDTO=notiService.getNotiDetail(seq);
//		} catch(Exception e) {
//			e.printStackTrace();
//		} finally {
//			model.addAttribute("nDTO", nDTO);
//		}
//		
//		return "/noti/notiModify";
//	}
//	
//	@RequestMapping(value="noti/notiModifyProc")
//	public String NotiModifyProc(HttpSession session, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
//		log.info(this.getClass().getName()+".notiModifiyProc start!");
//		
//		int res=0;
//		
//		NotiDTO nDTO=new NotiDTO();
//		
//		String seq=request.getParameter("seq");
//		String title=request.getParameter("title");
//		String content=request.getParameter("content");
//		
//		try {
//			nDTO.setSeq(seq);
//			nDTO.setTitle(title);
//			nDTO.setContent(content);
//			
//			res=notiService.updateNotiInfo(nDTO);
//			log.info(res);
//		} catch(Exception e) {
//			e.printStackTrace();
//		} finally {
//			if(res>0) {
//				model.addAttribute("msg","수정에 성공하였습니다.");
//				model.addAttribute("url","/noti/notiDetail.do?seq="+seq);
//			} else {
//				model.addAttribute("msg","수정에 실패하였습니다.");
//				model.addAttribute("url","/noti/notiDetail.do?seq="+seq);
//			}
//		}
//		return "/redirect";
//	}
//	
//	@RequestMapping(value="noti/notiDelete")
//	public String NotiDelete(HttpServletRequest request, ModelMap model) throws Exception {
//		log.info(this.getClass().getName()+".notiDelete start!");
//		
//		NotiDTO nDTO=new NotiDTO();
//		
//		String msg="";
//		
//		String seq=request.getParameter("seq");
//		log.info("seq: "+seq);
//		
//		
//		try {
//			nDTO.setSeq(seq);
//			notiService.deleteNotiInfo(nDTO);
//			msg="글삭제에 성공하였습니다.";
//		} catch(Exception e) {
//			msg="글삭제에 실패하였습니다.";
//			e.printStackTrace();
//		} finally {
//			log.info(this.getClass().getName()+".notiDelete end!");
//			model.addAttribute("msg", msg);
//			model.addAttribute("url", "/noti/notiList.do");
//			nDTO=null;
//		}
//		return "/redirect";
//	}
	
}
