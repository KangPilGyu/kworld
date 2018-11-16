package org.web.kworld.notice.dao;

import java.util.List;

import org.web.kworld.notice.vo.NoticeVO;

import com.util.page.SearchCriteria;

public interface NoticeDAO {
	
	public void insertNotice(NoticeVO vo) throws Exception;
	
	public List<NoticeVO> selectNoticeList(SearchCriteria cri) throws Exception;
	
	public int countNotice(SearchCriteria cri) throws Exception;
	
	public NoticeVO selectNotice(int n_no) throws Exception;
	
	public void updateCnt(int n_no) throws Exception;
	
	public void updateNotice(NoticeVO vo) throws Exception;
	
	public void deleteNotice(int n_no) throws Exception;
}
