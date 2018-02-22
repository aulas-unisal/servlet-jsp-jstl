package br.unisal.dao;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import br.unisal.util.DbUtil;

public abstract class GenericDAO {
	private static Connection conn;

	protected List<Object[]> executaSqlSemParametro(Connection conn, String sql) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		return executaSql(conn, sql, params, null);
	}

	private List<Object[]> executaSql(Connection conn, String sql, Map<String, Object> map, Integer maxRows)
			throws SQLException {
		List<Object[]> objects = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);

			int count = 0;
			for (String key : map.keySet()) {
				Object value = map.get(key);
				ps.setObject((count + 1), value);
				count++;
			}

			rs = ps.executeQuery();
			ResultSetMetaData rsMetaData = rs.getMetaData();
			int columnCount = rsMetaData.getColumnCount();
			int row = 0;
			while (rs.next()) {
				if (columnCount > 0) {
					Object[] columns = new Object[columnCount];
					for (int i = 0; i < columns.length; i++) {
						columns[i] = rs.getObject(i + 1);
					}
					objects.add(columns);
				}
				row++;
				if (maxRows != null && row >= maxRows) {
					break;
				}
			}
		} finally {
			DbUtil.getInstance().closeQuietly(ps, rs);
		}
		return objects;
	}

	public static Connection getConnectionPool() throws ClassNotFoundException, SQLException, IOException {
		if (conn == null || conn.isClosed())
			return conn = ConnectionPool.getInstance().createConnectionComPool();
		else
			return conn;
	}
}
