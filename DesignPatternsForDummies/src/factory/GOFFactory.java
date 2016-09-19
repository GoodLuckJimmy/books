package factory;



abstract class ConnectionFactory {
	public ConnectionFactory() {
	}
	
	protected abstract Connection createConnection(String type);
}

class SecureOracleConnection extends Connection {
	public SecureOracleConnection() {
	}

	public String description() {
		return "Oracle secure";
	}
}

class SecureSqlServerConnection extends Connection {
	public SecureSqlServerConnection() {
	}

	public String description() {
		return "SQL Server secure";
	}
}

class SecureMySqlConnection extends Connection {
	public SecureMySqlConnection() {
	}

	public String description() {
		return "MySQL secure";
	}
}

class SecureFactory extends ConnectionFactory {

	@Override
	protected Connection createConnection(String type) {
		if (type.equals("Oracle")) {
			return new SecureOracleConnection();
		}
		else if (type.equals("SQL Server")) {
			return new SecureSqlServerConnection();
		}
		else {
			return new SecureMySqlConnection();
		}
	}
}
public class GOFFactory {

	public static void main(String[] args) {
		SecureFactory factory;
		factory = new SecureFactory();
		
		Connection connection = factory.createConnection("Oracle");
		System.out.println("You are connecting with " + connection.description());
	}

}

/*
The result is just what you’d get from the FirstFactory(Factory.java) example discussed
earlier in this chapter, except that now, you’ve let the Western division pro-
grammers implement their own version of your factory. You set the factory
specification by creating an abstract class or interface that subclassers have
to use, and they build the actual concrete factory that can create objects. No
longer does a single concrete factory object instantiate your objects — a
set of subclasses does the work
*/