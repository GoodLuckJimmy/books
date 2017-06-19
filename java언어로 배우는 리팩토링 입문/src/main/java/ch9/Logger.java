package ch9;


public class Logger {
    public static final int STATE_STOPPED = 0;
    public static final int STATE_LOGGING = 1;

    private State state;

    public Logger() {
        this.state = new StateStopped();
        setState(STATE_STOPPED);
    }

    public int getState() {
        return state.getTypeCode();
    }

    public void setState(int _state) {
        switch (_state) {
            case STATE_STOPPED:
                state = new StateStopped();
                break;
            case STATE_LOGGING:
                state = new StateLogging();
                break;
                default:
                    System.out.println("Invalid state: " + state);
        }
    }

    public void start() {
            switch (getState()) {
                case STATE_STOPPED:
                    System.out.println("** START LOGGING **");
                    setState(STATE_LOGGING);
                    break;
                case STATE_LOGGING:
                    break;
                default:
                    System.out.println("Invalid state: " + state);
            }

    }

    public void stop() {
        switch (getState()) {
            case STATE_STOPPED:
                break;
            case STATE_LOGGING:
                System.out.println("** STOP LOGGING **");
                setState(STATE_STOPPED);
                break;
            default:
                System.out.println("Invalid state: " + state);
        }

    }

    public void log(String info) {
        switch (getState()) {
            case STATE_STOPPED:
                System.out.println("Ignoring: " + info);
                break;
            case STATE_LOGGING:
                System.out.println("** LOGGING: " + info);
                break;
            default:
                System.out.println("Invalid state: " + state);
        }
    }
}
