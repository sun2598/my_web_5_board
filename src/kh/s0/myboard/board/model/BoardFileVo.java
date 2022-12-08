package kh.s0.myboard.board.model;

public class BoardFileVo {
	
//	<BFILE 테이블>
//	<List> 타입 -> 여러개가 있을 수 있으니까.
//	BFILEPATH NOT NULL VARCHAR2(500) 
//	BNO       NOT NULL NUMBER  
	
	private String bfilepath;
	private int bno;
	
	
	
	// toString, 게터세터
	@Override
	public String toString() {
		return "BoardFileVo [bfilepath=" + bfilepath + ", bno=" + bno + "]";
	}
	
	public String getBfilepath() {
		return bfilepath;
	}
	public void setBfilepath(String bfilepath) {
		this.bfilepath = bfilepath;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}

}
