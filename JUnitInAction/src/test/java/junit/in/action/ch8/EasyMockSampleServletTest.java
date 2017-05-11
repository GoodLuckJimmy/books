package junit.in.action.ch8;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

public class EasyMockSampleServletTest {
	private SampleServlet servlet;
	private HttpServletRequest mockHttpServletRequest;
	private HttpSession mockHttpSession;
	
	@Before
	public void setUp() {
		servlet = new SampleServlet();
		mockHttpServletRequest =
				createStrictMock(HttpServletRequest.class);
		mockHttpSession = createStrictMock(HttpSession.class);
	}
	
	@Test
	public void testIsAuthenticatedAuthenticated() {
		expect(mockHttpServletRequest.getSession(eq(false)))
		.andReturn(mockHttpSession);
		expect(mockHttpSession.getAttribute(eq("authenticated")))
		.andReturn("true");
		replay(mockHttpServletRequest);
		replay(mockHttpSession);
		assertTrue(servlet.isAuthenticated(mockHttpServletRequest));
	}
	
	@Test
	public void testisAuthenticatedNotAuthenticated() {
		expect(mockHttpSession.getAttribute(eq("authenticated")))
		.andReturn("false");
		replay(mockHttpSession);
		expect(mockHttpServletRequest.getSession(eq(false)))
		.andReturn(mockHttpSession);
		replay(mockHttpServletRequest);
		assertFalse(servlet.isAuthenticated(mockHttpServletRequest));
	}
	
	@Test
	public void testIsAuthenticatedNoSession() {
		expect(mockHttpServletRequest.getSession(eq(false)))
		.andReturn(null);
		replay(mockHttpServletRequest);
		replay(mockHttpSession);
		assertFalse(servlet.isAuthenticated(mockHttpServletRequest));
	}

}
