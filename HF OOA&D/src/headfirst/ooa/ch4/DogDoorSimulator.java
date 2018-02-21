package headfirst.ooa.ch4;


public class DogDoorSimulator {
	
	public static void main(String[] args) {
		DogDoor door = new DogDoor();
		door.addAllowedBark(new Bark("rowlf"));
		door.addAllowedBark(new Bark("rooooowlf"));
		door.addAllowedBark(new Bark("rawlf"));
		door.addAllowedBark(new Bark("woof"));
		
		BarkRecongnizer recongnizer = new BarkRecongnizer(door);
		
		// sinulate the hardware hearing a bark
		System.out.println("Bruce starts barking.");
		recongnizer.recongnize(new Bark("rowlf"));
		
		System.out.println("\nBruce has gone outside...");
		
		try {
			Thread.currentThread().sleep(10000);
		} catch (InterruptedException e) {
		}
		
		System.out.println("\nBuruce's all done...");
		System.out.println("...but he's stuck outside!");
		
		
		// sinulate the hardware hearing a bark (not Bruce!)
		Bark smallDogBark = new Bark("yip");
		System.out.println("A small dog starts barking");
		recongnizer.recongnize(smallDogBark);
		
		try {
			Thread.currentThread().sleep(5000);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
		
		// simulate the hardware hearing a bark again
		System.out.println("Bruce starts barking.");
		recongnizer.recongnize(new Bark("rooowlf"));
		
		System.out.println("\nBurce's back inside...");
	}

}
