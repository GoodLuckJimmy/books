package singleton;

class DatabaseThreaded {
	//private static DatabaseThreaded singleObject;
	private static DatabaseThreaded singleObject = new DatabaseThreaded("products");
	private int record;
	private String name;
	
	private DatabaseThreaded(String n) {
		name = n;
		record = 0;
	}
	
	public static synchronized DatabaseThreaded getInstance(String n) {
		// singleObject 최초에 new안했을 경우 
		// if (singleObject == null) {
		// 	singleObject = new DatabaseThreaded(n);
		// }
		
		return singleObject;
	}
	
	public void editRecord(String operation) {
		System.out.println("Performing a " + operation + " operaton on record " + record + " in database " + name);
	}
	
	public String getName() {
		return name;
	}
}


class SingletonTest implements Runnable {
	Thread thread;
	
	public static void main(String[] args) {
		SingletonTest t = new SingletonTest();
	}

	public SingletonTest() {
		DatabaseThreaded database;
		
		database = DatabaseThreaded.getInstance("products");
		
		thread = new Thread(this, "second");
		thread.start();
		
		System.out.println("This is the " + database.getName() + " database.");
	}
	@Override
	public void run() {
		DatabaseThreaded database;
		database = DatabaseThreaded.getInstance("employees");	
		System.out.println("This is the " + database.getName() + " database.");
	}

}
