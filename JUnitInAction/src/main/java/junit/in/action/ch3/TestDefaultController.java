package junit.in.action.ch3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

public class TestDefaultController {
	private DefaultController controller;
	private Request request;
	private RequestHandler handler;
	
	@Before
	public void instantiate() {
		controller = new DefaultController();
		request = new SampleRequest();
		handler = new SampleHandler();
		
		controller.addHandler(request, handler);
	}
	
	@Test
	public void testAddHandler() {
		RequestHandler handler2 = controller.getHandler(request);
		assertSame("Handler we set in controller should be the same handler we get",
				handler2, handler);
	}
	
	@Test
	public void testProcessRequest() {
		Response response = controller.processRequest(request);
		assertNotNull("Mush not return a null response", response);
		assertEquals(new SampleResponse(), response);
	}
	
	@Test
	public void testProcessRequestAnswersErrorResponse() {
		SampleRequest request = new SampleRequest("testError");
		SampleExceptionHandler handler = new SampleExceptionHandler();
		controller.addHandler(request, handler);
		Response response = controller.processRequest(request);
		
		assertNotNull("Must not return a null response", response);
		assertEquals(ErrorResponse.class, response.getClass());
	}
	

	private class SampleRequest implements Request {
		private static final String DEFAUlT_NAME = "Test";
		private String name;
		
		public SampleRequest(String name) {
			this.name = name;
		}
		
		public SampleRequest() {
			this(DEFAUlT_NAME);
		}

		public String getName() {
			return this.name;
		}
	}
	
	private class SampleHandler implements RequestHandler {
		public Response process(Request request) throws Exception {
			return new SampleResponse();
		}
	}
	
	private class SampleResponse implements Response {
		private static final String NAME = "Test";

		public String getName() {
			return NAME;
		}
		
		public boolean equals(Object object) {
			boolean result = false;
			if (object instanceof SampleResponse) {
				result = ( (SampleResponse) object).getName().equals(getName());
			}
			
			return result;
		}
		
		public int hashCode() {
			return NAME.hashCode();
		}
	}
	
	private class SampleExceptionHandler implements RequestHandler {
		public Response process(Request request) throws Exception {
			throw new Exception("error processing request");
		}
	}
}
