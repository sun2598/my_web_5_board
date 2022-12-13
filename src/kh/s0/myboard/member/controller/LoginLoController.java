package kh.s0.myboard.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.s0.myboard.member.model.MemberService;
import kh.s0.myboard.member.model.MemberVo;

/**
 * Servlet implementation class LoginLoController
 */
@WebServlet("/LoginLoController")
public class LoginLoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginLoController() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		response.sendRedirect(request.getContextPath()+"/main"); 
//		--> ajax인 경우 사용하지 않음 (sendRedirect방식, forward 방식 모두 사용불가)
//			왜? 이미 갈 곳이 정해져있음. ajax의 콜백함수로 돌아가서 데이터 전달해줌 
//		ajax에서는 페이지 이동 코드를 작성하지 않음!! 데이터만 전달해줌
		
		PrintWriter out = response.getWriter();
		out.append("a");
		out.println("b");
		
		
		
		String mid = request.getParameter("mid");
		String mpwd = request.getParameter("mpwd");
		
		MemberService service = new MemberService();
		MemberVo loginInfo = service.login(mid, mpwd);
		
		if(loginInfo == null) {
			System.out.println("로그인 실패. id, pwd를 다시 입력해해주세요");
			out.println("fail");
		} else {
			if(loginInfo.getMauthcode().equals("1")) {
				System.out.println("로그인 성공");
				request.getSession().setAttribute("loginSsInfo", loginInfo);
				out.println("ok");
			} else if (loginInfo.getMauthcode().equals("0")) {
				System.out.println("로그인 인증 전");
				out.println("authcode0");
			} else {
				System.out.println("이메일 인증을 완료하세요");
				out.println("authcode1");
			}
		}
		
		out.flush();
		out.close(); // 여기까지 해줘야 ajax 전송 완료
	}

}
