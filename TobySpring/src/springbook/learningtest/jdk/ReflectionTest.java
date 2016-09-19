package springbook.learningtest.jdk;

import org.junit.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.lang.reflect.*;

public class ReflectionTest {
	@Test
	public void invokeMethod() throws Exception {
		String name = "Spring";
		
		//length()
		assertThat(name.length(), is(6));
		
		Method lengthMethod = String.class.getMethod("length");
		assertThat((Integer)lengthMethod.invoke(name), is(6));
		
		// charAt
		assertThat(name.charAt(0), is('S'));
		Method charAtMethod = String.class.getMethod("charAt", int.class);
		assertThat((Character)charAtMethod.invoke(name, 0), is('S'));
		}
	
	@Test
	public void simpleProxy() {
		Hello hello = new HelloTarget();
		assertThat(hello.sayHello("Toby"), is("Hello Toby"));
		assertThat(hello.sayHi("Toby"), is("Hi Toby"));
		assertThat(hello.sayHThankYou("Toby"), is("Thank You Toby"));
		
		Hello proxiedHello = new HelloUppercase(new HelloTarget());
		assertThat(proxiedHello.sayHello("Toby"), is("HELLO TOBY"));
		assertThat(proxiedHello.sayHi("Toby"), is("HI TOBY"));
		assertThat(proxiedHello.sayHThankYou("Toby"), is("THANK YOU TOBY"));
	}
}
