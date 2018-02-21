package proxy;

import java.io.*;
import java.net.*;
import java.util.*;

interface State {
	public String getApplication();
	public String checkApplication();
	public String rentApartment();
	public String dispenseKeys();
}

class WaitingState implements State {
	AutomatInterface automat;
	
	public WaitingState(AutomatInterface a) {
		automat = a;
	}

	@Override
	public String getApplication() {
		automat.setState(automat.getGotApplicationState());
		return "Thanks for the application.";
	}

	@Override
	public String checkApplication() {
		return "You have to submit an application.";
	}

	@Override
	public String rentApartment() {
		return "You have to submit an application.";
	}

	@Override
	public String dispenseKeys() {
		return "You have to submit an application.";
	}
	
}

class GotApplicationState implements State {
	AutomatInterface automat;
	Random random;
	
	public GotApplicationState(AutomatInterface a) {
		automat = a;
		random = new Random(System.currentTimeMillis());
	}

	@Override
	public String getApplication() {
		return "We already got your application.";
	}

	@Override
	public String checkApplication() {
		int yesno = random.nextInt() % 10;
		
		if (yesno > 4 && automat.getCount() > 0 ) {
			automat.setState(automat.getApartmentRentedState());
			return "Congratulations, you were approved.";
		} else {
			automat.setState(automat.getWaitingState());
			return "Sorry, you were not approved.";
		}
	}

	@Override
	public String rentApartment() {
		return "You must have your application checked.";
	}

	@Override
	public String dispenseKeys() {
		return "You must have your application checked.";
	}
	
}

class ApartmentRentedState implements State {
	AutomatInterface automat;
	
	public ApartmentRentedState(AutomatInterface a) {
		automat = a;
	}

	@Override
	public String getApplication() {
		return "Hang on, we're renting you an apartment.";
	}

	@Override
	public String checkApplication() {
		return "Hang on, we're renting you an apartment.";
	}

	@Override
	public String rentApartment() {
		automat.setCount(automat.getCount() - 1);
		return "Renting you an apartment...";
	}

	@Override
	public String dispenseKeys() {
		if (automat.getCount() <= 0 ) {
			automat.setState(automat.getFullyRentedState());
		} else {
			automat.setState(automat.getWaitingState());
		}
		return "Here are your keys!";
	}
	
}

class FullyRentedState implements State {
	AutomatInterface automat;
	
	public FullyRentedState(AutomatInterface a) {
		automat = a;
	}

	@Override
	public String getApplication() {
		return "Sorry, we're fully rented.";
	}

	@Override
	public String checkApplication() {
		return "Sorry, we're fully rented.";
	}

	@Override
	public String rentApartment() {
		return "Sorry, we're fully rented.";
	}

	@Override
	public String dispenseKeys() {
		return "Sorry, we're fully rented.";
	}
	
}

interface AutomatInterface {
	public void getApplication();
	public void checkApplication();
	public void rentApartment();
	public void setState(State s);
	public State getWaitingState();
	public State getGotApplicationState();
	public State getApartmentRentedState();
	public State getFullyRentedState();
	public int getCount();
	public void setCount(int n);
}


class Automat implements AutomatInterface {
	State waitingState;
	State gotApplicationState;
	State apartmentRentedState;
	State fullyRentedState;
	State state;
	int count;
	
	public Automat(int n) {
		count = n;
		waitingState = new WaitingState(this);
		gotApplicationState = new GotApplicationState(this);
		apartmentRentedState = new ApartmentRentedState(this);
		waitingState = new WaitingState(this);
		state = waitingState;
	}

	@Override
	public void getApplication() {
		System.out.println(state.getApplication());
	}

	@Override
	public void checkApplication() {
		System.out.println(state.checkApplication());
	}

	@Override
	public void rentApartment() {
		System.out.println(state.rentApartment());
		System.out.println(state.dispenseKeys());
	}

	@Override
	public void setState(State s) {
		state = s;
	}

	@Override
	public State getWaitingState() {
		return waitingState;
	}

