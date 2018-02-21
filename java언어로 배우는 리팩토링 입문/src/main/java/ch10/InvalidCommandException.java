package ch10;

public class InvalidCommandException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidCommandException(String name) {
		super(name);
	}
	
	public InvalidCommandException() {
	}

}
