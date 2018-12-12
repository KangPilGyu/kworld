package org.web.kworld.notice.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.web.kworld.notice.dao.NoticeDAO;
import org.web.kworld.notice.vo.NoticeVO;

import com.util.page.PageMaker;
import com.util.page.SearchCriteria;

@Service
public class NoticeService {
	
	@Inject
	private NoticeDAO noticeDAO;
	
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void registNotice(NoticeVO vo) {	
		try {
			noticeDAO.insertNotice(vo);
			
			
			
			String[] files = vo.getFiles();
			if(files==null) {return;}
			
			for (String fileName : files) {
				noticeDAO.addAttach(fileName);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public List<NoticeVO> getList(SearchCriteria cri){
		List<NoticeVO> list=null;
		try {		
			list=noticeDAO.selectNoticeList(cri);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public NoticeVO getNotice(int n_no) {
		NoticeVO noticeVO = null;
		try {
			noticeDAO.updateCnt(n_no);
			noticeVO = noticeDAO.selectNotice(n_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return noticeVO;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateNotice(NoticeVO vo) {
		try {
			noticeDAO.updateNotice(vo);
			
			int n_no = vo.getN_no();
			
			noticeDAO.deleteAttach(n_no);
			
			String[] files = vo.getFiles();
			
			if(files==null) return ;
			for (String fileName : files) {
				noticeDAO.replaceAttcah(fileName, n_no);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteNotice(int n_no) {
		try {
			noticeDAO.deleteAttach(n_no);
			noticeDAO.deleteNotice(n_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public int countNotice(SearchCriteria cri) {
		int result = -1;
		try {
			result=noticeDAO.countNotice(cri);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public PageMaker getPageMaker(SearchCriteria cri)
	{
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(countNotice(cri));
		
		return pageMaker;
	}
	public List<String> getAttach(int n_no){
		List<String> list=null;
		try {
			list=noticeDAO.getAttach(n_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void deleteFile(String f_name) {
		try {
			noticeDAO.deleteFile(f_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
