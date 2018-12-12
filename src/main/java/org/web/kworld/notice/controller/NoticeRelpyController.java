package org.web.kworld.notice.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.web.kworld.notice.service.NoticeReplyService;
import org.web.kworld.notice.vo.ReplyVO;

@RestController
@RequestMapping(value="/noticereply/*")
public class NoticeRelpyController {
	
	@Inject
	private NoticeReplyService replyService;  
	
	private static final Logger logger = LoggerFactory.getLogger(NoticeRelpyController.class);
	
	@RequestMapping(value="/reply", method=RequestMethod.POST)
	public ResponseEntity<String> register(
			@RequestBody ReplyVO vo){
		return replyService.addReply(vo);
	}
	@RequestMapping(value="/reply/all/{n_no}", method=RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> selectAll(
			@PathVariable("n_no") int n_no){
		return replyService.selectReply(n_no);
	}
	@RequestMapping(value="/reply/delete/{r_no}", method=RequestMethod.DELETE)
	public ResponseEntity<String> delete(
			@PathVariable("r_no") int r_no){
		return replyService.deleteReply(r_no);
	}
}