	@Override
	public State getGotApplicationState() {
		return gotApplicationState;
	}

	@Override
	public State getApartmentRentedState() {
		return apartmentRentedState;
	}

	@Override
	public State getFullyRentedState() {
		return fullyRentedState;
	}

	@Override
	public int getCount() {
		return count;
	}

	@Override
	public void setCount(int n) {
		count = n;
	}
}


class AutomatProxy implements Runnable {
	private Thread thread;
	Socket socket;
	InputStream in;
	PrintWriter out;
	int character;
	
	public AutomatProxy() {
		try {
			System.out.println("....");
			socket = new Socket("127.0.0.1", 8765);
			System.out.println("Connecting....");
			
			in = socket.getInputStream();
			out = new PrintWriter(socket.getOutputStream(), true);
			
			thread = new Thread(this);
			thread.start();
			
		} catch (IOException ioe) {
			System.err.println("The server must be running...");
			System.err.println("Not connected");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		if (socket != null && socket.isConnected()) {
			System.out.println("Connected");
		}
	}
	
	public void gotApplication() {
		out.println("gotApplication");
	}
	
	public void checkApplication() {
		out.println("checkApplication");
	}
	
	public void rentApartment() {
		out.println("rentApartment");
	}

	@Override
	public void run() {
		try {
			while ( (character = in.read()) != -1) {
				System.out.print((char) character);
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
}


class AutomatServer implements Runnable, AutomatInterface {

	State waitingState;
	State gotApplicationState;
	State apartmentRentedState;
	State fullyRentedState;
	State state;
	int count;
	private Thread thread;
	ServerSocket socket;
	PrintWriter out;
	Socket communicationSocket;
	
	public AutomatServer() {
		count = 9;
		waitingState = new WaitingState(this);
		gotApplicationState = new GotApplicationState(this);
		apartmentRentedState = new ApartmentRentedState(this);
		waitingState = new WaitingState(this);
		state = waitingState;
		
		try {
			socket = new ServerSocket(8765);
			System.out.println("ServerSocket()");
			//communicationSocket = socket.accept();
			//out = new PrintWriter (communicationSocket.getOutputStream(), true);
			
			//thread = new Thread(this);
			//thread.start();
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	@Override
	public void getApplication() {
		out.println(state.getApplication());
	}

	@Override
	public void checkApplication() {
		out.println(state.checkApplication());
	}

	@Override
	public void rentApartment() {
		out.println(state.rentApartment());
		out.println(state.dispenseKeys());
	}

	@Override
	public void setState(State s) {
		state = s;
		
	}

	@Override
	public State getWaitingState() {
		return waitingState;
	}

	@Override
	public State getGotApplicationState() {
		return gotApplicationState;
	}

	@Override
	public State getApartmentRentedState() {
		return apartmentRentedState;
	}

	@Override
	public State getFullyRentedState() {
		return fullyRentedState;
	}

	@Override
	public int getCount() {
		return count;
	}

	@Override
	public void setCount(int n) {
		count = n;
	}

	@Override
	public void run() {
		String incomingString;
		try {
			communicationSocket = socket.accept();
			out = new PrintWriter (communicationSocket.getOutputStream(), true);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(communicationSocket.getInputStream()));
			while ((incomingString = in.readLine()) != null) {
				if (incomingString.equals("gotApplication")) {
					getApplication();
				}
				else if (incomingString.equals("checkApplication")) {
					checkApplication();
				}
				else if (incomingString.equals("rentApartment")) {
					rentApartment();
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}



class ProxyTest {
	AutomatProxy automatProxy;

	public static void main(String[] args) {
		AutomatServer server = new AutomatServer();
		Thread thread = new Thread(server);
		thread.start();
		System.out.println("dddd");
		ProxyTest t = new ProxyTest();
	}
	
	public ProxyTest() {
		automatProxy = new AutomatProxy();
		
		automatProxy.gotApplication();
		automatProxy.checkApplication();
		automatProxy.rentApartment();
		
	}
}