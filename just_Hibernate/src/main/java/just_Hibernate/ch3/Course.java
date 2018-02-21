package just_Hibernate.ch3;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * 복합키 사용예1
 * 
 */
@Entity
@Table(name="COURSE_ANNOTATION")
public class Course {
	@Id
	private CoursePK id = null; // 복합키
	private int totalStudents = 0;
	private int registeredStudent = 0;
	
	
	public CoursePK getId() {
		return id;
	}
	public void setId(CoursePK id) {
		this.id = id;
	}
	public int getTotalStudents() {
		return totalStudents;
	}
	public void setTotalStudents(int totalStudents) {
		this.totalStudents = totalStudents;
	}
	public int getRegisteredStudent() {
		return registeredStudent;
	}
	public void setRegisteredStudent(int registeredStudent) {
		this.registeredStudent = registeredStudent;
	}
	

}

/*
class Course사용시() {
	
	private void persist() {
		...
		Course course = new Course();
		CoursePK coursePk = new CoursePK();
		coursePk.setTitle("Computer Science");
		coursePk.setTutor("Prof. Harry Barry");
		course.setId(coursePk);
		course.setTotalStudents(20);
		course.setRegisteredStudent(12);
		session.save(course);
		...
	}
}

*/