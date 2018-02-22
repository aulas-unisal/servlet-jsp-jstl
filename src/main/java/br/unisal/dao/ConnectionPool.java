package br.unisal.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;

import br.unisal.util.PropertyUtil;

public class ConnectionPool {

	private static ConnectionPool INSTANCE = null;
	private final Properties properties;
	private static BasicDataSource dataSource;

	private ConnectionPool(Properties props) {
		this.properties = props;
	}

	public static final synchronized ConnectionPool getInstance() throws IOException {
		if (INSTANCE == null) {
			final Properties props = PropertyUtil.getInstance().readFromClasspath("unisal.properties");
			if (props == null) {
				throw new FileNotFoundException(
						String.format("Dont possible find the db-config file: '%s'.", "unisal.properties"));
			}
			INSTANCE = new ConnectionPool(props);
		}
		return INSTANCE;
	}

	public Connection createConnectionComPool() throws SQLException, IOException, ClassNotFoundException {
		StringBuilder builder = new StringBuilder();
		builder.append(properties.getProperty("jdbc.protocolo"));
		builder.append(properties.getProperty("jdbc.host"));
		builder.append(":");
		builder.append(properties.getProperty("jdbc.port"));
		builder.append("/");
		builder.append(properties.getProperty("jdbc.database"));

		dataSource = new BasicDataSource();
		dataSource.setDriverClassName((properties.getProperty("jdbc.driver")));
		dataSource.setUrl(builder.toString());
		dataSource.setUsername(properties.getProperty("jdbc.username"));
		dataSource.setPassword(properties.getProperty("jdbc.password"));
		dataSource.setInitialSize(10);

		Connection connection = dataSource.getConnection();
		return connection;
	}

}
