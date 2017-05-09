package junit.in.action.ch3;

public interface Controller {
	// exception이 없다. 있으면 결국 jvm에 에러넘어가서 사용자가 서버 에러페이지를 보게되므로 비추
	Response processRequest(Request request);
	void addHandler(Request request, RequestHandler requestHandler);
}
