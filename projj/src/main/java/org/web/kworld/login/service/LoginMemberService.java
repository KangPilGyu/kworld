package org.web.kworld.login.service;

import javax.inject.Inject;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.web.kworld.login.dao.MemberDAO;
import org.web.kworld.login.vo.MemberVO;
import org.web.kworld.util.service.EmailService;

import com.util.mail.AuthKeyMaker;

@Service
public class LoginMemberService {
	@Inject
	private MemberDAO memberDAO;
	
	@Inject
	private BCryptPasswordEncoder passwordEncoder;
	
	@Inject
	private EmailService emailService;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void insertMember(MemberVO member) {
		// 1. 비밀번호 encode 처리
		String encodingPW = passwordEncoder.encode(member.getM_pwd());
		member.setM_pwd(encodingPW);
		// 2.  이메일 보낼 토큰 값 생성,저장
		String authNum=new AuthKeyMaker().getKey(6, true);
		member.setM_authnum(authNum);
		// 3. DB insert
		try {
			memberDAO.insertMember(member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 4. email 발송
		emailService.authNumSend(member);
	}
	
	public boolean hasEmail(String m_email) {
		boolean result=false;
		try {
			 result = (memberDAO.findEmail(m_email) ==0) ? false : true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
