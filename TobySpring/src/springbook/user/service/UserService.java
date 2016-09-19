package springbook.user.service;

import springbook.user.domain.*;

public interface UserService {
	void add(User user);
	void upgradeLevels();
}
