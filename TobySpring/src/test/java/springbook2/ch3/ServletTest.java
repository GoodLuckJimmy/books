package springbook2.ch3;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class ServletTest {
	
	@Test
	public void springMockTest() throws ServletException, IOException {
		MockHttpServletRequest req = new MockHttpServletRequest("GET", "/hello");
		req.addParameter("name", "Spring");
		
		MockHttpServletResponse res = new MockHttpServletResponse();
		
		SimpleGetServlet servlet = new SimpleGetServlet();
		servlet.service(req, res);
		
		assertThat(res.getContentAsString(), is("<HTML><BODY>Hello Spring</BODY></HTML>"));
		// 모든 contents를 비교할수는 없으니까 아래방식을 더 선호
		assertThat(res.getContentAsString().contains("Hello Spring"), is(true));
		
	}

}
