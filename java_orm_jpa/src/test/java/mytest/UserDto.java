package mytest;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDto {
	private long id;
	private String username;
	private String firstName;
	private String lastName;
	List<String> telephones = new ArrayList<String>();

}
