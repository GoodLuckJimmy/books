package junit.in.action.ch18;

import java.sql.Connection;

import javax.persistence.*;

import org.hibernate.*;
import org.hibernate.cfg.Settings;
import org.hibernate.connection.ConnectionProvider;
import org.hibernate.impl.SessionFactoryImpl;
import org.hibernate.jpa.internal.EntityManagerFactoryImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public abstract class AbstractJpaTestCase {
	private static EntityManagerFactory entityManagerFactory;
	protected static Connection connection;
	protected EntityManager em;
	
	@BeforeClass
	public static void setupDatabase() throws Exception {
		entityManagerFactory =
				Persistence.createEntityManagerFactory("chapter-18");
		connection = getConnection(entityManagerFactory);
	}
	
	@AfterClass
	public static void closeDatabase() throws Exception {
		if (connection != null) {
			connection.close();
			connection = null;
		}
		if (entityManagerFactory != null) {
			entityManagerFactory.close();
		}
	}
	
	@Before
	public void setEntityManager() {
		em = entityManagerFactory.createEntityManager();
	}
	
	@After
	public void closeEntityManager() {
		em.close();
	}

	private static Connection getConnection(Object object) {
		Connection connection = null;


		if (object instanceof EntityManagerFactoryImpl) {
			EntityManagerFactoryImpl impl =
					(EntityManagerFactoryImpl) object;
			SessionFactory sessionFactory = impl.getSessionFactory();
			if (sessionFactory instanceof SessionFactoryImpl) {
				SessionFactoryImpl sfi = (SessionFactoryImpl) sessionFactory;
				Settings settings = sfi.getSettings();
				ConnectionProvider provider = settings.getConnectionProvider();
				connection = provider.getConnection();
			}
		}
		return connection;
	}
	
	protected void beginTransaction() {
		em.getTransaction().begin();
	}
	
	protected void commitTransaction() {
		em.getTransaction().commit();
	}
	
	protected void commitTransaction(boolean clearContext) {
		commitTransaction();
		if (clearContext) {
			em.clear();
		}
	}

}
