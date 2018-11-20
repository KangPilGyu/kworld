package org.web.kworld.news.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.web.kworld.news.service.NewsService;
import org.web.kworld.news.vo.NewsVO;


@Controller
//클라이언트가 요청하면 여기로 온다.
@RequestMapping(value="/news/*")
//news/아무거나 오면 여기로 와서 처리한다.
public class NewsController {
	@Resource(name="newsService")
	private NewsService newsService;
	
	private static final Logger logger = LoggerFactory.getLogger(NewsController.class);
	
	//컨트롤 메서드
	@RequestMapping(value="/main", method=RequestMethod.GET)//news/main
	public String newsmain(Model model){
		logger.info("news main 페이지 호출");

		model.addAttribute("list", newsService.selectList());
		
		logger.info("news main 페이지 호출여까지오니?");
		return"news.main";
	}
	
	
	
	@RequestMapping(value="/detail/{n_no}")//news/main
	public String newsDetail(@PathVariable("n_no") int n_no,Model model){
		logger.info("news Detail 페이지 호출");
		model.addAttribute("news",newsService.getSelectOne(n_no));
		return"news.detail";
	
	}
	
	
	@RequestMapping(value="/reg", method=RequestMethod.GET)//news/main
	public String newsReg(Model model){
		model.addAttribute("list", newsService.selectList());
		logger.info("news Reg 페이지 호출");
		return"news.reg";
	
	}
	
	

	@RequestMapping(value="/rev", method=RequestMethod.GET)//news/main
	public String newsRev(Model model){
		model.addAttribute("list", newsService.selectList());
		logger.info("news Rev 페이지 호출");
		return"news.rev";
	
	}
	
		
	
	@RequestMapping(value="/rev", method=RequestMethod.POST)//news/main
	public String newsUpdate(NewsVO vo){
		logger.info("news Rev post 페이지 호출"+vo.getN_no()+vo.getN_content());
		newsService.updateNews(vo);
		return"redirect:/news/main";
	}
	

	
}
