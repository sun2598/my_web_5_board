package first.loginSv.filter;

import java.io.IOException;
import first.loginSv.wrapper.LoginWrapper;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class MyFilter implements Filter {
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("MyFilter.init() 호출~!");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest h_request = (HttpServletRequest) request; // ServletRequest -> HttpServletRequest 형변환
		
		h_request.setAttribute("pwdChk", h_request.getParameter("pwd")); // pwdChk라는 속성에 h_request.getParameter("pwd")를 담음
		
		LoginWrapper lw = new LoginWrapper(h_request); // h_request : LoginWrapper 클래스에서 override 메소드 적용된 객체.
		
		chain.doFilter(lw, response);
		// 원래 lw에 request가 있어야 함. 그러나 request에 pwdChk라는 속성을 추가해야했기 때문에 
	}

	@Override
	public void destroy() {
		System.out.println("Myfilter.destroy() 호출!!");
	}
}
