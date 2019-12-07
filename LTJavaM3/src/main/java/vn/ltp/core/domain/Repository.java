package vn.ltp.core.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Repository {
	protected Connection connection;
	protected Statement stmt;
	protected ResultSet rs;
	protected PreparedStatement pstmt;
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	protected void open() throws SQLException {
		connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/bookstore?useUnicode=true&characterEncoding=UTF-8", "root", "1234");
	}

	protected void close() throws SQLException {
		if (rs != null) {
			rs.close();
		}
		if (stmt != null) {
			stmt.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if (connection != null) {
			connection.close();
		}
	}
}