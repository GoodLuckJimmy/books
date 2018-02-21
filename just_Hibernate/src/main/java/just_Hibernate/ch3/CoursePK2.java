package just_Hibernate.ch3;

public class CoursePK2 {
	private String tutor = null;
	private String title = null;

	public void setTitle(String title) {
		this.title = title;
	}

	public void setTutor(String tutor) {
		this.tutor = tutor;
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
