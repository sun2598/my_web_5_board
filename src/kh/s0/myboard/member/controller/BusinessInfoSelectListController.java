package kh.s0.myboard.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.s0.myboard.member.model.BusinessInfoService;
import kh.s0.myboard.member.model.BusinessInfoVo;

/**
 * Servlet implementation class BusinessInfoSelectListController
 */
@WebServlet("/BusinessInfoSelectListController")
public class BusinessInfoSelectListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BusinessInfoSelectListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("BusinessInfoSelectListController");
		
		BusinessInfoService service = new BusinessInfoService();
		List<BusinessInfoVo> volist = service.selectList();
		System.out.println(volist);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	}

}
