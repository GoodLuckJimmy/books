package junit.in.action.ch18;

public interface UserDao {

	void addUser(User user);

	User getUserById(long id);

	void deleteUser(long id);

}
