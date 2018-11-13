package org.web.kworld.blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/blog/*")
public class BlogController {
	
	private static final Logger logger = LoggerFactory.getLogger(BlogController.class);
	
	@RequestMapping(value="")
	public String blogmain() {
		logger.info("blog main 페이지 호출");
		return "blog.main";
	}
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public String blogCreateGet()
	{
		return "blog.create";
	}
}
