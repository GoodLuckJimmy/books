package prototype.framwork;

import java.util.HashMap;


// 인스턴스를 복사하는 메소드를 이용해서 새로운 인스턴스를 만든다.
public class Manager {
	
	private HashMap<String, Product> showcase = new HashMap<>();

	public void register(String name, Product proto) {
		showcase.put(name, proto);
	}
	
	public Product create(String protoname) {
		Product p = showcase.get(protoname);
		return p.createClone();
	}

}
