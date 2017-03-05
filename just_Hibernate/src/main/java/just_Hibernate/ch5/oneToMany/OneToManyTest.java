package just_Hibernate.ch5.oneToMany;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


public class OneToManyTest {
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
				.addResource("just_Hibernate/ch5/oneToMany/Movie.hbm.xml");

		ServiceRegistry registry = new ServiceRegistryBuilder().applySettings(config.getProperties())
				.buildServiceRegistry();
		factory = config.buildSessionFactory(registry);
	}
	
	private void persist() {
		
		Session session = factory.getCurrentSession();

		session.beginTransaction();
		

		Movie movie = null;
		Actor actor = null;
		Set<Actor> actors = new HashSet<Actor>();
		
		actor = new Actor("Sharukh", "Khan", "King Khan");
		actors.add(actor);
		actor = new Actor("Deepika", "Padukone", "Miss Chennai");
		actors.add(actor);
		
		movie = new Movie("Chennai Express");
		movie.setActors(actors);
		
		session.getTransaction().commit();
	}

	public static void main(String[] args) {
		OneToManyTest test = new OneToManyTest();
		test.init();
		test.persist();
	}
}
