package com.dragon.utils;

import java.sql.*;
import java.sql.Connection;
import java.util.LinkedList;

import org.apache.log4j.Logger;

/**
 * @Title SQLUtils.java
 * @Description TODO
 * @author ganhb
 * @date 2016-6-30
 * @TODO 加上连接池操作
 */

public class DBUtil {
	private static Logger logger = Logger.getLogger(DBUtil.class);

	private static final String JDBCNAME_STRING = "com.mysql.jdbc.Driver";
	private static final String URL_STRING = "jdbc:mysql://localhost:3306/wwyiche";
	private static final String USERNAME_STRING = "root";
	private static final String PASSWORD_STRING = "123456";

	private static Connection connection;
/*
	private static final DBUtil instance = new DBUtil(); 
	private final int INIT_COUNT = 5;
	private final int MAX_COUNT = 30;
	private int count = 0;
	
	private final Object wait = new Object();
	
	private LinkedList<Connection> CONN_POOL;
	
    public DBUtil() {  
        CONN_POOL = new LinkedList<Connection>();  
        try {  
            Class.forName(JDBCNAME_STRING);  
            for (int i = 0; i < INIT_COUNT; i++) {  
                Connection connection = createConnection();  
                if(connection != null) {  
                    CONN_POOL.add(createConnection());  
                    count++;  
                }  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
      
    public static DBUtil getInstance() {  
        return instance;  
    }  
      
    private static Connection createConnection() {  
        try {  
            return DriverManager.getConnection(URL_STRING, USERNAME_STRING, PASSWORD_STRING);  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
  
    public Connection getConnection() {  
        synchronized (CONN_POOL) {  
            while(CONN_POOL.size() > 0) {  
                Connection conn = CONN_POOL.removeLast();  
                try {  
                    if(conn.isValid(1000)) {  
                        return conn;  
                    } else {  
                        count--;  
                    }  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
            }  
            if(count < MAX_COUNT) {  
                count++;  
                return createConnection();  
            }   
            synchronized (wait) {  
                try {  
                    wait.wait(3000);  
                    if(CONN_POOL.size() > 0) {  
                        return CONN_POOL.removeLast();  
                    }  
                } catch (InterruptedException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        return null;  
    }  
  
    public void releaseConnection(Connection connection) {  
        CONN_POOL.add(connection);  
        synchronized (wait) {  
            wait.notify();  
        }  
    }  */
	
	public static Connection getConnection() {
		try {
			Class.forName(JDBCNAME_STRING);
			connection = DriverManager.getConnection(URL_STRING,
					USERNAME_STRING, PASSWORD_STRING);
		} catch (Exception e) {
			logger.error("数据库连接失败:season=>" + e);
			e.printStackTrace();
		}
		return connection;
	}

	public static void releaseResource(Connection connection,
			PreparedStatement preparedStatement, ResultSet resultSet) {
		try {
			if (null != resultSet) {
				resultSet.close();
			}
			if (null != preparedStatement) {
				preparedStatement.close();
			}
			if (null != connection) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("数据库资源释放失败，reason=>" + e);
		}
	}

}
