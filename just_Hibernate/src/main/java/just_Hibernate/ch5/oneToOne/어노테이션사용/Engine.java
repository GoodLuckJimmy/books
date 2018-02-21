package just_Hibernate.ch5.oneToOne.어노테이션사용;

import javax.persistence.*;

@Entity
@Table(name="ENGINE_ONE2ONE_ANN")
public class Engine {
	@Id
	@Column(name="ENGINE_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id = 0;
	private String make = null;
	private String model = null;
	private String size = null;
	
	@OneToOne(mappedBy="car")
	private Car car = null;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}


}
