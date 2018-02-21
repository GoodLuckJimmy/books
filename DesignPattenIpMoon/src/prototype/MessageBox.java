package prototype;

import prototype.framwork.Product;

// ConcreatePrototype(구체적인 원형)의 역할
// 인스턴스르 복사해서 새로운 인스턴스를 만드는 메소드 구현.
public class MessageBox implements Product {

	private char decochar;

	public MessageBox(char decochar) {
		this.decochar = decochar;
	}

	@Override
	public void use(String s) {
		int length = s.getBytes().length;
		for (int i = 0; i < length + 4; i++) {
			System.out.print(decochar);
		}

		System.out.println("");
		System.out.println(decochar + " " + s + " " + decochar);

		for (int i = 0; i < length + 4; i++) {
			System.out.print(decochar);
		}

		System.out.println("");
	}

	// clean메소드는 자신의 클래스(혹은 하위클래스)에서만 호출 할 수 있으므로, 다른 클래스의 요청으로 복제하는 경우는
	// createClone과 같은 다른 메소드를 위해서 clone을 기술할 필요가 있다.
	@Override
	public Product createClone() {
		Product p = null;
		try {
			p = (Product) clone();

		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		return p;
	}

}
