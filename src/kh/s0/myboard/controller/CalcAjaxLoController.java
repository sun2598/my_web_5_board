package kh.s0.myboard.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class CalcAjaxDoController
 */
@WebServlet("/test/calc.lo")
public class CalcAjaxLoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalcAjaxLoController() {
        super();
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//----------------------------------------------------------------------------------		
//		교재 학습용 테스트 코드 
		Enumeration<String> names = request.getParameterNames();
		while(names.hasMoreElements()) {
			String name = names.nextElement();
			System.out.println(request.getParameter(name)); // request.getParameter : 개수가 고정이 아닐때
		}
		
		String[] artistsArr = request.getParameterValues("artist"); // request.getParameterValues : {key,value}에서 value를 불러옴
		List<String> artistsList = Arrays.asList(artistsArr);
		
		Map<String, String[]> reqMap = request.getParameterMap(); // request.getParameterMap : client가 보낸 데이터를 Map 방식으로 불러옴
		System.out.println(reqMap);
		Set<String> keys = reqMap.keySet(); // keySet() : map -> set으로 바꿈
		for(String key : keys) {
			System.out.println("key"+key);
			for(String value : reqMap.get(key)) {
				System.out.println("value"+value);
			}
		}
		
//----------------------------------------------------------------------------------			
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		
		System.out.println("CalcAjaxLoController");
		
//		{"result" : "3+2=5"}
//		-> 이런 모양으로 Map 생성하여 key : value 형태를 구성함
		Map<String, String> map = new HashMap<String, String>();
		
		String msg = "";
		int num1 = 0;
		int num2 = 0;
		String oper = "";
		
		try {
			num1 = Integer.parseInt(request.getParameter("n1"));
			num2 = Integer.parseInt(request.getParameter("n2"));
			if(num2 == 0) {
				msg = "나누는 수가 0으로 계산할 수 없습니다.";
				return;
			}
			oper = request.getParameter("oper");
			switch(oper) {
			case "add":
				msg = String.format("%d + %d = %d", num1, num2, (num1+num2));
				break;
			case "sub":
				msg = String.format("%d - %d = %d", num1, num2, (num1-num2));
				break;
			case "mul":
				msg = String.format("%d * %d = %d", num1, num2, (num1*num2));
				break;
			case "div":
				msg = String.format("%d / %d = %d", num1, num2, ((double)num1/num2)); // double로 형변환
				break;
			}
		} catch(ArithmeticException e) {
			msg = "0으로 나눌 수 없습니다.";
		} catch(NumberFormatException e) {
			msg = "계산 값이 숫자가 아닙니다.";
		} finally {
			map.put("result", msg);
			out.println(gson.toJson(map));
			out.flush();
			out.close();
		}
		
 	}

}
