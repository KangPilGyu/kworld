package org.web.kworld.notice.dao;

import java.util.List;

import org.web.kworld.notice.vo.ReplyVO;

public interface NoticeReplyDAO {
	
	public void insertReply(ReplyVO vo) throws Exception;
	
	public List<ReplyVO> selectAll(int b_no) throws Exception;
	
	public void deleteReply(int r_no) throws Exception;
}
