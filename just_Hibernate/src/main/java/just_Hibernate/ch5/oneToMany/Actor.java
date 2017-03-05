package just_Hibernate.ch5.oneToMany;

public class Actor {
	private int id = 0;
	private String firstName = null; 
	private String lastName = null;
	private String shortName = null;
	
	public Actor(String firstName, String lastName, String shortName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.shortName = shortName;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	

}
