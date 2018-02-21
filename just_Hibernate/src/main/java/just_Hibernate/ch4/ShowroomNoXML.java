package just_Hibernate.ch4;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity(name="SHOWROOM_LIST_ANN")
@Table(name="SHOWROOM_LIST_ANN")
public class ShowroomNoXML {

	@Id
	@Column(name="SHOWROOM_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id = 0;
	
	@OneToMany
	@JoinColumn(name="SHOWROOM_ID") // 외래키 설정
	@Cascade(CascadeType.ALL)
	private List<Car> car = null;
	
	private String manager = null;
	private String location = null;
	private List<Car> cars = null;
	
	
	public ShowroomNoXML() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public List<Car> getCars() {
		return cars;
	}
	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
}
