package springbook2.ch3;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component("/hello")
public class HelloController3 implements CustomController {
	@ViewName("/WEB-INF/view/hello.jsp")
	@RequiredParams({"name"})

	@Override
	public void control(Map<String, String> params, Map<String, Object> model) {
		model.put("message", "Hello " + params.get("name"));
	}

}
