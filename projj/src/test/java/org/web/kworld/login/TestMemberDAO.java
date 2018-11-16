package org.web.kworld.login;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.web.kworld.login.dao.MemberDAO;
import org.web.kworld.login.vo.MemberVO;
import org.web.kworld.news.dao.NewsDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations= {"file:src/main/webapp/WEB-INF/spring/**/*.xml"}
		)
public class TestMemberDAO {

	@Inject
	private SqlSession sqlSession;
	
	@Inject
	private NewsDAO newsDAO = null;

	@Test
	public void testInsert() throws Exception{
		System.out.println(sqlSession);
		System.out.println(">>> " + newsDAO);
		newsDAO.selectNews();
    }

	@Test
	public void testFindEmail() throws Exception{
			sqlSession.selectList("org.web.kworld.news.NewsMapper.selectNews");
	}

	
	
	@Test
	public void testFindEmail1() throws Exception{
		
		//newsDAO.selectNews();
		
	}
}
