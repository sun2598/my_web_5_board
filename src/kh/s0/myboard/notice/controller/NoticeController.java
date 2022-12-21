package kh.s0.myboard.notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.s0.myboard.board.model.BoardService;
import kh.s0.myboard.board.model.BoardVo;

/**
 * Servlet implementation class NoticeController
 */
@WebServlet("/notice")
public class NoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardService service = new BoardService();
		
		// <내가 정하는 고정개수>
		final int pageSize = 2; // 페이지당 글 수
		final int pageBlock = 3; // 페이지 링크 수  (예) 게시글하단에 1 2 3 >> << 4 5 6 >> << 7 8
		
		// <DB에서 불러와야하는 수>
		int cnt = 0; // 총 글 수
		int pageCnt = 0; // 총 페이지 수
		int currentPage = 1; // 현재페이지. 기본 1. 눌리면 바뀜
		int startPage = 1; // 화면에 나타날 시작페이지
		int endPage = 1; // 화면에 나타날 마지막페이지
		
		String searchword = request.getParameter("search");
		
		try {
			cnt = service.selectTotalCnt();
			
			if(cnt < 1) { // 게시글 없는 경우. -> 아래 게시글 selectList 할 필요 없음.
				return;
			}
			
			try {
				currentPage = Integer.parseInt(request.getParameter("pagenum"));
			} catch (Exception e) {
			}
			pageCnt = (cnt/pageSize) + (cnt%pageSize==0 ? 0 : 1); // 총 페이지 수
			
			if(currentPage % pageBlock == 0) {
				startPage = ((currentPage / pageBlock) - 1) * pageBlock + 1;
			} else {
				startPage = (currentPage / pageBlock) * pageBlock + 1;
			}
			endPage = startPage + pageBlock - 1;
			
			if(endPage > pageCnt) {
				endPage = pageCnt;
			}
			
			int startRnum = (currentPage - 1) * pageSize + 1;
			int endRnum = startRnum + pageSize - 1;
			if(endRnum > cnt ) {
				endRnum = cnt;
			}
			
			
			List<BoardVo> boardlist = new BoardService().selectList(startRnum, endRnum, searchword);
			request.setAttribute("boardlist", boardlist);
			
		} finally { // finally문은 무조건 거치게됨
			
			// 페이징버튼 눌렀을때 searchword가 날라가지않게
			if(searchword != null && !searchword.equals("")) {
				request.setAttribute("searchword", searchword);
			}
			request.setAttribute("pageCnt", pageCnt);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("currentPage", currentPage);
			
			String viewPath = "WEB-INF/view/notice.jsp";
			request.getRequestDispatcher(viewPath).forward(request, response);
		}
	}


}
