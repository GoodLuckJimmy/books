package just_Hibernate.ch3;

import java.io.Serializable;

/*
 *  CoursePK3클래스는 모든 필수 키본키 속성을 가지고 있다고 가정
 */
public class CoursePK3 implements Serializable {
	private String tutor = null;
	private String title = null;

	public void setTitle(String title) {
		this.title = title;
	}

	public void setTutor(String tutor) {
		this.tutor = tutor;
	}
	
	public CoursePK3() {
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

}
