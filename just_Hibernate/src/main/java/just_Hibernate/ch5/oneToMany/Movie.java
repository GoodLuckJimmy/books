package just_Hibernate.ch5.oneToMany;

import java.util.Set;

public class Movie {
	
	private int id = 0;
	private String title = null; 
	private Set<Actor> actors = null;
	
	public Movie(String title) {
		this.title = title;
	}
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
	public Set<Actor> getActors() {
		return actors;
	}
	public void setActors(Set<Actor> actors) {
		this.actors = actors;
	}
	
	
}
