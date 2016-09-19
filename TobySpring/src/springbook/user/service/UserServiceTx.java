package springbook.user.service;

import org.springframework.transaction.*;
import org.springframework.transaction.support.*;

import springbook.user.domain.*;

public class UserServiceTx implements UserService {
	
	UserService userService;
	PlatformTransactionManager transactionManager;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	@Override
	public void add(User user) {
		userService.add(user);
	}

	@Override
	public void upgradeLevels() {
		TransactionStatus status = this.transactionManager.getTransaction(new DefaultTransactionDefinition());
		try {
			userService.upgradeLevels();
			this.transactionManager.commit(status);
		} catch (RuntimeException e) {
			this.transactionManager.rollback(status);
			throw e;
		}
	}
	
	
}
