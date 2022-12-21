package kh.s0.myboard.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.s0.myboard.board.model.BoardService;
import kh.s0.myboard.board.model.BoardVo;
import kh.s0.myboard.member.model.MemberVo;

/**
 * Servlet implementation class BoardWriteDoController
 */
@WebServlet("/board/write.do")
public class BoardWriteDoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardWriteDoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 로그인이 안되어있을때 글쓰기 하려 한다면, 로그인 후 글쓰기 하도록 유도
		MemberVo loginSsInfo = (MemberVo)request.getSession().getAttribute("loginSsInfo"); 
		String bwriter = null;
		
		if(loginSsInfo == null) { // 로그인 안한 상태
			response.sendRedirect(request.getContextPath()+"/login");
			return; // 밑의 코드 수행 안하게끔
		} else { // 로그인 한 상태
			bwriter = loginSsInfo.getMid();
		}
		
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		
		BoardService service = new BoardService();
		BoardVo vo = new BoardVo();
		vo.setBtitle(btitle);
		vo.setBcontent(bcontent);
		vo.setBwriter(bwriter);
		
		int result = service.insert(vo);
		
		if(result > 0) { // 작성 성공시
			
//			request.setAttribute("result", "게시글이 등록되었습니다."); --> 이렇게 하고싶을때 
			// 방법 1
			// session의 유지기간 lifeCycle : 브라우저 창 닫힘. removeAttribute() / invalidate()
			// session은 contextPath 단위로 관리가 이루어짐 -> 장기 기억
			request.getSession().setAttribute("result", "게시글이 등록되었습니다."); 
			// 방법 2
			// web page로 일회성 전달 방법 -> 단기 기억
			
			
			response.sendRedirect(request.getContextPath()+"/board/list");
		} else { // 작성 실패시
//			response.sendRedirect(request.getContextPath()+"/board/write");
			request.setAttribute("msg", "게시글 등록에 실패하였습니다. 다시 글쓰기를 시도해주세요.");
			request.getRequestDispatcher("/WEB-INF/view/error/alert.jsp").forward(request, response);
		}
	}

}
