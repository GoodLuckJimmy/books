package junit.in.action.ch18;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UserDaoJpaImpl implements UserDao {
	private EntityManager entityManager;
	
	@Override
	public void addUser(User user) {
		entityManager.persist(user);
	}
	
	@Override
	public User getUserById(long id) {
		return entityManager.find(User.class, id);
	}
	
	@Override
	public void deleteUser(long id) {
		String jpl = "delete User where id = ?";
		Query query = entityManager.createQuery(jpl);
		query.setParameter(1, id);
		query.executeUpdate();
	}

}
