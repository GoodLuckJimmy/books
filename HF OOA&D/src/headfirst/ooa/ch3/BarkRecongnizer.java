package headfirst.ooa.ch3;

import headfirst.ooa.ch2.DogDoor;

public class BarkRecongnizer {
	
	private DogDoor door;
	
	public BarkRecongnizer(DogDoor door) {
		this.door = door;
	}
	
	public void recongnize(String bark) {
		System.out.println(" BarkRecongnizer: Heard a '" + bark + "'");
		door.open();
	}
}
