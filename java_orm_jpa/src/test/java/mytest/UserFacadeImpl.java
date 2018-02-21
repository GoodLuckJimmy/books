package mytest;

import java.util.List;

import lombok.Data;

@Data
public class UserFacadeImpl implements UserFacade {

	private static final String TELEPHONE_STRING_FORMAT = "%s (%s)";
	private UserDao userDao;
	
	@Override
	public UserDto getUserById(long id) {

		User user = userDao.getUserById(id);

		UserDto dto = new UserDto();
		dto.setFirstName(user.getFirstName());
		dto.setLastName(user.getLastName());
		dto.setUsername(user.getUsername());

		List<String> telephoneDtos = dto.getTelephones();
		for (Telephone telephone : user.getTelephones() ) {
			String telephoneDto = String.format(TELEPHONE_STRING_FORMAT, telephone.getNumber(), telephone.getType());
			telephoneDtos.add(telephoneDto);
		}

		return dto;
	}

}
