package kh.s0.myboard.board.controller;

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
 * Servlet implementation class BoardList
 */
@WebServlet("/board/list")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("boardlistcontroller");
		BoardService service = new BoardService();
		List<BoardVo> volist = service.selectList();
		System.out.println(volist);
	
		// request.setAttribute() -> forward 방식만 사용 가능
		request.setAttribute("aaa", volist); // aaa라는 String에 volist를 대입(?)해줌 -> jsp파일에서 volist를 호출하기 위해 aaa 사용 가능
		String viewPath = "/WEB-INF/view/boardlist_jstl_el.jsp"; // 이부분만 계속 바꿔서 사용하면됨. 여기엔 <%=request.getContextPath()%> 작성 안함 (forward방식은 애초에 contextpath가 유지가 됨)
		request.getRequestDispatcher(viewPath).forward(request, response);
		
		
		
		// 페이지 이동 방법 2개
//		response.sendRedirect("대부분99%/url"); -> 이건 url이 바뀌는거 (데이터 전달 불가)
//		request.getRequestDispatcher("대부분99%/WEB-INF/view/xxx.jsp").forward(request, response); --> 이건 url은 그대로, jsp파일이 열리는거 (데이터 전달 가능)
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
