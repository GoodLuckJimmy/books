package just_Hibernate.ch3;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * 복합키 사용예2
 * 
 */
@Entity
@Table(name="COURSE_ANNOTATION")
public class Course2 {
	@EmbeddedId // CoursePK2에는 어노테이션 안해도 됨
	private CoursePK2 id = null; // 복합키
	private int totalStudents = 0;
	private int registeredStudent = 0;
	
	public Course2(String title, String tutor) {
		id = new CoursePK2();
		id.setTitle(title);
		id.setTutor(tutor);
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
class Course2사용시() {
	
	private void persist() {
		...
		Course2 course = new Course2("Financial Risk Management", "Harry Barry");
		course.setTotalStudents(20);
		course.setRegisteredStudent(12);
		session.save(course);
		...
	}
}

*/