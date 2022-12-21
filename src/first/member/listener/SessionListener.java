package first.member.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import kh.s0.myboard.member.model.MemberVo;

public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener {
	
	/* 구현해야할 메소드 중 사용하는 부분만 표현하였음 */
	
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		String name = null;
		if(event.getSession().getAttribute("loginSsInfo") != null) {
			name = ((MemberVo)event.getSession().getAttribute("loginSsInfo")).getMname();
		}
		System.out.println(event.getSession().getServletContext().toString());
		System.out.println(name + " 님이 접속하셨습니다.");
	}

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		System.out.println(event.getSession().getServletContext().toString());
		System.out.println(event.getSession().getId() + "세션이 생성되었습니다.");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		System.out.println(event.getSession().getServletContext().toString());
		System.out.println(event.getSession().getId() + "세션이 파괴되었습니다.");
	}
	
}