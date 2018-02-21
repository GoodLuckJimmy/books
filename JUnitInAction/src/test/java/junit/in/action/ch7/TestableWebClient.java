package junit.in.action.ch7;

import java.net.HttpURLConnection;
import java.net.URL;

class TestableWebClient extends WebClient {
	private HttpURLConnection connection;
	public void setHttpURLConnection(HttpURLConnection connection) {
		this.connection = connection;
	}
	
	
	public HttpURLConnection createHttpURLConnection(URL url) {
		return this.connection;
	}

}
