package builder;

import java.io.*;
import java.util.*;

interface RobotBuilder {
	public void addStart();
	public void addGetParts();
	public void addAssemble();
	public void addTest();
	public void addStop();
	public RobotBuildable getRobot();
}


class CookieRobotBuilder implements RobotBuilder {
	CookieRobotBuildable robot;
	ArrayList<Integer> actions;
	
	public CookieRobotBuilder() {
		robot = new CookieRobotBuildable();
		actions = new ArrayList<Integer>();
	}

	@Override
	public void addStart() {
		actions.add(new Integer(1));
	}

	@Override
	public void addGetParts() {
		actions.add(new Integer(2));
	}

	@Override
	public void addAssemble() {
		actions.add(new Integer(3));
	}

	@Override
	public void addTest() {
		actions.add(new Integer(4));
	}

	@Override
	public void addStop() {
		actions.add(new Integer(5));
	}

	public RobotBuildable getRobot() {
		robot.loadActions(actions);
		return robot;
	}
}


interface RobotBuildable {
	public void go();
}


class CookieRobotBuildable implements RobotBuildable {
	ArrayList<Integer> actions;
	
	public CookieRobotBuildable() {
	}
	
	public void loadActions(ArrayList a) {
		actions = a;
	}

	@Override
	public final void go() {
		Iterator<Integer> itr = actions.iterator();
		while(itr.hasNext()) {
			switch ((Integer) itr.next()) {
			case 1:
				start();
				break;
			case 2:
				getParts();
				break;
			case 3:
				assemble();
				break;
			case 4:
				test();
				break;
			case 5:
				stop();
				break;
			}
		}
	}

	private void stop() {
		System.out.println("Stopping...");
	}

	private void test() {
		System.out.println("Crunching a cookie...");
	}

	private void assemble() {
		System.out.println("Baking a cookie...");
	}

	private void getParts() {
		System.out.println("Getting flour and sugar...");
	}

	private void start() {
		System.out.println("Starting...");
	}

}
public class BuilderTest {

	public static void main(String[] args) {
		RobotBuilder builder;
		RobotBuildable robot;
		
		String response = "a";
		
		System.out.println("Do you want a cookie robot [c] or an automotive one [a]? ");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			response = reader.readLine();
		}catch (IOException e) {
			System.err.println("Error");
		}
		
		/*
		if (response.equals("c")) {
			builder = new CookieRobotBuilder();
		} else {
			//builder = new AutomotiveRobotBuilder
		}
		*/
		
		builder = new CookieRobotBuilder();
		
		builder.addStart();
		builder.addTest();
		builder.addAssemble();
		builder.addStop();
		
		robot = builder.getRobot();
		robot.go();
	}

}
