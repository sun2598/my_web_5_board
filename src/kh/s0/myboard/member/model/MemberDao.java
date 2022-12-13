package kh.s0.myboard.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.jdbc.JdbcTemplate;

// Member + BusinessInfo 함께
public class MemberDao {
	
	// insert
	public int insert(Connection conn, MemberVo vo) {
		System.out.println(">>>>MemberDao Param : " + vo);
		int result = 0;
		
		String sql = ""; // TODO
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, vo.getBtitle());
//			pstmt.setString(2, vo.getBcontent());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>>>MemberDao Return : " + result);
		return result;
	}
	
	// update
	public int update(Connection conn, MemberVo vo, String mid /*여기에는 주로 기본키가 들어감*/) {
		int result = 0;
		
		String sql = "update board set mpwd=? memail=? where mid=?"; // TODO
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMpwd());
			pstmt.setString(2, vo.getMemail());
			pstmt.setString(3, mid);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		return result;
	}
	
	// delete
	public int delete(Connection conn, String mid /*여기에는 주로 기본키가 들어감*/) {
		int result = 0;
		
		String sql = "delete from member where mid=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		return result;
	}
	
	// selectList : 게시글 목록 조회 (리턴 모양이 중요!)
	public List<MemberVo> selectList(Connection conn) {
		List<MemberVo> volist = null;
		
		String sql = "select * from member";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// TODO
			if (rs.next()) {
				volist = new ArrayList<MemberVo>();
				do {
					MemberVo vo = new MemberVo();
					vo.setMauthcode(rs.getString("mauthcode"));
					vo.setMconsent1(rs.getInt("mconsent1"));
					vo.setMemail(rs.getString("memail"));
					vo.setMentrance(rs.getDate("mentrance"));
					vo.setMid(rs.getString("mid"));
					vo.setMname(rs.getString("mname"));
					vo.setMpwd(rs.getString("mpwd"));
					vo.setMtype(rs.getInt("mtype"));
					vo.setBusno(rs.getString("busno"));
					
					volist.add(vo);
				} while(rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
		return volist;
	}
	
	// selectOne : 게시글 하나 상세 조회
	public MemberVo selectOne(Connection conn, String mid /*여기에는 주로 기본키가 들어감*/) {
		MemberVo vo = null;
		
		String sql = "select * from member where mid=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo = new MemberVo();
				
				vo.setMauthcode(rs.getString("mauthcode"));
				vo.setMconsent1(rs.getInt("mconsent1"));
				vo.setMemail(rs.getString("memail"));
				vo.setMentrance(rs.getDate("mentrance"));
				vo.setMid(rs.getString("mid"));
				vo.setMname(rs.getString("mname"));
				vo.setMpwd(rs.getString("mpwd"));
				vo.setMtype(rs.getInt("mtype"));
				vo.setBusno(rs.getString("busno"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
		return vo;
	}
	
	// selectOne - login
	public MemberVo login(Connection conn, String mid, String mpwd) {
		MemberVo vo = null;
		
		// 로그인할때 session에 저장할 필요가 있는 항목만. ( * 쓰지 않음. 속도 저하의 원인)
		String sql = "select mid, mname, mauthcode, mtype, busno from member where mid=? and mpwd=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			pstmt.setString(2, mpwd);

			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo = new MemberVo();
				
				// 로그인할때 session에 저장할 필요가 있는 항목만
				vo.setMid(rs.getString("mid"));
				vo.setMname(rs.getString("mname"));
				vo.setMauthcode(rs.getString("mauthcode"));
				vo.setMtype(rs.getInt("mtype"));
				vo.setBusno(rs.getString("busno"));
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
