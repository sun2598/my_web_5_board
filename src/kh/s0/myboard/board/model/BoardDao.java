package kh.s0.myboard.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.jdbc.JdbcTemplate;

public class BoardDao {

// 최소 5개 CRUD
	
	// insert
	public int insert(Connection conn, BoardVo vo) {
		System.out.println(">>>>BoardDao Param : " + vo);
		int result = 0;
		
//		--원글 
//		INSERT INTO BOARD (BNO, BTITLE, BCONTENT, BREF, BRELEVEL, BRESTEP, BWRITER, BTYPE)
//		VALUES ((SELECT NVL(MAX(BNO),0)+1 FROM BOARD), '&NUMBER TITLE','CONTENT', (SELECT NVL(MAX(BNO),0)+1 FROM BOARD), 0, 1, 'EJKIM', 'A')
//		;
//		-- 답글 : 경우는 INSERT 전에 UPDATE 먼저 수행하여 STEP 수정을 함.
//		UPDATE BOARD SET BRESTEP = BRESTEP+1 
//		WHERE BREF = (SELECT BREF FROM BOARD WHERE BNO='&답글번호')
//		AND BRESTEP > (SELECT BRESTEP FROM BOARD WHERE BNO='&답글번호')
//		;
//		INSERT INTO BOARD (BNO, BTITLE, BCONTENT, BREF, BRELEVEL, BRESTEP, BWRITER, BTYPE)
//		VALUES ((SELECT NVL(MAX(BNO),0)+1 FROM BOARD), '&NUMBER TITLE','CONTENT', 
//		    (SELECT BREF FROM BOARD WHERE BNO='&답글번호'), 
//		    (SELECT BRELEVEL FROM BOARD WHERE BNO='&답글번호')+1, 
//		    (SELECT BRESTEP FROM BOARD WHERE BNO='&답글번호')+1,
//		    'EJKIM', 'A')
//		;
		
		String sql = "INSERT INTO BOARD (BNO, BTITLE, BCONTENT, BREF, BRELEVEL, BRESTEP, BWRITER, BTYPE)";
		sql += "VALUES ((SELECT NVL(MAX(BNO),0)+1 FROM BOARD), ?,?, (SELECT NVL(MAX(BNO),0)+1 FROM BOARD), 0, 1, ?, 'A')";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getBtitle());
			pstmt.setString(2, vo.getBcontent());
			pstmt.setString(3, vo.getBwriter());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>>>BoardDao Return : " + result);
		return result;
	}
	
	// update
	public int update(Connection conn, BoardVo vo, int bno /*여기에는 주로 기본키가 들어감*/) {
		int result = 0;
		
		String sql = "update board set BTITLE=? BCONTENT=? where bno=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getBtitle());
			pstmt.setString(2, vo.getBcontent());
			pstmt.setInt(3, bno);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		return result;
	}
	
	// delete
	public int delete(Connection conn, int bno /*여기에는 주로 기본키가 들어감*/) {
		int result = 0;
		
		String sql = "delete from board where bno=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		return result;
	}
	
	// selectList : 게시글 목록 조회 (리턴 모양이 중요!)
	public List<BoardVo> selectList(Connection conn) {
		List<BoardVo> volist = null;
		
		String sql = "select * from board";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// TODO
			if (rs.next()) {
				volist = new ArrayList<BoardVo>();
				do {
					BoardVo vo = new BoardVo();
					vo.setBcnt(rs.getInt("bcnt"));
					vo.setBcontent(rs.getString("bcontent"));
					vo.setBdate(rs.getTimestamp("bdate"));
					vo.setBno(rs.getInt("bno"));
					vo.setBref(rs.getInt("bref"));
					vo.setBrelevel(rs.getInt("brelevel"));
					vo.setBrestep(rs.getInt("brestep"));
					vo.setBtitle(rs.getString("btitle"));
					vo.setBtype(rs.getString("btype"));
					vo.setBwriter(rs.getString("bwriter"));
					volist.add(vo);
				} while(rs.next());
			}
			
			// while 사용하는 방법. 비추
//			volist = new ArrayList<BoardVo>(); 
//			while (rs.next()) {
//					BoardVo vo = new BoardVo();
//					vo.setBcnt(rs.getInt("bcnt"));
//					vo.setBcontent(rs.getString("bcontent"));
//					vo.setBdate(rs.getTimestamp("bdate"));
//					vo.setBno(rs.getInt("bno"));
//					vo.setBref(rs.getInt("bref"));
//					vo.setBrelevel(rs.getInt("brelevel"));
//					vo.setBrestep(rs.getInt("brestep"));
//					vo.setBtitle(rs.getString("btitle"));
//					vo.setBtype(rs.getString("btype"));
//					vo.setBwriter(rs.getString("bwriter"));
//					volist.add(vo);
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
		return volist;
	}
	
	// selectOne : 게시글 하나 상세 조회
	public BoardVo selectOne(Connection conn, int bno /*여기에는 주로 기본키가 들어감*/) {
		BoardVo vo = null;
		
		String sql = "select * from board where bno=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo = new BoardVo();
				vo.setBcnt(rs.getInt("bcnt"));
				vo.setBcontent(rs.getString("bcontent"));
				vo.setBdate(rs.getTimestamp("bdate"));
				vo.setBno(rs.getInt("bno"));
				vo.setBref(rs.getInt("bref"));
				vo.setBrelevel(rs.getInt("brelevel"));
				vo.setBrestep(rs.getInt("brestep"));
				vo.setBtitle(rs.getString("btitle"));
				vo.setBtype(rs.getString("btype"));
				vo.setBwriter(rs.getString("bwriter"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
		return vo;
	}
}
