package org.web.kworld.security;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.web.kworld.login.dao.MemberDAO;
import org.web.kworld.login.vo.MemberVO;


public class CustomUserDetailService implements UserDetailsService {
	
	private SqlSession sqlSession;
	
	
	public CustomUserDetailService(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	private static final String NAMESPACE="org.web.kworld.login.MemberMapper";
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 MemberVO member=null;
		try {
			member = sqlSession.selectOne(NAMESPACE+".selectSNSMember",username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 if(member==null) throw new UsernameNotFoundException(username);
		 
		 List<GrantedAuthority> authorities = new ArrayList<>();
		 authorities.add(new SimpleGrantedAuthority(member.getM_auth()));
		 
		return new SecUser(member.getM_email(), member.getM_pwd(), 
				true, member.getM_id(), member.getM_name(), 
				true, true, true, authorities);
	}
	

}
