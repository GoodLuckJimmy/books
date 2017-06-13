package ch11;

public class Client {
	private final Printer printer;
	public Client(boolean graphical) {
		if (graphical) {
			printer = new GraphPrinter();
		} else {
			printer = new DigitPrinter();
		}
	}

	public void execute() {
		int[] table = {3,1,4,1,5,9};
		for (int n: table) {
			printer.println(n);
		}
	}
}
