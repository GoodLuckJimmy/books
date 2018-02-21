package springbook2.ch1;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class ScopeTest {

	static class SingletonBean{}
	static class SingletonClientBean{
		@Autowired SingletonBean bean1;
		@Autowired SingletonBean bean2;
	}
	
	@Test
	public void singletonScope() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(
				SingletonBean.class, SingletonClientBean.class);
		
		// set은 중복허용안하므로 같은 오브젝트 추가해도 한개만 남는다
		Set<SingletonBean> beans = new HashSet<SingletonBean>();
		
		beans.add(ac.getBean(SingletonBean.class));
		beans.add(ac.getBean(SingletonBean.class));
		assertThat(beans.size(), is(1));
		
		beans.add(ac.getBean(SingletonClientBean.class).bean1);
		beans.add(ac.getBean(SingletonClientBean.class).bean2);
		assertThat(beans.size(), is(1));
		
	}
	
	
	@Scope("prototype")
	static class PrototypeBean{}
	static class PrototypeClientBean {
		@Autowired PrototypeBean bean1;
		@Autowired PrototypeBean bean2;
	}

	@Test
	public void prototypeScope() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(
				PrototypeBean.class, PrototypeClientBean.class);
		
		// set은 중복허용안하므로 같은 오브젝트 추가해도 한개만 남는다
		Set<PrototypeBean> beans = new HashSet<PrototypeBean>();
		
		beans.add(ac.getBean(PrototypeBean.class));
		assertThat(beans.size(), is(1));
		beans.add(ac.getBean(PrototypeBean.class));
		assertThat(beans.size(), is(2));
		
		beans.add(ac.getBean(PrototypeClientBean.class).bean1);
		assertThat(beans.size(), is(3));
		beans.add(ac.getBean(PrototypeClientBean.class).bean2);
		assertThat(beans.size(), is(4));
	}
	
}
