package just_Hibernate.ch3;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/*
 * 복합키 사용예3
 * CoursePK3이 모든 기본키를 가지고 있는 복합키 클래스일경우
 * 
 */

@IdClass(value=CoursePK3.class)
@Entity
@Table(name="COURSE_ANNOTATION")
public class Course3 {
	// 복합키 식별자를 중복선언해야함. 즉 CoursePK3의 키를 다시 선언해야함.
	@Id
	private String title = null;
	@Id
	private String tutor = null;
	

}

