package junit.in.action.ch3;

public interface RequestHandler {
	Response process(Request request) throws Exception;
}
