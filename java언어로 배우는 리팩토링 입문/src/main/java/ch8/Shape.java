package ch8;

public abstract class Shape {
	public static final int TYPECODE_LINE = 0;
	public static final int TYPECODE_RECTANGLE = 1;
	public static final int TYPECODE_OVAL = 2;

	private final int startx;
	private final int starty;
	private final int endx;
	private final int endy;
	
	// Factory Method
	public static Shape createShape(ShapeFactory factory, int startx, int starty, int endx, int endy) {
		return factory.create(startx, starty, endx, endy);
		/*
		switch (typecode) {
		case TYPECODE_LINE:
			return new ShapeLine(startx, starty, endx, endy);
		case TYPECODE_RECTANGLE:
			return new ShapeRectangle(startx, starty, endx, endy);
		case TYPECODE_OVAL:
			return new ShapeOval(startx, starty, endx, endy);
			default:
				throw new IllegalArgumentException("typecode = " + typecode);
		}
		*/
	}

	public Shape(int startx, int starty, int endx, int endy) {
		this.startx = startx;
		this.starty = starty;
		this.endx = endx;
		this.endy = endy;
	}

	public abstract int getTypecode();

	public abstract String getName();

	public String toString() {
		return "[ " + getName() + ", " + "(" + startx + ", " + starty + ")-" + "(" + endx + ", " + endy + ") ]";
	}

	public abstract void draw();
}
