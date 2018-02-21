package junit.in.action.ch6;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestWebClient1 {
	
	@BeforeClass
	public static void setUp() {
		TestWebClient1 t = new TestWebClient1();
		URL.setURLStreamHandlerFactory(t.new StubStreamHandlerFactory());
	}

	private class StubStreamHandlerFactory implements URLStreamHandlerFactory {
		@Override
		public URLStreamHandler createURLStreamHandler(String protocol) {
			return new StubHttpUrlStreamHandler();
		}
	}
	
	private class StubHttpUrlStreamHandler extends URLStreamHandler {
		@Override
		protected URLConnection openConnection(URL url) throws IOException {
			return new StubHttpURLConnection(url);
		}
	}
	
	@Test
	public void testGetContentOk() throws MalformedURLException {
		WebClient client = new WebClient();
		String result = client.getContent(new URL("http://localhost"));
		assertEquals("It works", result);
	}
}
