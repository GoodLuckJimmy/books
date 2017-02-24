package springbook2.ch3;

import java.util.Map;

public interface CustomController {
	void control(Map<String, String> params, Map<String, Object> model);
}
