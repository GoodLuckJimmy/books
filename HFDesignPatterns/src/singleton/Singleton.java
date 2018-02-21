package singleton;

public class Singleton {
	private volatile static Singleton uniqueInstance;
	private Singleton() {}
	
	public static Singleton getInstance() {
		if (uniqueInstance == null) {
			synchronized (Singleton.class) {
				if (uniqueInstance == null) { // DCL(double checking locking)
					uniqueInstance = new Singleton();
				}
			}
		}
		return uniqueInstance;
	}
}
