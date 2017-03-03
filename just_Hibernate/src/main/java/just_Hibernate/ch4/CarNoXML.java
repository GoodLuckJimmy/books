package just_Hibernate.ch4;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="CAR_LIST_ANN")
@Table(name="CAR_LIST_ANN")
public class CarNoXML {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CAR_ID")
	private int id;
	private String name = null;
	private String color = null;
	
	public CarNoXML() {
		
	}
	
	public CarNoXML(String name, String color) {
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
