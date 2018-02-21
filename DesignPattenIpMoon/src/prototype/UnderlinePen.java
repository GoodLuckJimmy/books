package prototype;

import prototype.framwork.Product;


//ConcreatePrototype(구체적인 원형)의 역할
//인스턴스르 복사해서 새로운 인스턴스를 만드는 메소드 구현.
public class UnderlinePen implements Product {
	private char ulchar;
	public UnderlinePen(char ulchar) {
		this.ulchar = ulchar;
	}

	@Override
	public void use(String s) {
		int length = s.getBytes().length;
		System.out.println("\"" + s + "\"");
		System.out.print(" ");

		for(int i = 0; i < length; i++) {
			System.out.print(ulchar);
		}
		
		System.out.println("");
		
	}

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
