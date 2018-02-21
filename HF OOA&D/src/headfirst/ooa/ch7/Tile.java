package headfirst.ooa.ch7;

import java.util.LinkedList;
import java.util.List;

public class Tile {
	
	private List<Unit> units;
	
	public Tile() {
		units = new LinkedList<>();
	}


	protected void addUnit(Unit unit) {
		units.add(unit);
	}

	protected void removeUnit(Unit unit) {
		units.remove(unit);
	}

	protected void removeUnits() {
		// TODO Auto-generated method stub
		
	}

	protected List<Unit> getUnits() {
		// TODO Auto-generated method stub
		return null;
	}

}
