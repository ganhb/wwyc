package com.dragon.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.dragon.entity.SysUserInfo;
import com.dragon.service.RegisterService;
import com.dragon.utils.SQLUtil;

/**
 * @Title RegisterServiceImpl.java
 * @Description TODO
 * @author ganhb
 * @date 2016-7-5
 */

public class RegisterServiceImpl implements RegisterService {
	private static Logger logger = Logger.getLogger(RegisterServiceImpl.class);
	@SuppressWarnings("null")
	@Override
	public void saveUserInfo(String openId, String mobilePhone, String cpqh,
			String cphm) {
		Connection connection = SQLUtil.getConnection();
		String sqlString = "insert into T_SYS_USER_INFO (open_id,mobile_phone,cpqh,cphm) value(?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			connection.prepareStatement(sqlString);
			preparedStatement.setString(1, openId);
			preparedStatement.setString(2, mobilePhone);
			preparedStatement.setString(3, cpqh);
			preparedStatement.setString(4, cphm);
			preparedStatement.execute();
		} catch (SQLException e) {
			logger.error("注册操作失败", e);
			e.printStackTrace();
		} finally {
			SQLUtil.releaseResource(connection, preparedStatement, null);
		}
	}
	
	
	public static void addUserInfo(String userName,String mobilePhone,String cphm,String openId ){
		Connection connection = SQLUtil.getConnection();
		String sql = "insert into t_sys_user_info (user_name,mobile_phone,cphm) value (?,?,?) where open_id = ?";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, mobilePhone);
			preparedStatement.setString(3, cphm);
			preparedStatement.setString(4, openId);
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			SQLUtil.releaseResource(connection, preparedStatement, null);
		}
	}


	
	//TEST
	public static void main (String[] args){
	}
	
}
