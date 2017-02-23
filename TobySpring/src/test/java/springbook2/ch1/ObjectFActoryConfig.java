package springbook2.ch1;

import org.springframework.beans.factory.config.ObjectFactoryCreatingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectFActoryConfig {
	
	@Bean
	public ObjectFactoryCreatingFactoryBean serviceRequestFactory() {
		ObjectFactoryCreatingFactoryBean factoryBean =
				new ObjectFactoryCreatingFactoryBean();
		factoryBean.setTargetBeanName("serviceRequest");
		return factoryBean;
		
		/*
		 *  스프링의 인터페이스를 애플리케이션 코드에서 사용하기 싫을경우는
		 *  ObjectFactoryCreatingFactoryBean 대신 ServiceLocatorFactoryBean이용. p172참고
		 *  
		 *  자바표준쓸려면 javax.inject.Provider 사용. p175참고
		 */
	}

}
