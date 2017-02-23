package springbook2.ch2;

import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class MemberDao {

	SimpleJdbcTemplate simpleJdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}

}
