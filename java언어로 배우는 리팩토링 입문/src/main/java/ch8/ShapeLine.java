package ch8;

public class ShapeLine extends Shape {

	public ShapeLine(int startx, int starty, int endx, int endy) {
		super(startx, starty, endx, endy);
	}
	
	@Override
	public int getTypecode() {
		return Shape.TYPECODE_LINE;
	}

	@Override
	public void draw() {
		drawLine();
	}

	@Override
	public String getName() {
		return "LINE";
	}
	
	private void drawLine() {
		System.out.println("drawLine: " + this.toString());
	}
}
