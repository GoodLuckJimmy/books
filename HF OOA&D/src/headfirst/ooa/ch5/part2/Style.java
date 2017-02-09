package headfirst.ooa.ch5.part2;

public enum Style {
	A, F;

	@Override
	public String toString() {
		switch (this) {
		case A:
			return "A";
		case F:
			return "F";
		default:
			return "";
		}
	}
}
