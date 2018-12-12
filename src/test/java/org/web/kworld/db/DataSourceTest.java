package org.web.kworld.db;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"}
		
		)
@WebAppConfiguration
public class DataSourceTest {
	/*@Inject
	private DataSource dataSource;
	
	@Test
	public void testConnection() throws Exception{
		try(Connection conn = dataSource.getConnection()){
			System.out.println(conn);
			System.out.println("dataSource Connection Sucess!!");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}
