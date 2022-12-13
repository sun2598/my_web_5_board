package kh.s0.myboard.member.model;

import java.sql.Connection;
import java.util.List;

import common.jdbc.JdbcTemplate;

public class MemberService {
	
	private MemberDao dao = new MemberDao();
	
	// insert
	public int insert(MemberVo vo) {
		int result = 0;
		Connection conn = JdbcTemplate.getConnection();
		result = dao.insert(conn, vo);
		JdbcTemplate.close(conn); // 리턴하기 전에 닫아줌
		return result;
	}
	// update
	public int update(MemberVo vo, String mid /*여기에는 주로 기본키가 들어감*/) {
		int result = 0;
		Connection conn = JdbcTemplate.getConnection();
		result = dao.update(conn, vo, mid);
		JdbcTemplate.close(conn);
		return result;
	}
	// delete
	public int delete(String mid /*여기에는 주로 기본키가 들어감*/) {
		int result = 0;
		Connection conn = JdbcTemplate.getConnection();
		result = dao.delete(conn, mid);
		JdbcTemplate.close(conn);
		return result;
	}
	// selectList : 게시글 목록 조회 (리턴 모양이 중요!)
	public List<MemberVo> selectList() {
		List<MemberVo> volist = null;
		Connection conn = JdbcTemplate.getConnection();
		volist = dao.selectList(conn);
		JdbcTemplate.close(conn);
		return volist;
	}
	// selectOne : 게시글 하나 상세 조회
	public MemberVo selectOne(String mid /*여기에는 주로 기본키가 들어감*/) {
		MemberVo vo = null;
		Connection conn = JdbcTemplate.getConnection();
		vo = dao.selectOne(conn, mid);
		JdbcTemplate.close(conn);
		return vo;
	}
	// selectOne - login
	public MemberVo login(String mid, String mpwd) {
		MemberVo vo = null;
		Connection conn = JdbcTemplate.getConnection();
		vo = dao.login(conn, mid, mpwd);
		JdbcTemplate.close(conn);
		return vo;
	}
	
	
	
	
	
	
	
	
	
	
	
}
