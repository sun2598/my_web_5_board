package kh.s0.myboard.member.model;

import java.sql.Connection;
import java.util.List;

import common.jdbc.JdbcTemplate;

public class BusinessInfoService {
	
	private BusinessInfoDao dao = new BusinessInfoDao();
	
	// insert
	public int insert(BusinessInfoVo vo) {
		int result = 0;
		Connection conn = JdbcTemplate.getConnection();
		result = dao.insert(conn, vo);
		JdbcTemplate.close(conn); // 리턴하기 전에 닫아줌
		return result;
	}
	// update
	public int update(BusinessInfoVo vo, String busno /*여기에는 주로 기본키가 들어감*/) {
		int result = 0;
		Connection conn = JdbcTemplate.getConnection();
		result = dao.update(conn, vo, busno);
		JdbcTemplate.close(conn);
		return result;
	}
	// delete
	public int delete(String busno /*여기에는 주로 기본키가 들어감*/) {
		int result = 0;
		Connection conn = JdbcTemplate.getConnection();
		result = dao.delete(conn, busno);
		JdbcTemplate.close(conn);
		return result;
	}
	// selectList : 게시글 목록 조회 (리턴 모양이 중요!)
	public List<BusinessInfoVo> selectList() {
		List<BusinessInfoVo> volist = null;
		Connection conn = JdbcTemplate.getConnection();
		volist = dao.selectList(conn);
		JdbcTemplate.close(conn);
		return volist;
	}
	// selectOne : 게시글 하나 상세 조회
	public BusinessInfoVo selectOne(String busno /*여기에는 주로 기본키가 들어감*/) {
		BusinessInfoVo vo = null;
		Connection conn = JdbcTemplate.getConnection();
		vo = dao.selectOne(conn, busno);
		JdbcTemplate.close(conn);
		return vo;
	}
}
