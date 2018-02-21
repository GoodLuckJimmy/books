package junit.in.action.ch7;

import java.io.IOException;
import java.io.InputStream;

public class WebClient {

	public String getContent(ConnectionFactory connectionFactory) {
		String result;
		StringBuffer content = new StringBuffer();
		InputStream is = null;

		try {
			is = connectionFactory.getData();
			int count;
			while(-1 != (count = is.read())) {
				content.append(new String(Character.toChars(count)));
			}
			result = content.toString();
		} catch (Exception e) {
			return null;
		}
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				result = null;
			}
		}
		
		return result;
	}
}
