package headfirst.ooa.ch7;

import java.util.ArrayList;
import java.util.List;

public class Board {
	
	private int width, height;
	private List<List<Tile>> tiles;
	
	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		initialize();
	}
	
	private void initialize() {
		tiles = new ArrayList<List<Tile>>(width);
		for (int i = 0; i < width; i++) {
			tiles.add(i, new ArrayList<Tile>(height));
			for (int j = 0; i < height; j++) {
				((ArrayList<Tile>)tiles.get(i)).add(j, new Tile());
			}
		}
	}
	
	public Tile getTile(int x, int y) {
		return (Tile) ((ArrayList<Tile>)tiles.get(x-1)).get(y-1);
	}
	
	public void addUnit(Unit unit, int x, int y) {
		Tile tile = getTile(x,y);
		tile.addUnit(unit);
	}
	
	public void removeUnit(Unit unit, int x, int y) {
		Tile tile = getTile(x, y);
		tile.removeUnit(unit);
	}
	
	public void removeUnit(int x, int y) {
		Tile tile = getTile(x, y);
		tile.removeUnits();
	}
	
	public List<Unit> getUnits(int x, int y) {
		return getTile(x, y).getUnits();
	}

}
