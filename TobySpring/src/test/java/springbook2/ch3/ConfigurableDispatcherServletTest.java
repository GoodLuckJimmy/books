package springbook2.ch3;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.web.servlet.ModelAndView;

import springbook.temp.HelloSpring;

/*
 *  DispatcherServlet이 서블릿 컨텍스트 만드는 과정 테스트
 */
public class ConfigurableDispatcherServletTest {
	
	@Test
	public void test() throws ServletException, IOException {
		//======================== 세팅시작 ===================================================================
		ConfigurableDispatcherServlet servlet = new ConfigurableDispatcherServlet();
		// 빈등록하기
		// 1. xml로 등록
		servlet.setRelativeLocations(getClass(), "spring-servlet.xml");
		// 2. 클래스 직접등록
		servlet.setClasses(HelloSpring.class);
		
		// 서블릿 config 정보로 초기화
		servlet.init(new MockServletConfig("spring"));
		
		
		//======================== 서블릿 호출 ===================================================================
		MockHttpServletRequest req = new MockHttpServletRequest("GET", "/hello");
		req.addParameter("name", "Spring");
		MockHttpServletResponse res = new MockHttpServletResponse();
		
		servlet.service(req, res);

		//======================== 서블릿 검증 ===================================================================
		ModelAndView mav = servlet.getModelAndView();
		assertThat(mav.getViewName(), is("/WEB-INF/view/hello.jsp"));
		assertThat((String)mav.getModel().get("message"), is("Hello Spring"));
	}

}
