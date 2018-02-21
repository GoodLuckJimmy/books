package decorator;

//Decorator 역할. Component역할과 동일한 인터페이스.
public abstract class Border extends Display {
	protected Display display; // 이 장식이 둘러싸고 있는 내용물
	public Border(Display display) {
		this.display = display;
	}

}
