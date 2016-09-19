package template;


abstract class RobotTemplate {
	public final void go() {
		start();
		getParts();
		assemble();
		// hook 추가 
		if (testOK()) {
			test();
		}
		stop();
	}
	
	public boolean testOK() {
		return true;
	}

	public void start() {
		System.out.println("Starting...");
	}
	
public void getParts() {
		System.out.println("Getting parts...");
	}
	
	public void assemble() {
		System.out.println("Assembling...");
	}
	
	public void test() {
		System.out.println("Testing...");
	}
	
	public void stop() {
		System.out.println("Stopping...");
	}
}


class AutomotiveRobot extends RobotTemplate {
	
	private String name;
	
	public AutomotiveRobot(String n) {
		name = n;
	}
	
	public void getParts() {
		System.out.println("Getting a carburetor...");
	}
	
	public void assemble() {
		System.out.println("Installing the carburetor...");
	}
	
	public void test() {
		System.out.println("Revving the engine...");
	}
	
	public String getName() {
		return name;
	}
}


class CookieRobot extends RobotTemplate {
	
	private String name;
	
	public CookieRobot(String n) {
		name = n;
	}
	
	public void getParts() {
		System.out.println("Getting flour and sugar...");
	}
	
	public void assemble() {
		System.out.println("Baking a cookie...");
	}
	
	public void test() {
		System.out.println("Crunching a cookie...");
	}
	
	public String getName() {
		return name;
	}
	
	public boolean testOK() {
		return false;
	}
}


class TemplateTest {

	public static void main(String[] args) {
		AutomotiveRobot automotiveRobot = new AutomotiveRobot("Automotive Robot");
		CookieRobot cookieRobot = new CookieRobot("Cookie Robot");
		System.out.println(automotiveRobot.getName() + ":");
		automotiveRobot.go();
		System.out.println();
		System.out.println(cookieRobot.getName() + ":");
		cookieRobot.go();
	}

}
