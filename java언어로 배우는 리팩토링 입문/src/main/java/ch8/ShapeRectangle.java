package ch8;

public class ShapeRectangle extends Shape {

	public ShapeRectangle(int startx, int starty, int endx, int endy) {
		super(startx, starty, endx, endy);
	}

	@Override
	public int getTypecode() {
		return Shape.TYPECODE_RECTANGLE;
	}

	@Override
	public String getName() {
		return "RECTANGLE";
	}

	@Override
	public void draw() {
		drawRectangle();
	}
	
	private void drawRectangle() {
		System.out.println("drawRectangle: " + this.toString());
	}

}
