package mytest;

import org.junit.Before;
import static org.easymock.EasyMock.*;

public class UserFacadeImplTest {
	
	private UserFacadeImpl facade;
	private UserDao dao;
	
	@Before
	public void setFixtures() {
		facade = new UserFacadeImpl();
		dao = createMock(UserDao.class);
	}

}
