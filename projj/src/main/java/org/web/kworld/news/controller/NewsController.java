package org.web.kworld.news.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.web.kworld.news.service.NewsService;

import com.google.inject.Inject;


@Controller
//클라이언트가 요청하면 여기로 온다.
@RequestMapping(value="/news/*")
//news/아무거나 오면 여기로 와서 처리한다.
public class NewsController {
	@Inject
	private NewsService newsService;
	
	private static final Logger logger = LoggerFactory.getLogger(NewsController.class);
	
	@RequestMapping(value="/main", method=RequestMethod.GET)//news/main
	public String newsmain(Model model){
		logger.info("news main 페이지 호출");
	
		model.addAttribute("list", newsService.selectList());
		
		logger.info("news main 페이지 호출여까지오니?");
		return"news.main";
	}
	
	
	
	@RequestMapping(value="/view")//news/main
	public String newsview(){
		logger.info("news view 페이지 호출");
		return"news.view";
	
	}
	
	
}
