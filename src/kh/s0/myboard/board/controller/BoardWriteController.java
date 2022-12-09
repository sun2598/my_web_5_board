package kh.s0.myboard.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.s0.myboard.board.model.BoardService;
import kh.s0.myboard.board.model.BoardVo;

/**
 * Servlet implementation class BoardWriteController
 */
@WebServlet("/board/write")
public class BoardWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardWriteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("BoardWriteController");
		// DB 안갔다옴
		// view/url 변화함
		String viewPath = "/WEB-INF/view/boardwrite.jsp";
		request.getRequestDispatcher(viewPath).forward(request, response);
		
		
		// 페이지 이동 방법 2개
//		response.sendRedirect("대부분99%/url"); -> 이건 url이 바뀌는거
//		request.getRequestDispatcher("대부분99%/WEB-INF/view/xxx.jsp").forward(request, response); --> 이건 url은 그대로, jsp파일이 열리는거
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//	}

}
