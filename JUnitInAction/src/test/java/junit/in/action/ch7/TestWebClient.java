package junit.in.action.ch7;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestWebClient {

	@Test
	public void testGetContentOk() {
		MockConnectionFactory mockConnectionFactory =
				new MockConnectionFactory();
		
		MockInputStream mockStream = new MockInputStream();
		mockStream.setBuffer("It works");
		mockConnectionFactory.setData(mockStream);
		
		WebClient client = new WebClient();
		String result = client.getContent(mockConnectionFactory);

		assertEquals("It works", result);
		mockStream.verify();
	}
}
