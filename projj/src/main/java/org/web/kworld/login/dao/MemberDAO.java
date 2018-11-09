package org.web.kworld.login.dao;

import org.web.kworld.login.vo.MemberVO;


public interface MemberDAO {
	
	public void insertMember(MemberVO vo) throws Exception;
	
	public int findEmail(String m_email) throws Exception;
	
}
