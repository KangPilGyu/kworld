package org.web.kworld.news.dao;

import java.util.List;

import org.web.kworld.news.vo.NewsVO;

public interface NewsDAO {
	
	public List<NewsVO> selectNews() throws Exception;
	
	
	
}
