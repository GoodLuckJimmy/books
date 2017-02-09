package headfirst.ooa.ch3;

import headfirst.ooa.ch2.DogDoor;
import headfirst.ooa.ch2.Remote;

public class DogDoorSimulator {
	
	public static void main(String[] args) {
		DogDoor door = new DogDoor();
		BarkRecongnizer recongnizer = new BarkRecongnizer(door);
		Remote remote = new Remote(door);
		
		System.out.println("Fido starts barking");
		recongnizer.recongnize("Woof");
		
		System.out.println("\nFido has gone outside...");

		System.out.println("\nFido's all done...");
		
		try {
			Thread.currentThread().sleep(10000);
		} catch (InterruptedException e) {
			
		}
		
		System.out.println("Fido starts barking.");
		recongnizer.recongnize("Woof");
		
		System.out.println("\nFido's back inside...");
	}

}
