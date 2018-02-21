package springbook2.ch1;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
public class DatasourceConfig {
	
	@Bean
	/*
	 *     <bean id="dataSource" class="org.springframework.jdbc.datasource.SimpleDriverDataSource" >
	 *       <property name="driverClass" value="com.mysql.jdbc.Driver" />
	 *       <property name="url"         value="jdbc:mysql://localhost/testdb" />
	 *       <property name="username"    value="spring" />
	 *       <property name="password"    value="book" />
	 *     </bean>
	 */
	public DataSource dataSource() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
		dataSource.setUrl("jdbc:mysql://localhost/testdb");
		dataSource.setUsername("spring");
		dataSource.setPassword("book");
		
		return dataSource;
	}

}
