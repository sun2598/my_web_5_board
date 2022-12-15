package kh.s0.myboard.member.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kh.s0.myboard.member.model.MemberService;
import kh.s0.myboard.member.model.MemberVo;
import kh.s0.myboard.member.model.TestJsonVo;

/**
 * Servlet implementation class LoginLoController
 */
@WebServlet("/login.lo") // jsp파일의 ajax url과 동일
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

		Gson gson = new Gson();
		
		
		String mid = request.getParameter("mid");
		String mpwd = request.getParameter("mpwd");
		
		MemberService service = new MemberService();
		MemberVo loginInfo = service.login(mid, mpwd);
		
		if(loginInfo == null) {
			System.out.println("로그인 실패. id, pwd를 다시 입력해해주세요");
//			out.println("fail"); --> append, print는 닫아줘야함. json이 인식 못함
		} else {
			if(loginInfo.getMauthcode().equals("1")) {
				System.out.println("로그인 성공");
				request.getSession().setAttribute("loginSsInfo", loginInfo);
//				out.println("ok");
			} else if (loginInfo.getMauthcode().equals("0")) {
				System.out.println("로그인 인증 전");
//				out.println("authcode0");
			} else {
				System.out.println("이메일 인증을 완료하세요");
//				out.println("authcode1");
			}
		}
		
		
		String result = gson.toJson(loginInfo); // MemberVo 타입을 json 모양으로 변환 -> javascript에서 인식 가능하게끔
		System.out.println(result);
		
		out.println(result);
		
		out.flush();
		out.close(); // 여기까지 해줘야 ajax 전송 완료
		
		
		
//----------------------------------------------------------------------------------------------------		
		// TODO 임시코드 - json test를 위해
		BufferedReader br = request.getReader();
		String requestData = br.readLine();
//		System.out.println(requestData); // [{"pno":21,"amount":2},{"pno":31,"amount":7},{"pno":41,"amount":3}] 이걸 읽어와서 출력함
		
		// gson 예전 버전에서 지원하던 방식
//		ArrayList<TestJsonVo> testjsonlist = gson.fromJson(requestData.toString(), new TypeToken<ArrayList<TestJsonVo>>.getClass());
		// 현재버전방식
		TestJsonVo[] testjsonArray = gson.fromJson(requestData, TestJsonVo[].class); // fromJson : json -> 여기선 배열 형태로 변환
//		for(TestJsonVo vo : testjsonArray) {
//			System.out.println(vo);
//		}
//		System.out.println(testjsonArray);
		List<TestJsonVo> testjsonlist = Arrays.asList(testjsonArray); // 배열에서 컬렉션(list)으로 변환
		
//		String mid = "user1";
//		String mpwd = "user1";
		
//----------------------------------------------------------------------------------------------------		
	}

}
