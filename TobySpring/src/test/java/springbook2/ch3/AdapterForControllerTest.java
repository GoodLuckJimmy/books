package springbook2.ch3;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.SimpleServletHandlerAdapter;

public class AdapterForControllerTest extends AbstractDispatcherServletTest {

	/*
	 *  Spring Adapter 종류
	 *  1. SimpleServletHandlerAdapter: 일반 서블릿을 빈으로 등록할때
	 *  2. HttpRequestHandlerAdapter: RMI기반할때 (p400참조)
	 *  3. SimpleControllerHandlerAdapter: 가장많이 사용하는 스프링 controller를 위한 adapter. Controller interface를 직접 구현한 컨트롤러보단
	 *  필수기능이 구현된 Controller 인터페이스를 상속한 AbstractController를 더 많이쓴다
	 *  4. AnnotationMethodHandlerAdapter: annotatin으로 결정
	 */
	@Test
	public void helloServletController() throws UnsupportedEncodingException, ServletException, IOException {
		// 서블릿 타입의 컨트롤러를 DispatcherServlet이 호출해줄 때 필요한 핸들러 어댑터(SimpleServletHandlerAdapter)를 등록
		setClasses(SimpleServletHandlerAdapter.class, HelloServlet.class);
		initRequest("/hello").addParameter("name", "Spring");
		
		assertThat(runService().getContentAsString(), is("Hello Spring"));
	}
	
	@Component("/hello")
	static class HelloServlet extends HttpServlet {
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
			String name = req.getParameter("name");
			resp.getWriter().print("Hello " + name);
		}
	}

}
