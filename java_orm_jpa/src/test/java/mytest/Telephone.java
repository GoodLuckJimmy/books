package mytest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "phones")
public class Telephone {
	public static enum Type {
		HOME, OFFICE, MOBILE;
	}

	@Id
	@GeneratedValue
	private long id;

	private String number;

	private Type type;
}
