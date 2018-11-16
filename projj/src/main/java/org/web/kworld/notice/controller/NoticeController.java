package org.web.kworld.notice.controller;

import java.security.Principal;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.web.kworld.notice.service.NoticeService;
import org.web.kworld.notice.vo.NoticeVO;

import com.util.page.PageMaker;
import com.util.page.SearchCriteria;

@Controller
@RequestMapping(value = "/notice/*")
public class NoticeController {
// 공지사항 컨트롤러	
	
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	
	@Inject
	private NoticeService noticeService;
	
	// 메인 페이지	
	@RequestMapping(value="/main") 
	public String blogmain(@ModelAttribute("cri") SearchCriteria cri,
			Model model) {
		logger.info("notice main 페이지 호출");
		
		model.addAttribute("list",noticeService.getList(cri));
		
		model.addAttribute("pageMaker",noticeService.getPageMaker(cri));	
		
		return "notice.main";  
	}
	
	// 공지 사항 등록 페이지
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String noticeRegisterGET() {
		logger.info("notice register get 페이지 호출");
		return "notice.register";
	}
	
	// 공지 사항 등록 작업
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String noticeRegisterPOST(NoticeVO vo) {
		logger.info("notice register post 페이지 호출");
		
		noticeService.registNotice(vo);
		
		return "notice/registerSuccess";
	}
	
	// 공지사항 detail 페이지
	@RequestMapping(value="/detail/{n_no}", method=RequestMethod.GET)
	public String detailNoticeGET(@PathVariable("n_no") int n_no,
			Model model
			) {
		logger.info("notice detail get 페이지 호출");
		model.addAttribute("notice",noticeService.getNotice(n_no));		
		return "notice.detail";
	}
	
	@RequestMapping(value="/update/{n_no}", method=RequestMethod.GET)
	public String updateNoticeGET(@PathVariable("n_no") int n_no,
			Model model
			) {
		logger.info("notice update get 페이지 호출");
		model.addAttribute("notice",noticeService.getNotice(n_no));				
		return "notice.update";
	}
	
	@RequestMapping(value="/update/{n_no}", method=RequestMethod.POST)
	public String updateNoticePOST(NoticeVO vo,
			Model model,
			RedirectAttributes rttr
			) {
		logger.info("notice update post 페이지 호출");
		
		noticeService.updateNotice(vo);
		rttr.addAttribute("msg","upsuccess");
		return "redirect:/notice/main";
	}
	@RequestMapping(value="/delete/{n_no}", method=RequestMethod.GET)
	public String deleteNoticePOST(
			@PathVariable("n_no") int n_no,
			Model model,
			RedirectAttributes rttr
			) {
		logger.info("notice delete get 페이지 호출");
		
		noticeService.deleteNotice(n_no);
		rttr.addAttribute("msg","delsuccess");
		
		return "redirect:/notice/main";
	}
	
}
