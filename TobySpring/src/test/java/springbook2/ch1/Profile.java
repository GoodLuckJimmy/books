package springbook2.ch1;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Profile {

	@Test
	@Ignore
	public void profileActive() {
		GenericXmlApplicationContext ac = new GenericXmlApplicationContext();
		// 프로파일은 xml이 로딩되기 전이나 refresh()호출전에 실행할것
		ac.getEnvironment().setActiveProfiles("dev");
		ac.load(getClass(), "applicationContext.xml");
		ac.refresh();
	}
	
}
