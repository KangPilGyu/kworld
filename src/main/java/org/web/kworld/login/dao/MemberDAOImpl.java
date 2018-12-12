package org.web.kworld.login.dao;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.web.kworld.login.vo.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	@Inject
	private SqlSession sqlSession;
	
	// Mapper 네임스페이스
	private static final String NAMESPACE="org.web.kworld.login.MemberMapper";
	
	@Override
	public void insertMember(MemberVO vo) throws Exception {
		sqlSession.insert(NAMESPACE+".insertMember",vo);
	}

	@Override
	public int findEmail(String m_email) throws Exception {
		return sqlSession.selectOne(NAMESPACE+".findEmail",m_email);
	}

	@Override
	public int findAuthnum(String authnum) throws Exception {
		return sqlSession.selectOne(NAMESPACE+".findAuthnum",authnum);
	}

	@Override
	public void updateAuth(String authnum) throws Exception {
		sqlSession.update(NAMESPACE+".updateAuth",authnum);
		
	}

	@Override
	public MemberVO selectMember(String m_email, String m_pwd) throws Exception {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("m_email", m_email);
		paramMap.put("m_pwd", m_pwd);
		
		return sqlSession.selectOne(NAMESPACE+".selectMember",paramMap);
	}

	@Override
	public MemberVO selectSNSMember(String m_email) throws Exception {
	
		return sqlSession.selectOne(NAMESPACE+".selectSNSMember",m_email);
	}

	@Override
	public void updateSNS(MemberVO vo) throws Exception {
		sqlSession.selectOne(NAMESPACE+".updateSNS",vo);
	}

}
