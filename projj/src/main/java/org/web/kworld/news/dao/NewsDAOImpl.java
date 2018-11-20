package org.web.kworld.news.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.web.kworld.news.vo.NewsVO;

import com.google.inject.Inject;


//DAO를 뜻한다 빈객체를 만들었따
//@Repository
public class NewsDAOImpl implements NewsDAO{
	
	//@/Inject//주입
	private SqlSession sqlSession;
	
	//org.web.kworld.news.NewsMapper.selectNews
	
	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	private static final String NAMESPACE="org.web.kworld.news.NewsMapper";

	@Override
	public List<NewsVO> selectNews() throws Exception {
		return sqlSession.selectList( NAMESPACE + ".selectNews");
	}

	@Override
	public int count() throws Exception {
		return sqlSession.selectOne( NAMESPACE + ".count");
	}

	@Override
	public NewsVO newsOne(int n_no) throws Exception {
		return sqlSession.selectOne(NAMESPACE+".newsOne",n_no);
	}

	@Override
	public void updateCnt(int n_no) throws Exception {
		
	}

	@Override
	public NewsVO selectOne(int n_no) throws Exception {
		return sqlSession.selectOne(NAMESPACE+".selectOne");
	}

	@Override
	public void updateNews(NewsVO vo) throws Exception {
		sqlSession.update(NAMESPACE+".updateNews");
		
	}
	
	
	
}
