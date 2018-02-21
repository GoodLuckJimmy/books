package just_Hibernate.ch1;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class BasicMovieManager {
	
	private SessionFactory sessionFactory = null;
	
	private void initSessionFactory() {
		Configuration config = new Configuration().configure(); // default xml로 config 설정
		/*
		// java코드 설정시
		Configuration cfg = new Configuration()
				.setProperty("hibernate.connection.url", "jdbc:mysql://db4free.net:33067/db4freehan")
				.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
				.setProperty("hibernate.connection.username", "db4freehan")
				.setProperty("hibernate.connection.password", "db4freehanpw")
				.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect")
				.setProperty("hibernate.order_updates", "true");
				
		// xml 정의시
		Configuration cfg = new Configuration().configure("xxx/hibernate.cfg.xml");
		
		*/
		

		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		
		sessionFactory = config.buildSessionFactory(serviceRegistry);
	}
	
	public void persistMovie(Movie movie) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(movie);
		session.getTransaction().commit();
	}
	
	private void findMovie(int movieId) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		Movie movie = (Movie)session.load(Movie.class, movieId);
		
		System.out.println("Movie: " + movie);
		session.getTransaction().commit();
	}
	
	private void findAll() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		List<Movie> movies = session.createQuery("from Movie").list();
		session.getTransaction().commit();
		System.out.println("All Movies: " + movies);
		

	}
	
}
