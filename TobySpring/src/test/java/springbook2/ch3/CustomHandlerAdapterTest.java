package springbook2.ch3;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Test;

public class CustomHandlerAdapterTest extends AbstractDispatcherServletTest {
	
	@Test
	public void customHandlerAdapter() throws ServletException, IOException {
		setClasses(CustomSimpleHandlerAdapter.class, HelloController3.class);
		initRequest("/hello").addParameter("name", "Spring").runService();
		assertViewName("/WEB-INF/view/hello.jsp");
		assertModel("message", "Hello Spring");
		
	}

}
