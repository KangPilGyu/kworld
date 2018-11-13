package org.web.kworld.login.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.web.kworld.login.service.LoginMemberService;
import org.web.kworld.login.vo.MemberVO;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/login/*")
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Inject
	private LoginMemberService loginMemberService;
	
	// mail AJax 처리
	@ResponseBody
	@RequestMapping(value="/hasmail" ,method = RequestMethod.POST)
	public String hasMail(@RequestParam("m_email") String m_email) {
		logger.info("ajax 처리 e_mail : "+m_email);
		String str="none";
		if(loginMemberService.hasEmail(m_email))
			str="exist";
		logger.info("----------------------------"+str);
		return str;
	} 
	
	// 도로 API 처리
	@RequestMapping(value="/doro", method = { RequestMethod.GET, RequestMethod.POST })
		public String doro() {
			logger.info("도로명 api 호출");
			return "login/doro";
		}
	// 회원 가입 페이지
	@RequestMapping(value="/join", method = RequestMethod.GET )
	public String joinGET() {
		logger.info("회원가입페이지호출 join -> get");
		return "login.join";
	}
	// 회원 가입 성공( 인증 미완료 )
	@RequestMapping(value="/join", method = RequestMethod.POST )
	public String joinPOST(@ModelAttribute("member") MemberVO member) {
		logger.info("회원가입페이지 신청 join -> post");
		//logger.info(member.getM_email());

		loginMemberService.insertMember(member);
		
		return "login.welcome";
	}
	// 회원 가입 인증 처리 페이지
	@RequestMapping(value="/auth/{authnum}", method = RequestMethod.GET )
	public String joinAuth(@PathVariable("authnum") String authnum,
			Model model) {
		logger.info("회원 가입 인증 처리 페이지");
				
		int result = loginMemberService.searchAuthnum(authnum);
		if(result>0)
			model.addAttribute("msg","success");
		else
			model.addAttribute("msg","false");
		
		
		return "login/authFind";
	}
	
	  @ResponseBody
	  @RequestMapping(value = "/loginCk", method = { RequestMethod.POST })
	  public String loginCk(String m_email, String m_pwd) {
		  return loginMemberService.searchMember(m_email, m_pwd);
	  }

  		
}
