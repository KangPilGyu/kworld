package org.web.kworld.notice.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.web.kworld.notice.dao.NoticeDAO;
import org.web.kworld.notice.vo.NoticeVO;

@Service
public class NoticeService {
	
	@Inject
	private NoticeDAO noticeDAO;
	
	public void registNotice(NoticeVO vo) {
		
		try {
			noticeDAO.insertNotice(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
