package org.web.kworld.login;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.web.kworld.login.dao.MemberDAO;
import org.web.kworld.login.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations= {"file:src/main/webapp/WEB-INF/spring/**/*.xml"}
		)
public class TestMemberDAO {
	/*@Inject
	private MemberDAO memberDAO;
	
	@Test
	public void testInsert() throws Exception{
		MemberVO vo = new MemberVO();
		vo.setM_name("testName");
		vo.setM_email("test@test.com");
		
		memberDAO.insertMember(vo);
	}
	@Test
	public void testFindEmail() throws Exception{
		
		int str = memberDAO.findEmail("eiffeltop01@gmail.com");
		System.out.println( str);
	}*/
}
