package ch10;

import java.util.HashMap;
import java.util.Map;

public abstract class Command {

	public static final Command FORWARD = new Forward();
	public static final Command BACKWARD = new Backward();
	public static final Command TURN_RIGHT = new Right();
	public static final Command TURN_LEFT = new Left();
	private static final Map<String, Command> commandNameMap = new HashMap<>();
	
	static {
		commandNameMap.put(FORWARD.name, FORWARD);
		commandNameMap.put(BACKWARD.name, BACKWARD);
		commandNameMap.put(TURN_LEFT.name, TURN_LEFT);
		commandNameMap.put(TURN_RIGHT.name, TURN_RIGHT);
	}
	
	private final String name;

	protected Command(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public static Command parseCommand(String name) throws InvalidCommandException {
		if (!commandNameMap.containsKey(name)) {
			throw new InvalidCommandException(name);
		}
		return commandNameMap.get(name);
	}
	
	public abstract void execute(Robot robot);
	
	private static class Forward extends Command {
		public Forward() {
			super("forward");
		}

		@Override
		public void execute(Robot robot) {
			robot.forward();
		}
	}
	
	private static class Backward extends Command {
		public Backward() {
			super("backward");
		}

		@Override
		public void execute(Robot robot) {
			robot.backward();
		}
	}
	
	private static class Right extends Command {
		public Right() {
			super("right");
		}

		@Override
		public void execute(Robot robot) {
			robot.right();
		}
	}

	private static class Left extends Command {
		public Left() {
			super("left");
		}

		@Override
		public void execute(Robot robot) {
			robot.left();
		}
	}



}
