package just_Hibernate.ch3;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TBL_EMPLOYEE")
public class Employee {
	@Id
	@Column(name="EMPLOYEE_ID", nullable=false, unique=true)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int empoyeeId = 0;

}
