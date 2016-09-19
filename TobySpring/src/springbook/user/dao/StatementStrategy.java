package springbook.user.dao;

import java.sql.*;

public interface StatementStrategy {
	PreparedStatement makePreparedStatement(Connection c) throws SQLException;
}
