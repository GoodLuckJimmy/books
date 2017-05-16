package junit.in.action.ch18;

import javax.persistence.*;

@Entity
@Table(name="phones")
public class Telephone {
	public static enum Type {
		HOME, OFFICE, MOBILE;
	}
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String number;
	private Type type;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	
}
