package just_Hibernate.ch3;

import java.io.Serializable;

import javax.persistence.Embeddable;

/*
 * Course 클래스를 위한 복합 key용 클래스
 */
@Embeddable
public class CoursePK implements Serializable {
	
	private String tutor = null;
	private String title = null;
	
	public CoursePK() {
		// 필수
	}
	
	public String getTutor() {
		return tutor;
	}

	public void setTutor(String tutor) {
		this.tutor = tutor;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int hashCode() {
		// 필수
		return super.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		//  필수
		return super.equals(obj);
	}

}
