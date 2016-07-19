package com.dragon.utils;

import java.sql.*;

import org.apache.log4j.Logger;

/**
 * @Title SQLUtils.java
 * @Description TODO
 * @author ganhb
 * @date 2016-6-30
 */

public class SQLUtil {
	private static Logger logger = Logger.getLogger(SQLUtil.class);

	private static final String JDBCNAME_STRING = "com.mysql.jdbc.Driver";
	private static final String URL_STRING = "jdbc:mysql://localhost:3306/ycgo";
	private static final String USERNAME_STRING = "root";
	private static final String PASSWORD_STRING = "123456";

	private static Connection connection;

	public static Connection getConnection() {
		try {
			Class.forName(JDBCNAME_STRING);
			connection = DriverManager.getConnection(URL_STRING,
					USERNAME_STRING, PASSWORD_STRING);
		} catch (Exception e) {
			logger.error("数据库连接失败:season=>" + e);
		}
		return connection;
	}

	public static void releaseResource(Connection connection,
			PreparedStatement preparedStatement, ResultSet resultSet) {
		try {
			if (null != connection) {
				connection.close();
			}
			if (null != preparedStatement) {
				preparedStatement.close();
			}
			if (null != resultSet) {
				resultSet.close();
			}
		} catch (SQLException e) {
			logger.error("数据库资源释放失败，reason=>" + e);
		}
	}

}
