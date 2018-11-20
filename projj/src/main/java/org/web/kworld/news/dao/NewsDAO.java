package org.web.kworld.news.dao;

import java.util.List;

import org.web.kworld.news.vo.NewsVO;

public interface NewsDAO {
	
	public List<NewsVO> selectNews() throws Exception;
	
	public int count() throws Exception;
	
	NewsVO newsOne(int n_no) throws Exception;
	
	public void updateCnt(int n_no) throws Exception;
	
	public NewsVO selectOne(int n_no) throws Exception;
	
	public void updateNews(NewsVO vo) throws Exception;
	
}
