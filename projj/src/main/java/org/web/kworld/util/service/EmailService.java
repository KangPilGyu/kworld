package org.web.kworld.util.service;

import java.io.UnsupportedEncodingException;

import javax.inject.Inject;
import javax.mail.MessagingException;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.web.kworld.login.vo.MemberVO;

import com.util.mail.MailHandler;

@Service
public class EmailService {
	@Inject
	private JavaMailSender javaMailSender;
	
	public void authNumSend(MemberVO member) {
		
		MailHandler sendMail;
		try {
			sendMail = new MailHandler(javaMailSender);
			sendMail.setSubject("[KWorld 서비스 이메일 인증]");
			sendMail.setText(
					new StringBuffer().append("<h1>이메일 인증 </h1>")
												.append("<span>"+member.getM_name()+"의 가입을 환영합니다. 이메일 인증 부탁드립니다.</span>")
												.append("<a href='http://localhost/login/authEmail/token="+member.getM_authnum()+"'>이메일 인증 확인</a>").toString());
			sendMail.setFrom("eiffeltop@nate.com", "Kworld 주인장");
			sendMail.setTo(member.getM_email());
			sendMail.send();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	
	}
}
