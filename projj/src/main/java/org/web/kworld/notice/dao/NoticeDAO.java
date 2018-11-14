package org.web.kworld.notice.dao;

import java.util.List;

import org.web.kworld.notice.vo.NoticeVO;

public interface NoticeDAO {
	public void insertNotice(NoticeVO vo) throws Exception;
	
	//public List<NoticeVO> selectNoticeList() throws Exception;
}
