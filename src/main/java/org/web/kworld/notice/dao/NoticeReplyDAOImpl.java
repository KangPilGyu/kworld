package org.web.kworld.notice.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.web.kworld.notice.vo.ReplyVO;

@Repository
public class NoticeReplyDAOImpl implements NoticeReplyDAO{
	@Inject
	private SqlSession sqlSession;
	
	// Mapper 네임스페이스
	private static final String NAMESPACE="org.web.kworld.notice.NoticeReplyMapper";

	@Override
	public void insertReply(ReplyVO vo) throws Exception {
		sqlSession.insert(NAMESPACE+".insertReply",vo);
	}

	@Override
	public List<ReplyVO> selectAll(int b_no) throws Exception {
		return sqlSession.selectList(NAMESPACE+".selectAll",b_no);
	}

	@Override
	public void deleteReply(int r_no) throws Exception {
		sqlSession.selectList(NAMESPACE+".deleteReply",r_no);
		
	}
	
	
	
}
