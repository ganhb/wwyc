package com.dragon.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.dragon.service.SMSService;
import com.dragon.utils.SQLUtil;

/** 
 * @Title SMSServiceImpl.java
 * @Description TODO
 * @author ganhb 
 * @date 2016-7-5
 */

public class SMSServiceImpl implements SMSService {
private static Logger logger = Logger.getLogger(SMSServiceImpl.class);
	@Override
	public void saveCallSMS(String openId, String message) {
		Connection connection = SQLUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet=null;
		String sql = "insert into T_CALLING_RECORD_COPY (open_id,message) value(?,?)";
		try {
			//,Statement.RETURN_GENERATED_KEYS
			preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, openId);
			preparedStatement.setString(2, message);
			preparedStatement.executeUpdate();
			//得到插入行的主键，结果集合只有一条
			resultSet=preparedStatement.getGeneratedKeys();
			logger.info("resultset=>"+resultSet.toString());
						if (resultSet.next()) {
				System.out.println(resultSet.getObject(1));
			}
		} catch (SQLException e) {
			logger.error("保存短信留言操作失败了，[reason]\n" + e);
			e.printStackTrace();
		} finally {
			SQLUtil.releaseResource(connection, preparedStatement, null);
		}
	}

	@Override
	public void findByOpenId(String openId) {
		Connection connection = SQLUtil.getConnection();
		Statement statement;
		ResultSet resultSet;
		
		String sql="select message from T_CALLING_RECORD_COPY where open_id="+openId;
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			if (resultSet.next()) {
				String message = resultSet.getString("message");
				logger.info("message=>"+message);
			}else{
				logger.info("您未有任何短信留言记录！请您新增后再来！");
			}
			
		} catch (SQLException e) {
			logger.error("查看短信留言操作失败,[reason]=>\n"+e);
			e.printStackTrace();
		}
	}

}
