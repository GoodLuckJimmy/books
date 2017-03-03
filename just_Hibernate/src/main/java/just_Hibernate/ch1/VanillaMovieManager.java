package just_Hibernate.ch1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/* 기존 db이용법
 * 
 */
public class VanillaMovieManager {

	private Connection connection = null;
	private String insertSql = "insert into movies values (?, ?, ?, ?)";
	private String selectSql = "select * from movies";

	// 데이터베이스 속성
	private String url = "jdbc:mysql://db4free.net:33067/db4freehan";
	private String driverClass = "com.mysql.jdbc.Driver";
	private String username = "db4freehan";
	private String password = "db4freehanpw";

	private void persistMovie() {
		try {
			PreparedStatement pst = getConnection().prepareStatement(insertSql);
			pst.setInt(1, 1001);
			pst.setString(2, "Top Gun");
			pst.setString(3, "Action Film");
			pst.setString(4, "Tony Scott");
			
			pst.execute();
			System.out.println("Movie persisted successfully!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void queryMovies() {
		try {
			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM MOVIES"); 
			while (rs.next()) {
				System.out.println("Movie Found: " + rs.getInt("ID")
				+ ", Title:"
				+ rs.getString("TITLE"));	
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private Connection getConnection() {
		try {
			Class.forName(driverClass).newInstance();
			connection = DriverManager.getConnection(url, username, password);
		} catch (Exception ex) {
			System.err.println("Exception:" + ex.getMessage());
		}
		return connection;
	}

}
