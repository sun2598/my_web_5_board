package kh.s0.myboard.board.model;

import java.sql.Connection;
import java.util.List;

import common.jdbc.JdbcTemplate;



public class BoardService {
	
	private BoardDao dao = new BoardDao();

// 최소 5개 CRUD
	
	// insert
	public int insert(BoardVo vo) {
		System.out.println(">>BoardService Param :"+ vo);
		int result = 0;  // insert, 0오류, 1정상
		int result2 = 0;  // update, -1오류, 0 이상 정상
		Connection conn = JdbcTemplate.getConnection();
		JdbcTemplate.setAutoCommit(conn, false);
		if(vo.getBno() != 0) {
			// 답글로 인식
			result2 = dao.updateForInsert(conn, vo);
			if(result2 >= 0)
				result = dao.insert(conn, vo);
		}else {
			// 원본글로 인식
			result = dao.insert(conn, vo);
		}
		if(result > 0) {
			JdbcTemplate.commit(conn);
		}else {
			JdbcTemplate.rollback(conn);
		}
		JdbcTemplate.close(conn);
		System.out.println(">>BoardService Return:"+ result);
		return result;
	}
	// update
	public int update(BoardVo vo, int bno /*여기에는 주로 기본키가 들어감*/) {
		int result = 0;
		Connection conn = JdbcTemplate.getConnection();
		
		result = dao.update(conn, vo, bno);
		
		JdbcTemplate.close(conn);
		return result;
	}
	// delete
	public int delete(int bno /*여기에는 주로 기본키가 들어감*/) {
		int result = 0;
		Connection conn = JdbcTemplate.getConnection();
		
		result = dao.delete(conn, bno);
		
		JdbcTemplate.close(conn);
		return result;
	}
	// selectList
	public List<BoardVo> selectList() {
		List<BoardVo> volist = null;
		Connection conn = JdbcTemplate.getConnection();
		
		volist = dao.selectList(conn);
		
		JdbcTemplate.close(conn);
		return volist;
	}
	// selectList - overloading
	public List<BoardVo> selectList(int startRnum, int endRnum, String searchword) {
		List<BoardVo> volist = null;
		Connection conn = JdbcTemplate.getConnection();
		
		volist = dao.selectList(conn, startRnum, endRnum, searchword);
		
		JdbcTemplate.close(conn);
		return volist;
	}
	// selectOne
	public BoardVo selectOne(int bno /*여기에는 주로 기본키가 들어감*/) {
		BoardVo vo = null;
		Connection conn = JdbcTemplate.getConnection();
		
		vo = dao.selectOne(conn, bno);
		
		JdbcTemplate.close(conn);
		return vo;
	}
	// 게시글 총 개수 
	public int selectTotalCnt() {
		int result = 0;
		Connection conn = JdbcTemplate.getConnection();
		
		result = dao.selectTotalCnt(conn);
		
		JdbcTemplate.close(conn);
		return result;
	}
}
