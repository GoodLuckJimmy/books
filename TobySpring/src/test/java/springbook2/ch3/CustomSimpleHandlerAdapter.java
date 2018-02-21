package springbook2.ch3;


import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;

// 빈으로 등록하면 스프링에서 supports함수를 이용해 자동 호출함
public class CustomSimpleHandlerAdapter implements HandlerAdapter{

	@Override
	public boolean supports(Object handler) {
		// 이 컨트롤러가 지원하는 타입 확인
		return (handler instanceof CustomController) ;
	}

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Method m = ReflectionUtils.findMethod(handler.getClass(), "control", Map.class, Map.class);
		ViewName viewName = AnnotationUtils.getAnnotation(m, ViewName.class);
		RequiredParams requiredParams = AnnotationUtils.getAnnotation(m, RequiredParams.class);
		
		Map<String, String> params = new HashMap<String, String>();
		for (String param : requiredParams.value()) {
			String value = request.getParameter(param);
			if (value == null)
				throw new IllegalStateException();
		}
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		((CustomController)handler).control(params, model);
		
		return new ModelAndView(viewName.value(), model);
	}

	@Override
	public long getLastModified(HttpServletRequest request, Object handler) {
		// 캐싱 적용하지 않을려면 0보다 작은값
		return -1;
	}

}
