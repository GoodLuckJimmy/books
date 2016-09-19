package state;

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
public class AdvencedStateTest {

	Automat automat;
	
	public static void main(String[] args) {
		AdvencedStateTest t = new AdvencedStateTest();
	}
	
	public AdvencedStateTest() {
		automat = new Automat(9);
		automat.getApplication();
		automat.checkApplication();
		automat.rentApartment();
	}

}
