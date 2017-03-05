package just_Hibernate.ch5.oneToOne.기본키사용;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class OneToOneTest {
	
	private SessionFactory factory = null;
	
	private void init() {
		Configuration config = new Configuration()
				.setProperty("hibernate.connection.url", "jdbc:mysql://db4free.net:3306/db4freehan")
				.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
				.setProperty("hibernate.connection.username", "db4freehan")
				.setProperty("hibernate.connection.password", "db4freehanpw")
				.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect")
				.setProperty("hibernate.order_updates", "true")
				.setProperty("hibernate.current_session_context_class", "thread")
				.setProperty("hibernate.hbm2ddl.auto", "update")
				.addResource("just_Hibernate/ch5/oneToOne/기본키사용/Car.hbm.xml");

		ServiceRegistry registry = new ServiceRegistryBuilder().applySettings(config.getProperties())
				.buildServiceRegistry();
		factory = config.buildSessionFactory(registry);
	}
	
	private void persist() {
		
		Session session = factory.getCurrentSession();

		session.beginTransaction();
		
		Car car = new Car();
		
		car.setId(1);
		car.setName("Cadillac ATS Sedan");
		car.setColor("White");
		
		Engine engine = new Engine();
		engine.setMake("V8 Series");
		engine.setModel("DTS");
		engine.setSize("1.6 V8 GAS");
		
		car.setEngine(engine);
		engine.setCar(car);
		
		session.save(car);
		session.save(engine);
		
		session.getTransaction().commit();
	}

	public static void main(String[] args) {
		OneToOneTest test = new OneToOneTest();
		test.init();
		test.persist();
	}
}
