package headfirst.ooa.ch5.part2;

public enum InstrumentType {

	GUITAR, BANJO, DOBRO, FIDDLE, BASS, MANDOLIN;

	public String toString() {
		switch (this) {
		case GUITAR:
			return "Guitar";
		case BANJO:
			return "Guitar";
		case DOBRO:
			return "Guitar";
		case FIDDLE:
			return "Guitar";
		case BASS:
			return "Guitar";
		case MANDOLIN:
			return "Guitar";
		default:
			return "Unspecified";
		}
	}

}
