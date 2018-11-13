package org.web.kworld.login.dao;

import org.web.kworld.login.vo.MemberVO;


public interface MemberDAO {
	
	public void insertMember(MemberVO vo) throws Exception;
	
	public int findEmail(String m_email) throws Exception;
	
	public int findAuthnum(String authnum) throws Exception;
	
	public void updateAuth(String authnum) throws Exception;
	
	public MemberVO selectMember(String m_email, String m_pwd) throws Exception;
	
	public MemberVO selectSNSMember(String m_email) throws Exception;
	
	public void updateSNS(MemberVO vo) throws Exception;
	
}
