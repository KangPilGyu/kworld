package org.web.kworld.notice.controller;

import java.security.Principal;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.web.kworld.notice.service.NoticeService;
import org.web.kworld.notice.vo.NoticeVO;
import org.web.kworld.security.SecUser;

@Controller
@RequestMapping(value = "/notice/*")
public class NoticeController {
	
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	
	@Inject
	private NoticeService noticeService;
	
	
	@RequestMapping(value="/main")
	public String blogmain() {
		logger.info("notice main 페이지 호출");
		return "notice.main";
	}
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String blogRegisterGET() {
		logger.info("notice register get 페이지 호출");
		
		
		return "notice.register";
	}
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String blogRegisterPOST(NoticeVO vo,Principal principal) {
		logger.info("notice register post 페이지 호출");
		
		noticeService.registNotice(vo);
		
		return "notice/registerSuccess";
	}
	
}
