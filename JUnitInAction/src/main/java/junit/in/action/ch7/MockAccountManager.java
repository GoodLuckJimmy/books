package junit.in.action.ch7;

import java.util.HashMap;
import java.util.Map;

public class MockAccountManager implements AccountManager {
	
	private Map<String, Account> accounts = new HashMap<>();

	public void addAccount(String userId, Account account) {
		this.accounts.put(userId, account);
	}

	@Override
	public Account findAccountForUser(String userId) {
		return this.accounts.get(userId);
	}

	@Override
	public void updateAccount(Account account) {
		// do nothing
	}

}
