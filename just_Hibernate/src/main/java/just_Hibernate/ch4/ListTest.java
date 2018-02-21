package just_Hibernate.ch4;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class ListTest {
	
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
				.addResource("Showroom.hbm.xml");

		
		ServiceRegistry registry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		factory = config.buildSessionFactory(registry);
	}

	private void persistBags() {
        Session session = factory.getCurrentSession();
        
        session.beginTransaction();

        Showroom showroom = new Showroom();
        showroom.setLocation("East Croydon, Greater London");
        showroom.setManager("Barry Larry");
        List<Car> cars = new ArrayList<Car>();
        
        cars.add(new Car("Toyota", "Racing Green"));
        cars.add(new Car("Toyota", "Racing Green"));
        cars.add(new Car("Nissan", "White"));
        cars.add(new Car("BMW", "Black"));
        cars.add(new Car("Mercedes", "Silver"));

        showroom.setCars(cars);
        
        session.save(showroom);
        
        session.getTransaction().commit();
        System.out.println("Done");
    }
	
	private void retrieveBags(){
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        
        List list = session.createQuery("from just_Hibernate.ch4.Showroom").list();
        
        for (Object object : list) {
            System.out.println("List items: "+object);
        }
        session.getTransaction().commit();
        System.out.println("Done");
        
    }
	
	
    public static void main(String[] args) {
        ListTest test = new ListTest();
        test.init();
        test.persistBags();
        test.retrieveBags();
    }
}
