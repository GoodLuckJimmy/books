package singleton;

class StudentThreaded {
	
	int id;
	int score;
	double averageScore;
	private static StudentThreaded singleObject  = new StudentThreaded();
	
	private StudentThreaded() {}
	
	public static StudentThreaded getInstance() {
		return singleObject;
	}

	String name;
		public String getName() {
		return name;
	}
	
	public int getId() {
		return id;
	}
	
	public int getScore() {
		return score;
	}
	
	public double getAverageScore() {
		return averageScore;
	}

	public double getStanding() { // 시스템 리소스를 많이 잡아먹는 놈이라 가정. 비용이 큰 자원을 공통으로 사용할 수 있도록 만드는 패턴
		return (((double) score) / averageScore - 1.0) * 100.0; 
	}
	
	public StudentThreaded(double a) {
		averageScore = a;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public void setId(int i) {
		id = i;
	}
	
	public void setScore(int s) {
		score = s;
	}

	public void setAverageScore(double a) {
		averageScore = a;
	}
	
}


public class FlyweightTest implements Runnable {
	Thread thread;

	public static void main(String[] args) {
		FlyweightTest t = new FlyweightTest();
	}

	public FlyweightTest() {
		//String names[] = {"Ralph", "Alice", "Sam"};
		//int ids[] = {1001, 1002, 1003};
		int scores[] = {45, 55, 65};
		
		double total = 0;
		for (int i = 0; i < scores.length; ++i) {
			total += scores[i];
		}
		
		double averageScore = total / scores.length;
		
		StudentThreaded student = StudentThreaded.getInstance();
		
		student.setAverageScore(averageScore);
		student.setName("Ralph");
		student.setId(1002);
		student.setScore(45);
		
		thread = new Thread(this, "second");
		thread.start();
			
		System.out.println("Name: " + student.getName() + ", Standing: " + Math.round(student.getStanding()));
	}
		

	@Override
	public void run() {
		StudentThreaded student = StudentThreaded.getInstance();
		System.out.println("Name: " + student.getName() + ", Standing: " + Math.round(student.getStanding()));
	}

}
