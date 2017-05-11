package junit.in.action.ch8;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SampleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public boolean isAuthenticated(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return false;
		}
		
		String authenticationAttribute =
				(String) session.getAttribute("authenticated");
		return Boolean.valueOf(authenticationAttribute).booleanValue();
	}

}
