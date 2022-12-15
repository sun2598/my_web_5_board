package first.loginSv.wrapper;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Base64;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class LoginWrapper extends HttpServletRequestWrapper {
	
	public LoginWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override // 덮어쓰기!
	public String getParameter(String name) {
		if (name != null && name.equals("pwd"))
			name = getSha512(super.getParameter(name)); // SHA-512 암호화 메소드
		else
			name = super.getParameter(name);
		return name;
	}

	private static String getSha512(String pwd) {
		try { // SHA-512 암호화를 위한 로직 구현
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] bytes = pwd.getBytes(Charset.forName("UTF-8"));
			md.update(bytes);
			return Base64.getEncoder().encodeToString(md.digest());
		} catch (Exception e) {
			System.out.println("Sha512 error.");
			e.printStackTrace();
			return null;
		}
	}
}