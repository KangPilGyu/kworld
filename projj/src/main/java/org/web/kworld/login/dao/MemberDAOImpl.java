package org.web.kworld.login.dao;

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

}
