package springbook2.ch1;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;


public class ContextTest {

	private String basePath = StringUtils.cleanPath(ClassUtils.classPackageAsResourcePath(getClass())) + "/";
	
	@Test
	public void staticApplicationContext() {

		// StaticApplicationContext는 test용
		StaticApplicationContext ac = new StaticApplicationContext();
		ac.registerSingleton("hello1", Hello.class);
		
		Hello hello1 = ac.getBean("hello1", Hello.class);
		assertThat(hello1, is(notNullValue()) );
	
		
		// bean definition을 이용한 빈 등록
		BeanDefinition helloDef = new RootBeanDefinition(Hello.class);
		// <property name="name" value="Spring" />
		helloDef.getPropertyValues().addPropertyValue("name", "Spring");
		// <bean id = "hello2" ... />
		ac.registerBeanDefinition("hello2", helloDef);
		

		Hello hello2 = ac.getBean("hello2", Hello.class);

		assertThat(hello2.sayHello(), is("Hello Spring"));
		assertThat(hello1, is(not(hello2)));
		assertThat(ac.getBeanFactory().getBeanDefinitionCount(), is(2));
	}
	
	@Test
	public void registerBeanWithDependency() {
		// StaticApplicationContext는 test용
		StaticApplicationContext ac = new StaticApplicationContext();
		
		// SpringPrinter 클래스 타입이며, printer라는 이름을 가진 빈 등록
		ac.registerBeanDefinition("printer", new RootBeanDefinition(StringPrinter.class));
		
		BeanDefinition helloDef = new RootBeanDefinition(Hello.class);
		helloDef.getPropertyValues().addPropertyValue("name", "Spring"); // 단순 값
		// 아이디가 printer인 빈에 대한 레퍼런스를 프로퍼티로 등록
		helloDef.getPropertyValues().addPropertyValue("printer", new RuntimeBeanReference("printer"));
		
		ac.registerBeanDefinition("hello", helloDef);
		
		Hello hello = ac.getBean("hello", Hello.class);
		hello.print();
		
		assertThat(ac.getBean("printer").toString(), is("Hello Spring"));
		
	}
	
	@Test
	public void genericApplicationContextWithXml() {
		GenericApplicationContext ac = new GenericApplicationContext();

		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(ac);
		// classpath: file: http: 접두어 가능
		reader.loadBeanDefinitions(basePath + "genericApplicationContext.xml");

		ac.refresh();
		
		Hello hello = ac.getBean("hello", Hello.class);
		hello.print();
		
		assertThat(ac.getBean("printer").toString(), is("Hello Spring"));
		
	}
	
	@Test
	public void genericXmlApplicationContext() {
		// GenericXmlApplicationContext = GenericApplicationContext + XmlBeanDefinitionReader + refresh
		GenericApplicationContext ac = new GenericXmlApplicationContext(
				basePath + "genericApplicationContext.xml");
		
		Hello hello = ac.getBean("hello", Hello.class);
		hello.print();
		
		assertThat(ac.getBean("printer").toString(), is("Hello Spring"));
		
	}
	
	@Test(expected=BeanCreationException.class)
	public void createContextWithoutParent() {
		ApplicationContext child = new GenericXmlApplicationContext(basePath + "childContext.xml");
	}
	
	@Test
	public void contextHierachy() {

		ApplicationContext parent = new GenericXmlApplicationContext(basePath + "parentContext.xml");

		GenericApplicationContext child = new GenericApplicationContext(parent);
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(child);
		reader.loadBeanDefinitions(basePath + "childContext.xml");
		child.refresh();
		
		Printer printer = child.getBean("printer", Printer.class);
		assertThat(printer, is(notNullValue()));
		
		Hello hello = child.getBean("hello", Hello.class);
		assertThat(hello, is(notNullValue()));
		
		hello.print();
		assertThat(printer.toString(), is("Hello Child"));
		
	}
	
	@Test
	public void simpleBeanScanning() {
		// AnnotationConfigApplicationContext는 빈 스캐너 내장. but 학습용
		// 실전에서는 AnnotationConfigWebApplicationContext 쓸것
		// @Component, @Repository, @Service, @Controller가 붙은 클래스를 스캔할 패키지 넣으면 됨
		// usingConfigurationAnno와 충돌나서 주석처리
		ApplicationContext ctx = 
				new AnnotationConfigApplicationContext(
						"springbook2.ch1");

		AnnotatedHello hello = ctx.getBean("annotatedHello", AnnotatedHello.class);
		
		assertThat(hello, is(notNullValue()));
	}
	
	@Test
	public void filteredBeanScanning() {
		//ApplicationContext ctx = new GenericXmlApplicationContext(basePath + "filteredScanningContext.xml");
		//Hello hello = ctx.getBean("hello", Hello.class);
		//assertThat(hello, is(notNullValue()));
	}
	
	@Test
	public void configurationBean() {
		ApplicationContext ctx =
				new AnnotationConfigApplicationContext(AnnotatedHelloConfig.class);

		AnnotatedHello hello = ctx.getBean("annotatedHello", AnnotatedHello.class);

		assertThat(hello, is(notNullValue()));
		
		// config 클래스자체도 하나의 빈으로 등록됨
		AnnotatedHelloConfig config = ctx.getBean("annotatedHelloConfig", AnnotatedHelloConfig.class);
		assertThat(config, is(notNullValue()));
		
		assertThat(config.annotatedHello(), is(sameInstance(hello))); //singleton
		assertThat(config.annotatedHello(), is(config.annotatedHello()));
		
		System.out.println(ctx.getBean("systemProperties").getClass());
	}
	
	@Test
	public void constructorArg() {
		// AnnotationConfigApplicationContext를 사용하면 등록할 빈 클래스 직접 지정가능
		AbstractApplicationContext ac =
				new AnnotationConfigApplicationContext(BeanA.class, BeanB.class);
		BeanA beanA = ac.getBean(BeanA.class);

		assertThat(beanA.beanB, is(notNullValue()));
		
	}
	
	private static class BeanA {
		@Autowired BeanB beanB;
	}
	
	private static class BeanB {
	}
}
