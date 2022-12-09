package kh.s0.myboard.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.s0.myboard.member.model.BusinessInfoService;
import kh.s0.myboard.member.model.BusinessInfoVo;

/**
 * Servlet implementation class BusinessInfoSelectOneController
 */
@WebServlet("/BusinessInfoSelectOneController")
public class BusinessInfoSelectOneController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BusinessInfoSelectOneController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberSelectOnBusinessInfoSelectOneControllereController");
		
		String busno = ""; //TODO
		BusinessInfoService service = new BusinessInfoService();
		BusinessInfoVo vo = service.selectOne(busno);
		System.out.println(vo);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	}

}
