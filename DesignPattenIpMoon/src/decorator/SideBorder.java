package decorator;

// ConcreteDecorator
public class SideBorder extends Border {
	private char boarderChar;
	public SideBorder(Display display, char ch) {
		super(display);
		this.boarderChar = ch;
	}

	@Override
	public int getColumns() {
		return 1 + display.getColumns() + 1;
	}

	@Override
	public int getRows() {
		return display.getRows();
	}

	@Override
	public String getRowText(int row) {
		return boarderChar + display.getRowText(row) + boarderChar;
	}

}
