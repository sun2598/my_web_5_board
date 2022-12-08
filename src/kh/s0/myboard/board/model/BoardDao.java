package kh.s0.myboard.board.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class BoardDao {

	// insert
	public int insert(Connection conn, BoardVo vo) {
		int result = 0;
		
		return result;
	}
	// update
	public int update(Connection conn, BoardVo vo, int bno /*여기에는 주로 기본키가 들어감*/) {
		int result = 0;
		
		return result;
	}
	// delete
	public int delete(Connection conn, int bno /*여기에는 주로 기본키가 들어감*/) {
		int result = 0;
		
		return result;
	}
	// selectList
	public List<BoardVo> selectList(Connection conn) {
		List<BoardVo> volist = null;
//		Statement stmt = conn.createStatement();
//		String sql = "select * from board";
//		ResultSet rs = stmt.executeQuery(sql);
//		while(rs.next()) {
//			
//		}
		return volist;
	}
	// selectOne
	public BoardVo selectOne(Connection conn, int bno /*여기에는 주로 기본키가 들어감*/) {
		BoardVo vo = null;
		
		return vo;
	}
}
