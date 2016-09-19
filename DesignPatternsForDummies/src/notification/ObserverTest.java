package notification;

import java.util.*;

interface Observer {
	public void update(String operation, String record);
}

interface  Subject{
	void registerObserver(Observer o);
	void removeObserver(Observer o);
	void notifyObservers();	
}

class Database implements Subject{

	private Vector<Observer> observers;
	private String operation;
	private String record;
	
	Database() {
		observers = new Vector<Observer>();
	}

	@Override
	public void registerObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);
	}

	@Override
	public void notifyObservers() {
		for (int loopIndex = 0; loopIndex < observers.size(); loopIndex++) {
			Observer observer = (Observer)observers.get(loopIndex);
			observer.update(operation, record);
		}
	}
	
	public void editRecord(String operation, String record) {
		this.operation = operation;
		this.record = record;
		notifyObservers();
	}
}


// Observers
class Archiver implements Observer {

	@Override
	public void update(String operation, String record) {
		System.out.println("The archiver says a " + operation + " operation was performed on " + record);
	}
}

class Client implements Observer {

	@Override
	public void update(String operation, String record) {
		System.out.println("The client says a " + operation + " operation was performed on " + record);
	}
}

class Boss implements Observer {

	@Override
	public void update(String operation, String record) {
		System.out.println("The boss says a " + operation + " operation was performed on " + record);
	}
}

class ObserverTest {
	public static void main(String[] args) {
		Database database = new Database();
		
		Archiver archiver = new Archiver();
		Client client = new Client();
		Boss boss = new Boss();
		
		database.registerObserver(archiver);
		database.registerObserver(client);
		database.registerObserver(boss);
		
		database.editRecord("delete", "record 1");
	}
}