package junit.in.action.ch18;

import static org.easymock.EasyMock.*;
import static junit.in.action.ch18.EntitiesHelper.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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
	
	@Test
	public void testGetUserByIdUnknowId() {
		int id = 666;
		expect(dao.getUserById(id)).andReturn(null);
		replay(dao);
		UserDto dto = facade.getUserById(id);
		assertNull(dto);
	}

}
