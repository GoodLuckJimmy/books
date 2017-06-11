package ch9;

public abstract class Logger {
//	public static final int STATE_STOPPED = 0;
//	public static final int STATE_LOGGING = 1;
//	private int state;
	private State _state;
	
	private enum State {
		STATE_STOPPED {
			@Override
			public void start() {
				System.out.println("** START LOGGING **");
			}
			@Override
			public void stop() {
				// do nothing
			}
			@Override
			public void log(String info) {
				System.out.println("Ignoring: " + info);
			}
		},
		STATE_LOGGING {
			@Override
			public void start() {
				// do nothing
			}
			@Override
			public void stop() {
				System.out.println("** STOP LOGGING **");
			}
			@Override
			public void log(String info) {
				System.out.println("Ignoring: " + info);
			}
		};
		
		public abstract void start();
		public abstract void stop();
		public abstract void log(String info);
	}
	
	public Logger() {
//		state = STATE_STOPPED;
		setState(State.STATE_STOPPED);
	}
	
//	public int getState() {
	public State getState() {
//		return _state;
//		return _state.getTypeCode();
		return _state;
	}

//	public void setState(int state) {
	public void setState(State state) {
		/*
//		this.state = state;
		switch (state) {
		case STATE_STOPPED:
			_state = new StateStopped();
			break;
		case STATE_LOGGING:
			_state = new StateLogging();
			break;
		default:
			System.out.println("Invalid state: " + state);
		}
		*/
		_state = state;
	}
	
	public void start() {
		_state.start();
		setState(State.STATE_LOGGING);
	}
	
	public void stop() {
		_state.stop();
		setState(State.STATE_STOPPED);
	}
	
	public void log(String info) {
		_state.log(info);
	}

/*
	public void start() {
//		switch (state) {
		switch (getState()) {
		case STATE_STOPPED:
			System.out.println("** START LOGGING");
//			state = STATE_LOGGING;
			setState(STATE_LOGGING);
			break;
		case STATE_LOGGING:
			// do nothing
			break;
		default:
//			System.out.println("Invalid state: " + state);
			System.out.println("Invalid state: " + getState());
		}
	}
	
	public void stop() {
		switch (getState()) {
		case STATE_STOPPED:
			// do nothing
			break;
		case STATE_LOGGING:
			System.out.println("** STOP LOGGING");
//			state = STATE_STOPPED;
			setState(STATE_STOPPED);
			break;
		default:
//			System.out.println("Invalid state: " + state);
			System.out.println("Invalid state: " + getState());
		}
	}
	
	public void log(String info) {
		switch (getState()) {
		case STATE_STOPPED:
			System.out.println("Ignoring: " + info);
			break;
		case STATE_LOGGING:
			System.out.println("Logging: " + info);
//			state = STATE_STOPPED;
			break;
		default:
//			System.out.println("Invalid state: " + state);
			System.out.println("Invalid state: " + getState());
		}
	}
*/
}
