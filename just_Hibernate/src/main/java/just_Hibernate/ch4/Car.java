package just_Hibernate.ch4;

public class Car {
	private int id;
	private String name = null;
	private String color = null;
	
	public Car() {
		
	}
	
	public Car(String name, String color) {
		this.name = name;
		this.color = color;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String coler) {
		this.color = coler;
	}
	
	

}
