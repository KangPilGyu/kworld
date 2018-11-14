package org.web.kworld.notice.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.web.kworld.notice.vo.NoticeVO;

@Repository
public class NoticeDAOImpl implements NoticeDAO{
	@Inject
	private SqlSession sqlSession;
	
	// Mapper 네임스페이스
	private static final String NAMESPACE="org.web.kworld.notice.NoticeMapper";
	
	@Override
	public void insertNotice(NoticeVO vo) throws Exception {
		sqlSession.insert(NAMESPACE+".insertNotice",vo);
	}

}
