package junit.in.action.ch6;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import java.net.ProtocolException;

public class StubHttpURLConnection extends HttpURLConnection {
	private boolean isInput = true;
	public StubHttpURLConnection(URL url) {
		super(url);
	}
	
	public InputStream getInputStream() throws IOException {
		if (!isInput) {
			throw new ProtocolException(
					"Cannot read from URLConnection"
					+ " if doInput=false (call setDoInput(true))");
		}
		
		ByteArrayInputStream bais = new ByteArrayInputStream(
				new String("It works").getBytes());
		return bais;
	}

	@Override
	public void connect() throws IOException {
	}

	@Override
	public void disconnect() {
	}

	@Override
	public boolean usingProxy() {
		return false;
	}

}
