package facades;

class DifficultProduct {
	char nameChars[] = new char[7];

	public DifficultProduct() {
	}
	
	void setFirstNameCharacter(char c) {
		nameChars[0] = c;
	}
	
	void setSecondNameCharacter(char c) {
		nameChars[1] = c;
	}
	
	void setThirdNameCharacter(char c) {
		nameChars[2] = c;
	}
	
	void setFourthNameCharacter(char c) {
		nameChars[3] = c;
	}
	
	void setFithNameCharacter(char c) {
		nameChars[4] = c;
	}
	
	void setSixthNameCharacter(char c) {
		nameChars[5] = c;
	}
	
	void setSeventhNameCharacter(char c) {
		nameChars[6] = c;
	}
	
	String getName() {
		return new String(nameChars);
	}
}


class SimpleProductFacade {
	
	DifficultProduct difficultProduct;
	
	public SimpleProductFacade() {
		difficultProduct = new DifficultProduct();
	}
	
	public void setName(String n) {
		char chars[] = n.toCharArray();
		
		if (chars.length > 0) {
			difficultProduct.setFirstNameCharacter(chars[0]);
		}
		
		if (chars.length > 1) {
			difficultProduct.setSecondNameCharacter(chars[1]);
		}
		
		if (chars.length > 2) {
			difficultProduct.setThirdNameCharacter(chars[2]);
		}
		
		if (chars.length > 3) {
			difficultProduct.setFithNameCharacter(chars[3]);
		}
		
		if (chars.length > 4) {
			difficultProduct.setFithNameCharacter(chars[4]);
		}
		
		if (chars.length > 5) {
			difficultProduct.setSixthNameCharacter(chars[5]);
		}
		
		if (chars.length > 6) {
			difficultProduct.setSeventhNameCharacter(chars[6]);
		}
	}
	
	public String getName() {
		return difficultProduct.getName();
	}
}

class FacadesTest {

	public static void main(String[] args) {
		FacadesTest t = new FacadesTest();
	}
	
	public FacadesTest() {
		SimpleProductFacade simpleProductFacade = new SimpleProductFacade();
		
		simpleProductFacade.setName("printer");
		
		System.out.println("This product is a " + simpleProductFacade.getName());
	}

}
