package org.web.kworld.news.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.web.kworld.news.dao.NewsDAO;
import org.web.kworld.news.vo.NewsVO;

import com.google.inject.Inject;

@Service
public class NewsService {
	@Inject
	private NewsDAO newsDAO; 
	
	public List<NewsVO> selectList(){
		List<NewsVO> rlist = null;
		try {
			rlist = newsDAO.selectNews();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(rlist.get(0).getN_title());
		return rlist;
	};
}
