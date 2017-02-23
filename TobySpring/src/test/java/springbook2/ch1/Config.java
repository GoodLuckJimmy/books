package springbook2.ch1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/*
 *  자바코드를 이용한 DI
 */
@Configuration
//@Profile("dev")
public class Config {
	@Bean public Hello hello(Printer printer) {
		Hello hello = new Hello();
		hello.setPrinter(printer);
		return hello;
	}
	
	@Bean public Printer printer() {
		return new StringPrinter();
	}

}
