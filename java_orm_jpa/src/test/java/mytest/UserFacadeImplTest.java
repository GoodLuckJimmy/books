package mytest;

import org.junit.Before;
import org.junit.Test;

import static org.easymock.EasyMock.*;

public class UserFacadeImplTest {
	
	private UserFacadeImpl facade;
	private UserDao dao;
	
	@Before
	public void setFixtures() {
		facade = new UserFacadeImpl();
		dao = createMock(UserDao.class);
		facade.setUserDao(dao);
	}
	
	@Test
	public void testGetUserById() {
		int id = 666;
		User user = newUserWithTelephones();
		expect(dao.getUserById(id)).andReturn(user);
		replay(dao);
		UserDto dto = facade.getUserById(id);
		assertUser(dto);
	}

}
