package org.web.kworld.db;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.junit.Test;

public class MySQLConnectionTest {
	/*
	@Test
	public void testMySQLConnectionTest() throws Exception{
		Properties properties = new Properties();
		String filePath ="C:\\Class\\newPro\\projj\\src\\main\\resources\\properties\\dataSource.properties";
		try(FileReader fis = new FileReader(filePath)) {
			properties.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String driver=properties.getProperty("datasource.driverClassName");
		String url=properties.getProperty("datasource.url");
		String username=properties.getProperty("datasource.username");
		String password=properties.getProperty("datasource.password");
		
		Class.forName(driver);
		try(Connection con = DriverManager.getConnection(url, username, password)){
			System.out.println(con);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	*/
}
