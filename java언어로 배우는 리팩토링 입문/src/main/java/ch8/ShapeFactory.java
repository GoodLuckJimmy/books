package ch8;

public abstract class ShapeFactory {

	public abstract Shape create(int startx, int starty, int endx, int endy);
	
	public static class LineFactory extends ShapeFactory {
		public static final ShapeFactory factory = new LineFactory();
		@Override
		public Shape create(int startx, int starty, int endx, int endy) {
			return new ShapeLine(startx, starty, endx, endy);
		}
	}
	
	public static class RectangleFactory extends ShapeFactory {
		public static final ShapeFactory factory = new RectangleFactory();
		@Override
		public Shape create(int startx, int starty, int endx, int endy) {
			return new ShapeRectangle(startx, starty, endx, endy);
		}
	}
	
	public static class OvalFactory extends ShapeFactory {
		public static final ShapeFactory factory = new OvalFactory();
		@Override
		public Shape create(int startx, int starty, int endx, int endy) {
			return new ShapeOval(startx, starty, endx, endy);
		}
	}

}
