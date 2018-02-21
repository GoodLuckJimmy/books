package just_Hibernate.ch5.manyToMany;

import java.util.Set;

public class Course {
		
	private int id = 0;
	private String title = null;
	
	private Set<Student> students = null;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

}
