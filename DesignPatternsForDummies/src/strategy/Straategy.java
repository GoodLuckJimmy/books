package strategy;

interface GoAlgorithm {
	public void go();
}

class GoByDrivingAlgorithm implements GoAlgorithm {
	public void go() {
		System.out.println("Now I'm driving.");
	}
}

class GoByFlyingAlgorithm implements GoAlgorithm {
	public void go() {
		System.out.println("Now I'm flying at 200mph");
	}
}

class GoByFlyingFastAlgorithm implements GoAlgorithm {
	public void go() {
		System.out.println("Now I'm flying fast.");
	}
}

abstract class Vehicle {
	private GoAlgorithm goAlgorithm;
	public Vehicle(){}
	public void setGoAlgorithm(GoAlgorithm algorithm) {
		goAlgorithm = algorithm;
	}
	public void go() {
		goAlgorithm.go();
	}
}

class StreetRacer extends Vehicle {
	public StreetRacer() {
		setGoAlgorithm(new GoByDrivingAlgorithm());
	}
}

class FormulaOne extends Vehicle {
	public FormulaOne() {
		setGoAlgorithm(new GoByDrivingAlgorithm());
	}
}

class Helicopter extends Vehicle {
	public Helicopter() {
		setGoAlgorithm(new GoByFlyingAlgorithm());
	}
}

class Jet extends Vehicle {
	public Jet() {
		setGoAlgorithm(new GoByFlyingFastAlgorithm());
	}
}

//class StartTheRace {
//	public static void main(String[] args) {
//		StreetRacer streetRacer = new StreetRacer();
//		FormulaOne formulaOne = new FormulaOne();
//		Helicopter helicopter = new Helicopter();
//		Jet jet = new Jet();
//		
//		streetRacer.go();
//		formulaOne.go();
//		helicopter.go();
//		jet.go();
//	}
//}

// run time에서 dynamically하게 변경
class Strategy {
	public static void main(String[] args) {
		Jet jet = new Jet();
		
		jet.setGoAlgorithm(new GoByDrivingAlgorithm());
		jet.go();
		
		jet.setGoAlgorithm(new GoByFlyingFastAlgorithm());
		jet.go();

		jet.setGoAlgorithm(new GoByDrivingAlgorithm());
		jet.go();
		
	}
}