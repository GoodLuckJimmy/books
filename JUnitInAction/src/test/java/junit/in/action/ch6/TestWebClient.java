package junit.in.action.ch6;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mortbay.jetty.HttpHeaders;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.AbstractHandler;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.util.ByteArrayISO8859Writer;

public class TestWebClient {
	
	@BeforeClass
	public static void setUp() throws Exception {
		Server server = new Server(8080);
		TestWebClient t = new TestWebClient();
		
		Context contentOkContext = new Context(server, "/testGetContentOk");
		contentOkContext.setHandler(t.new TestGetContentOkHandler());
		
		Context contentNotFoundContext = new Context(server,
				"/testGetContentNotFound");
		contentNotFoundContext.setHandler(t. new TestGetContentNotFoundHandler());
		
		server.setStopAtShutdown(true);
		server.start();
	}
	
	@AfterClass
	public static void tearDown() {
	}
	
	@Test
	public void testGetContentOk() throws Exception {
		WebClient client = new WebClient();
		String result = client.getContent(new URL(
				"http://localhost:8080/testGetContentOk"));
		
		assertEquals("It works", result);
	}
	
	@Test
	public void testGetContentNotFound() throws Exception {
		WebClient client = new WebClient();
		String result = client.getContent(new URL(
				"http://localhost:8080/testGetContentNotFound"));
		assertNull(result);
	}

	private class TestGetContentOkHandler extends AbstractHandler {
		@Override
		public void handle(String target, HttpServletRequest request,
				HttpServletResponse response, int dispatch)
				throws IOException, ServletException {
			
			OutputStream out = response.getOutputStream();
			ByteArrayISO8859Writer writer = new ByteArrayISO8859Writer();
			writer.write("It works");
			writer.flush();
			response.setIntHeader(HttpHeaders.CONTENT_LENGTH, writer.size());
			writer.writeTo(out);
			out.flush();
		}
	}
	
	private class TestGetContentNotFoundHandler extends AbstractHandler {
		@Override
		public void handle(String target, HttpServletRequest request,
				HttpServletResponse response, int dispatch)
				throws IOException, ServletException {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		
	}
}
