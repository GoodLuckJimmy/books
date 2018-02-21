package mytest;

public interface UserDao {

	public void addUser(User user);
	
	public User getUserById(long id);
	
	public void deleteUser(long id);
}
