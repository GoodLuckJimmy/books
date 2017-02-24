package springbook2.ch3;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.util.ClassUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AbstractRefreshableWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;

/*
 * 테스트를 위해 DispatcherServlet를 확장해서 만든 클래스
 */
@SuppressWarnings("serial")
public class ConfigurableDispatcherServlet extends DispatcherServlet{
	private Class<?>[] classes; // 클래스 설정파일
	private String[] locations; // xml 설정파일
	
	private ModelAndView modelAndView;
	
	public ConfigurableDispatcherServlet(String[] locations) {
		this.locations = locations;
	}
	
	public ConfigurableDispatcherServlet(Class<?> ...classes) {
		this.classes = classes;
	}
	
	public void setLocations(String ...locations) {
		this.locations = locations;
	}
	
	// 주어진 클래스로부터 상대적인 위치의 클래스패스에 있는 설정파일을 지정할 수 있게 해준다
	public void setRelativeLocations(Class<?> clazz, String ...relativeLocations) {
		String[] locations = new String[relativeLocations.length];
		String currentPath = ClassUtils.classPackageAsResourcePath(clazz) + "/";
		
		for (int i = 0; i < relativeLocations.length; i++) {
			locations[i] = currentPath + relativeLocations[i];
		}
		this.setLocations(locations);
	}
	
	public void setClasses(Class<?> ...classes) {
		this.classes = classes;
	}
	
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		modelAndView = null;
		super.service(req, res);
	}

	@Override
	// DispatcherServlet의 서블릿 컨텍스트를 생성하는 메소드를 오버라이드해서 테스트용 메타정보를 이용해서 서블릿 컨택스트를 생성한다
	protected WebApplicationContext createWebApplicationContext (ApplicationContext parent) {

		AbstractRefreshableWebApplicationContext wac =
				new AbstractRefreshableWebApplicationContext() {
					
					@Override
					protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeansException, IOException {
						if (locations != null) {
							XmlBeanDefinitionReader xmlReader =
									new XmlBeanDefinitionReader(beanFactory);
							xmlReader.loadBeanDefinitions(locations);
						}
						if (classes != null) {
							AnnotatedBeanDefinitionReader reader =
									new AnnotatedBeanDefinitionReader(beanFactory);
							reader.register(classes);
						}
					}
				};
		wac.setServletContext(getServletContext());
		wac.setServletConfig(getServletConfig());
		wac.refresh();

		return wac;
		
	}
	
	// 뷰를 실행하는 과정을 가로채서 컨트롤러가 돌려준 ModelAndView 정보를 따로 저장해둔다.
	// 테스트에서 HttpServletResponse를 확인하는 대신 컨트롤러가 리턴한 ModelAndView를 검증할 수 있게 해준다
	protected void render(ModelAndView mv, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.modelAndView = mv;
		super.render(mv, request, response);
	}
	
	public ModelAndView getModelAndView() {
		return modelAndView;
	}
}
