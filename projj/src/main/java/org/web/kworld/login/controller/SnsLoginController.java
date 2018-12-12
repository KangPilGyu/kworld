package org.web.kworld.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.web.kworld.login.service.FaceBookLoginService;
import org.web.kworld.login.service.GoogleLoginService;
import org.web.kworld.login.service.NaverLoginService;
import org.web.kworld.login.vo.MemberVO;

@Controller
@RequestMapping(value = "/login/*")
public class SnsLoginController {
	private static final Logger logger = LoggerFactory.getLogger(SnsLoginController.class);
	
	private GoogleLoginService googleLoginService;	
	
	@Autowired
    public void setGoogleLoginService(GoogleLoginService googleLoginService) {
		this.googleLoginService = googleLoginService;
	}
	
	private FaceBookLoginService facebookLoginService;	
	@Autowired
    public void setfacebookLoginService(FaceBookLoginService facebookLoginService) {
		this.facebookLoginService = facebookLoginService;
	}
	
	private NaverLoginService naverLoginService;
	@Autowired
	  public void setNaverLoginService(NaverLoginService naverLoginService) {
		this.naverLoginService = naverLoginService;
	}
	  @RequestMapping(value = "/login", method = { RequestMethod.GET})
	    public String loginlogin(HttpServletResponse response, Model model, HttpSession session,
	    		RedirectAttributes rttr
	    		,HttpServletRequest request) {
		  logger.info("login / login  호출");
		  
		  	String google_url = googleLoginService.getGoogleUrl();
	    	model.addAttribute("google_url",google_url);
	    	
	    	String facebook_url = facebookLoginService.getFaceBookUrl();
	    	model.addAttribute("facebook_url",facebook_url);
	    	
	    	String naver_url =naverLoginService.getNaverUrl(session);
	    	model.addAttribute("naver_url",naver_url);
		  
	        return "login.login";
	    }
    // ------------------------------------ 구글 콜백 ----------------------------------------
    @RequestMapping(value = "/google", method = { RequestMethod.GET })
    public String doSessionAssignActionPageGoogle(
    		@RequestParam(value="code",required=false) String code,
    		Model model,
    		HttpServletRequest request) throws Exception {
    	
    	
    	logger.info("구글 콜백 호출~~~"+code);
    	googleLoginService.makeAccessToken(code);
        MemberVO member = googleLoginService.getUserProfile();
           
        model.addAttribute("member",member);
        return "login/loginSuccess";
    }
    
    // ------------------------------------ 페이스북 콜백 ----------------------------------------
    @RequestMapping(value = "/facebook", method = { RequestMethod.GET, RequestMethod.POST })
    public String doSessionAssignActionPageFacebook(HttpServletRequest request) throws Exception {
    	logger.info("페이스북 콜백 호출~~~");
    	//페이스 북 정보 가져오기 처리
    	// 2018.11.04 아직 https 인증 못함 ㅎ추후 처리...
        return "login/loginSuccess";
 
    }
    // ------------------------------------ 네이버 콜백 ----------------------------------------
    @RequestMapping(value = "/naver", method = { RequestMethod.GET, RequestMethod.POST })
    public String doSessionAssignActionPageNaver(
    		@RequestParam(value="code", required=false) String code,
    		@RequestParam String state, 
    		HttpSession session, Model model) throws Exception {
 
        //네이버 정보 가져오기 처리
    	logger.info("네이버 콜백 호출~~~");
    	// make 토큰
    	naverLoginService.makeAccessToken(session, code, state);
    
    	// get user
    	
    	JSONParser parser = new JSONParser();
    	
    	JSONObject jsonObject = new JSONObject((JSONObject)parser.parse(naverLoginService.getUserProfile()));
    	System.out.println(jsonObject);
    	System.out.println(jsonObject.get("resultcode"));
    	model.addAttribute("result", jsonObject);
    	return "login/loginSuccess";
       // return "redirect:/";
    }
}
