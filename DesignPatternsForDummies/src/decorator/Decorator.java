package decorator;

class Computer {
	public Computer() {}
	
	public String description() {
		return "computer";
	}
}

abstract class ComponentDecorator extends Computer {
	public abstract String description();
}

class Disk extends ComponentDecorator {
	Computer computer;
	
	public Disk(Computer c) {
		computer = c;
	}

	@Override
	public String description() {
		return computer.description() + " add a disk";
	}
	
}

class CD extends ComponentDecorator {
	Computer computer;
	
	public CD(Computer c) {
		computer = c;
	}

	@Override
	public String description() {
		return computer.description() + " add a CD";
	}
	
}

class Monitor extends ComponentDecorator {
	Computer computer;
	
	public Monitor(Computer c) {
		computer = c;
	}

	@Override
	public String description() {
		return computer.description() + " add a monitor";
	}
	
}

public class Decorator {

	public static void main(String[] args) {
		Computer computer = new Computer();
		computer = new Disk(computer);
		computer = new CD(computer);
		computer = new CD(computer);
		
		System.out.println("You are getting a " + computer.description() + ".");

	}

}
