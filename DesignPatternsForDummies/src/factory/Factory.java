package factory;

abstract class Connection {
	public Connection() {
	}
	
	public String description() {
		return "Generic";
	}
}

class OracleConnection extends Connection {
	public OracleConnection() {
	}
	
	public String description() {
		return "Oracle";
	}
}

class SqlServerConnection extends Connection {
	public SqlServerConnection() {
	}
	
	public String description() {
		return "SQL Server";
	}
}

class MySqlConnection extends Connection {
	public MySqlConnection() {
	}
	
	public String description() {
		return "MySQL";
	}
}

class FirstFactory {
	protected String type;
	
	public FirstFactory(String t) {
		type = t;
	}
	
	public Connection createConnection() {
		if (type.equals("Oracle")) {
			return new OracleConnection();
		}
		else if (type.equals("SQL Server")) {
			return new SqlServerConnection();
		}
		else {
			return new MySqlConnection();
		}
	}
}
public class Factory {
	public static void main(String[] args) {
		FirstFactory factory;
		factory = new FirstFactory("Oracle");
		Connection connection = factory.createConnection();
		
		System.out.println("You are connecting with " + connection.description());
	}
}
