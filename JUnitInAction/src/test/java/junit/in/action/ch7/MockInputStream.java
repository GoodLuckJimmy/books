package junit.in.action.ch7;

import java.io.IOException;
import java.io.InputStream;

public class MockInputStream extends InputStream {
	private String buffer;
	private int position = 0;
	private int closeCount = 0;
	public void setBuffer(String buffer) {
		this.buffer = buffer;
	}

	@Override
	public int read() throws IOException {
		if (position == this.buffer.length()) {
			return -1;
		}
		return this.buffer.charAt(this.position++);
	}
	
	public void close() throws IOException {
		closeCount++;
		super.close();
	}
	
	public void verify() throws java.lang.AssertionError {
		if (closeCount != 1) {
			throw new AssertionError("close() should have been called once"
					+ " and once only");
		}
	}

}
