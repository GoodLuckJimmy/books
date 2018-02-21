package springbook2.ch3;

import java.util.Map;

/*
 * SimpleController.java를 이용한 controller
 */
public class HelloController2 extends SimpleController {
	public HelloController2() {
		this.setRequiredParams(new String[] {"name"});
		this.setViewName("/WEB-INF/view/hello.jsp");
	}

	@Override
	public void control(Map<String, String> params, Map<String, Object> model) throws Exception {
		model.put("message", "Hello " + params.get("name"));
	}

}
