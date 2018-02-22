package br.unisal.util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class DbUtil {

	private static final DbUtil INSTANCE = new DbUtil();

	private DbUtil() {
	}

	public static DbUtil getInstance() {
		return INSTANCE;
	}

	public void closeQuietly(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
		}
	}

	public void closeQuietly(Connection conn) {
		closeQuietly(conn, null, null);
	}

	public void closeQuietly(Connection conn, Statement stmt) {
		closeQuietly(conn, stmt, null);
	}

	public void closeQuietly(Statement stmt, ResultSet rs) {
		closeQuietly(null, stmt, rs);
	}

	public void closeQuietly(Statement stmt) {
		closeQuietly(null, stmt, null);
	}

	public void closeQuietly(ResultSet rs) {
		closeQuietly(null, null, rs);
	}

	public void setLong(PreparedStatement stmt, int parameterIndex, Long value) throws SQLException {
		if (value == null) {
			stmt.setNull(parameterIndex, Types.NUMERIC);
		} else {
			stmt.setLong(parameterIndex, value);
		}
	}

	public void setString(PreparedStatement stmt, int parameterIndex, String value) throws SQLException {
		if (value == null) {
			stmt.setNull(parameterIndex, Types.VARCHAR);
		} else {
			stmt.setString(parameterIndex, value);
		}
	}

	public void setInt(PreparedStatement stmt, int parameterIndex, Integer value) throws SQLException {
		if (value == null) {
			stmt.setNull(parameterIndex, Types.INTEGER);
		} else {
			stmt.setInt(parameterIndex, value);
		}
	}

	public void setDate(PreparedStatement stmt, int parameterIndex, java.util.Date value) throws SQLException {
		Date date;
		if (value == null) {
			date = null;
		} else {
			date = new Date(value.getTime());
		}
		setDate(stmt, parameterIndex, date);
	}

	public void setDate(PreparedStatement stmt, int parameterIndex, Date value) throws SQLException {
		if (value == null) {
			stmt.setNull(parameterIndex, Types.DATE);
		} else {
			stmt.setDate(parameterIndex, value);
		}
	}

}
