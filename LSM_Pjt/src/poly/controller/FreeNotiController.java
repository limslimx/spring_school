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

import poly.dto.FreeNotiDTO;
import poly.service.IFreeNotiService;

@Controller
public class FreeNotiController {
	
	private Logger log=Logger.getLogger(getClass());

	@Resource(name="FreeNotiService")
	private IFreeNotiService freeNotiService;
	
	@RequestMapping(value="freeNotiList")
	public String FreeNotiList(ModelMap model) throws Exception{
		log.info(this.getClass().getName()+".freeNotiList start!");
		
		List<FreeNotiDTO> fnList=new ArrayList<>();
		
		try {
			fnList=freeNotiService.getFreeNotiList();
			
			model.addAttribute("fnList", fnList);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			log.info(this.getClass().getName()+".freeNotiList end!");
		}
		
		return "/freeNoti/freeNotiList";
	}
	
	@RequestMapping(value="freeNotiReg")
	public String FreeNotiReg() throws Exception{
		log.info(this.getClass().getName());
		
		return "/freeNoti/freeNotiReg";
	}
	
	@RequestMapping(value="freeNotiRegProc")
	public String FreeNotiRegProc(HttpSession session, HttpServletRequest request, ModelMap model) throws Exception{
		log.info(this.getClass().getName()+".freeNotiRegProc start!");
		
		int res=0;
		
		FreeNotiDTO fnDTO=new FreeNotiDTO();
		
		String reg_id=(String)session.getAttribute("user_id");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		
		try {
			fnDTO.setReg_id(reg_id);
			fnDTO.setTitle(title);
			fnDTO.setContent(content);
			
			res=freeNotiService.insertFreeNotiInfo(fnDTO);
			
			if(res>0) {
				model.addAttribute("url", "/freeNotiList.do");
				model.addAttribute("msg", "자유게시판 글 등록에 성공하였습니다.");
			} else {
				model.addAttribute("url", "/freeNotiList.do");
				model.addAttribute("msg", "자유게시판 글 등록에 실패하였습니다.");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			log.info(this.getClass().getName()+".freeNotiRegProc end!");
			fnDTO=null;
		}
		
		return "/redirect";
	}
	
	@RequestMapping(value="freeNotiDetail")
	public String FreeNotiDetail(HttpServletRequest request, ModelMap model) throws Exception{
		log.info(this.getClass().getName()+".freeNotiDetail start!");
		
		FreeNotiDTO fnDTO=new FreeNotiDTO();
		
		String seq=request.getParameter("seq");
		
		try {
			fnDTO.setSeq(seq);
			
			fnDTO=freeNotiService.getFreeNotiDetail(fnDTO);
			
			model.addAttribute("fnDTO", fnDTO);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			log.info(this.getClass().getName()+".freeNotiDetail end!");
			fnDTO=null;
		}
		
		return "/freeNoti/freeNotiDetail";
	}
	
	@RequestMapping(value="freeNotiModify")
	public String FreeNotiModify(HttpServletRequest request, ModelMap model) throws Exception{
		log.info(this.getClass().getName()+".freeNotiModify start!");
		
		FreeNotiDTO fnDTO=new FreeNotiDTO();
		
		String seq=request.getParameter("seq");
		
		try {
			fnDTO.setSeq(seq);
			
			fnDTO=freeNotiService.getFreeNotiDetail(fnDTO);
			
			model.addAttribute("fnDTO", fnDTO);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			log.info(this.getClass().getName()+".freeNotiModify end!");
			fnDTO=null;
		}
		
		return "/freeNoti/freeNotiModify";
	}
	
	@RequestMapping(value="freeNotiModifyProc")
	public String FreeNotiModifyProc(HttpServletRequest request, ModelMap model) throws Exception{
		log.info(this.getClass().getName()+".freeNotiModifyProc start!");
		
		int res=0;
		
		FreeNotiDTO fnDTO=new FreeNotiDTO();
		
		String seq=request.getParameter("seq");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		
		try {
			fnDTO.setSeq(seq);
			fnDTO.setTitle(title);
			fnDTO.setContent(content);
			
			res=freeNotiService.updateFreeNotiInfo(fnDTO);
			
			if(res>0) {
				model.addAttribute("url", "/freeNotiDetail.do?seq="+seq);
				model.addAttribute("msg", "자유게시판 글 수정에 성공하였습니다.");
			} else {
				model.addAttribute("url", "/freeNotiDetail.do?seq="+seq);
				model.addAttribute("msg", "자유게시판 글 수정에 실패하였습니다.");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			log.info(this.getClass().getName()+".freeNotiModifyProc end!");
			fnDTO=null;
		}
		
		return "/redirect";
	}
	
	@RequestMapping(value="freeNotiDelete")
	public String FreeNotiDelete(HttpServletRequest request, ModelMap model) throws Exception{
		log.info(this.getClass().getName()+".freeNotiDelete start!");
		
		int res=0;
		
		FreeNotiDTO fnDTO=new FreeNotiDTO();
		
		String seq=request.getParameter("seq");
		
		try {
			fnDTO.setSeq(seq);
			
			res=freeNotiService.deleteNotiInfo(fnDTO);
			
			if(res>0) {
				model.addAttribute("url", "/freeNotiList.do");
				model.addAttribute("msg", "자유게시판 글 삭제에 성공하였습니다.");
			} else {
				model.addAttribute("url", "/freeNotiDetail.do?seq="+seq);
				model.addAttribute("msg", "자유게시판 글 삭제에 실패하였습니다.");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			log.info(this.getClass().getName()+"freeNotiDelete end!");
			fnDTO=null;
		}
		
		return "/redirect";
	}
}
