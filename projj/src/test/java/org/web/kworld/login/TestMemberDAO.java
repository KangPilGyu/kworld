package org.web.kworld.login;

import javax.inject.Inject;

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
	private NewsDAO newsDAO;
	
	@Test
	public void testInsert() throws Exception{
		System.out.println(newsDAO);
	}
	@Test
	public void testFindEmail() throws Exception{
		newsDAO.selectNews();
	}
}
