package junit.in.action.ch3;

public class ErrorResponse implements Response {
	private Request originalRequest;
	private Exception originalException;
	
	public ErrorResponse(Request request, Exception exception) {
		this.originalException = exception;
		this.originalRequest = request;
	}
	
	public Request getOriginalRequest() {
		return this.originalRequest;
	}
	
	public Exception getOriginalException() {
		return this.originalException;
	}

	public String getName() {
		return null;
	}

}
