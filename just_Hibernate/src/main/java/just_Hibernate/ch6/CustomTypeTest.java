package just_Hibernate.ch6;

import org.hibernate.cfg.Configuration;

public class CustomTypeTest {

	private void init() {
		// ...
		Configuration config = new Configuration().configure("hibernate.cfg.xml");
		config.registerTypeOverride(new CustomType());
		// ...
	}
}
