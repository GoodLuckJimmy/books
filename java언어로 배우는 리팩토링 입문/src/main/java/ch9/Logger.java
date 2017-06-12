//package ch9;
//
//public class Logger {
//	private State _state;
//	
//	
//	public Logger() {
//		setState(State.STATE_STOPPED);
//	}
//	
//	public State getState() {
//		return _state;
//	}
//
//	public void setState(State state) {
//		_state = state;
//	}
//	
//	public void start() {
//		_state.start();
//		setState(State.STATE_LOGGING);
//	}
//	
//	public void stop() {
//		_state.stop();
//		setState(State.STATE_STOPPED);
//	}
//	
//	public void log(String info) {
//		_state.log(info);
//	}
//
///*
//	public void start() {
////		switch (state) {
//		switch (getState()) {
//		case STATE_STOPPED:
//			System.out.println("** START LOGGING");
////			state = STATE_LOGGING;
//			setState(STATE_LOGGING);
//			break;
//		case STATE_LOGGING:
//			// do nothing
//			break;
//		default:
////			System.out.println("Invalid state: " + state);
//			System.out.println("Invalid state: " + getState());
//		}
//	}
//	
//	public void stop() {
//		switch (getState()) {
//		case STATE_STOPPED:
//			// do nothing
//			break;
//		case STATE_LOGGING:
//			System.out.println("** STOP LOGGING");
////			state = STATE_STOPPED;
//			setState(STATE_STOPPED);
//			break;
//		default:
////			System.out.println("Invalid state: " + state);
//			System.out.println("Invalid state: " + getState());
//		}
//	}
//	
//	public void log(String info) {
//		switch (getState()) {
//		case STATE_STOPPED:
//			System.out.println("Ignoring: " + info);
//			break;
//		case STATE_LOGGING:
//			System.out.println("Logging: " + info);
////			state = STATE_STOPPED;
//			break;
//		default:
////			System.out.println("Invalid state: " + state);
//			System.out.println("Invalid state: " + getState());
//		}
//	}
//*/
//}
