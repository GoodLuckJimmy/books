package junit.in.action.ch7;

import java.io.InputStream;

public interface ConnectionFactory {

	InputStream getData() throws Exception;
}
