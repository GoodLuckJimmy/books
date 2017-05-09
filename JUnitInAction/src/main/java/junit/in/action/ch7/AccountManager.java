package junit.in.action.ch7;

public interface AccountManager {
	Account findAccountForUser(String userId);
	void updateAccount(Account account);
}
