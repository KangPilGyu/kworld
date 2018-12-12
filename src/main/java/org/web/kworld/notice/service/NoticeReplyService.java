package org.web.kworld.notice.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.web.kworld.notice.dao.NoticeReplyDAO;
import org.web.kworld.notice.vo.ReplyVO;

@Service
public class NoticeReplyService {
	@Inject
	private NoticeReplyDAO noticeReplyDAO;
	
	public ResponseEntity<String> addReply(ReplyVO vo) {
		ResponseEntity<String> entity=null;
		try {
			noticeReplyDAO.insertReply(vo);
			entity = new ResponseEntity<String>("success",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	public ResponseEntity<List<ReplyVO>> selectReply(int b_no){
		ResponseEntity<List<ReplyVO>> entity=null;
		try {
			entity = new ResponseEntity<List<ReplyVO>>
								(noticeReplyDAO.selectAll(b_no),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>
								(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	public ResponseEntity<String> deleteReply(int r_no) {
		ResponseEntity<String> entity=null;
		try {
			noticeReplyDAO.deleteReply(r_no);
			entity = new ResponseEntity<String>("success",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

}
