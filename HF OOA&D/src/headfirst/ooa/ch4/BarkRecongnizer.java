package headfirst.ooa.ch4;

import java.util.Iterator;
import java.util.List;

public class BarkRecongnizer {
	
	private DogDoor door;

	public BarkRecongnizer(DogDoor door) {
		this.door = door;
	}

	public void recongnize(Bark bark) {
		System.out.println(" BarkRecongnizer: Heard a '" +
				bark.getSound() + "'");
		List<Bark> allowedBarks = door.getAllowedBarks();
		
		for (Iterator<Bark> i = allowedBarks.iterator(); i.hasNext(); ) {
			Bark allowedBark = i.next();
			if (allowedBark.equals(bark)) {
				door.open();
				return;
			}
		}
		
		System.out.println("This dog is not allowed");
	}

}
