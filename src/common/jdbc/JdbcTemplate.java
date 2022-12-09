package common.jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JdbcTemplate {

	public static Connection getConnection() {
		
		Connection conn = null;
		Properties prop = new Properties();
		
		try {
			String currentPath = JdbcTemplate.class.getResource("./").getPath(); // 이 클래스의 경로를 currentPath에 담기
			System.out.println(currentPath);
			prop.load(new BufferedReader(new FileReader(currentPath+"driver.properties"))); 
													// (여기+properties파일 경로)
			
			Class.forName(prop.getProperty("db.driver"));
			conn = DriverManager.getConnection(prop.getProperty("db.url"),
												prop.getProperty("db.user"),
												prop.getProperty("db.pwd"));
			if(conn != null) {
				System.out.println("DB 연결 성공");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
		
	}
	
	// 닫기
	public static void close(Connection conn) {
		try {
			if(conn!=null && !conn.isClosed()) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void close(Statement stmt) {
		try {
			if(stmt!=null && !stmt.isClosed()) stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void close(ResultSet rs) {
		try {
			if(rs!=null && !rs.isClosed()) rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 커밋, 롤백
	public static void commit(Connection conn) {
		try {
			if(conn!=null && !conn.isClosed()) conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void rollback(Connection conn) {
		try {
			if(conn!=null && !conn.isClosed()) conn.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
