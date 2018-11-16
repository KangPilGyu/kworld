package org.web.kworld.news.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.web.kworld.news.vo.NewsVO;

import com.google.inject.Inject;


//DAO를 뜻한다 빈객체를 만들었따
@Repository
public class NewsDAOImpl implements NewsDAO{
	
	@Inject//주입
	private SqlSession sqlSession;
	//org.web.kworld.news.NewsMapper.selectNews
	
	private static final String NAMESPACE="org.web.kworld.news.NewsMapper";

	@Override
	public List<NewsVO> selectNews() throws Exception {
		return sqlSession.selectList( NAMESPACE + ".selectNews");
	}
	
	
	
}
