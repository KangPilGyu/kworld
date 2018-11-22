package org.web.kworld.news.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.web.kworld.news.dao.NewsDAO;
import org.web.kworld.news.vo.NewsVO;

public class NewsService {

	private NewsDAO newsDAO; 

	/*
	public NewsDAO getNewsDAO() {
		
		return newsDAO;
	}
	*/
	
	
	
	//조회수 증가
	@Transactional(propagation=Propagation.REQUIRED)//두가지 동시에 하는거
	public NewsVO getNewsDAO(int n_no) {
		NewsVO newsVo = null;
	
		try {
			newsDAO.updateCnt(n_no);
			newsVo = newsDAO.selectOne(n_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newsVo;

	}
	public void setNewsDAO(NewsDAO newsDAO) {
		this.newsDAO = newsDAO;
	}
	
	public NewsVO getSelectOne(int n_no) {
		NewsVO newsVo = null;
	
		try {
			newsVo = newsDAO.newsOne(n_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return newsVo;		
	}
	
	

	public List<NewsVO> selectList(){
		List<NewsVO> rlist = null;
		try {
			rlist = newsDAO.selectNews();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rlist;
	}
	
	
	public void updateNews(NewsVO vo){
		try {
			newsDAO.updateNews(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	public void newsInsert(NewsVO vo){
		try {
			newsDAO.newsInsert(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void newsDelete(NewsVO vo) {
		try {
			newsDAO.newsDelete(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
