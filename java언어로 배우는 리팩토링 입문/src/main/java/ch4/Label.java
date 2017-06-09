package ch4;

public class Label {
	private final String _label;

	public Label(String label) {
		_label = label;
	}

	public void display() {
		System.out.println("display: " + _label);
	}
	
	public String toString() {
		return "\"" + _label + "\"";
	}
	
	public boolean isNull() {
		return false;
	}
	
	// Factory Method
	public static Label newNull() {
		return NullLable.getInstance();
	}

	// Null Object
	private static class NullLable extends Label {
		// Singleton
		private static final NullLable sigleton = new NullLable();
		private static Label getInstance() {
			return sigleton;
		}

		public NullLable() {
			super("(none)");
		}

		@Override
		public boolean isNull() {
			return true;
		}

		@Override
		public void display() {
		}
	}
}
