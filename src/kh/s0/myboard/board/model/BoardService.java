package kh.s0.myboard.board.model;

import java.sql.Connection;
import java.util.List;

import common.jdbc.JdbcTemplate;

public class BoardService {
	
	private BoardDao dao = new BoardDao();

// 최소 5개 CRUD
	
	// insert
	public int insert(BoardVo vo) {
		Connection conn = JdbcTemplate.getConnection();
		int result = 0;
		result = dao.insert(conn, vo);
		JdbcTemplate.close(conn); // 리턴하기 전에 닫아줌
		return result;
	}
	// update
	public int update(BoardVo vo, int bno /*여기에는 주로 기본키가 들어감*/) {
		Connection conn = JdbcTemplate.getConnection();
		int result = 0;
		result = dao.update(conn, vo, bno);
		JdbcTemplate.close(conn);
		return result;
	}
	// delete
	public int delete(int bno /*여기에는 주로 기본키가 들어감*/) {
		Connection conn = JdbcTemplate.getConnection();
		int result = 0;
		result = dao.delete(conn, bno);
		JdbcTemplate.close(conn);
		return result;
	}
	// selectList
	public List<BoardVo> selectList() {
		Connection conn = JdbcTemplate.getConnection();
		List<BoardVo> volist = null;
		volist = dao.selectList(conn);
		JdbcTemplate.close(conn);
		return volist;
	}
	// selectOne
	public BoardVo selectOne(int bno /*여기에는 주로 기본키가 들어감*/) {
		Connection conn = JdbcTemplate.getConnection();
		BoardVo vo = null;
		vo = dao.selectOne(conn, bno);
		JdbcTemplate.close(conn);
		return vo;
	}
}
