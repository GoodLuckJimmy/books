package springbook2.ch1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/*
 *  xml의 <beans> = @Configuration
 *  xml의 <bean> = @Bean
 */
@Configuration
public class AnnotatedHelloConfig {
	
	@Bean // @Bean이 붙은 메소드 하나가 하나의 빈을 정의
	public AnnotatedHello annotatedHello() {
		return new AnnotatedHello();
	}

}
