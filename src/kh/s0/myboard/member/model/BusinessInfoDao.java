package kh.s0.myboard.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.jdbc.JdbcTemplate;

public class BusinessInfoDao {
	
	// insert
	public int insert(Connection conn, BusinessInfoVo vo) {
		System.out.println(">>>>BusinessInfoDao Param : " + vo);
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
		System.out.println(">>>>BusinessInfoDao Return : " + result);
		return result;
	}
	
	// update
	public int update(Connection conn, BusinessInfoVo vo, String busno /*여기에는 주로 기본키가 들어감*/) {
		int result = 0;
		
		String sql = "update board set busname=? busceoname=? where busno=?"; // TODO
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getBusname());
			pstmt.setString(2, vo.getBusceoname());
			pstmt.setString(3, busno);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		return result;
	}
	
	// delete
	public int delete(Connection conn, String busno /*여기에는 주로 기본키가 들어감*/) {
		int result = 0;
		
		String sql = "delete from business_info where busno=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, busno);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		return result;
	}
	
	// selectList : 게시글 목록 조회 (리턴 모양이 중요!)
	public List<BusinessInfoVo> selectList(Connection conn) {
		List<BusinessInfoVo> volist = null;

		String sql = "select * from business_info";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// TODO
			if (rs.next()) {
				volist = new ArrayList<BusinessInfoVo>();
				do {
					BusinessInfoVo vo = new BusinessInfoVo();
					vo.setBusceoname(rs.getString("busceoname"));
					vo.setBuscertification(rs.getString("buscertification"));
					vo.setBusname(rs.getString("busname"));
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
	public BusinessInfoVo selectOne(Connection conn, String busno /*여기에는 주로 기본키가 들어감*/) {
		BusinessInfoVo vo = null;
		
		String sql = "select * from business_info where busno=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, busno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo = new BusinessInfoVo();
				
				vo.setBusceoname(rs.getString("busceoname"));
				vo.setBuscertification(rs.getString("buscertification"));
				vo.setBusname(rs.getString("busname"));
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
