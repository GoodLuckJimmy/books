package just_Hibernate.ch5.oneToOne.어노테이션사용;

import javax.persistence.*;

@Entity
@Table(name="CAR_ONE2ONE_ANN")
public class Car {

	@Id
	@Column(name="CAR_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	private String color;
	
	@OneToOne (cascade=CascadeType.ALL)
	@JoinColumn(name="ENGINE_ID")
	private Engine engine;

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

	public void setColor(String color) {
		this.color = color;
	}

	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}
}
