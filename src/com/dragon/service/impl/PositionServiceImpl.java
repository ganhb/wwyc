package com.dragon.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dragon.service.PositionService;
import com.dragon.utils.DBUtil;

/** 
 * @Title PositionServiceImpl.java
 * @Description TODO
 * @author ganhb 
 * @date 2016-7-19
 */

public class PositionServiceImpl implements PositionService {
	private static Logger logger = LoggerFactory.getLogger(PositionServiceImpl.class);
	
	@Override
	public void SaveUserPosition(String longitude,String latitude,String precision,String openId){
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = 		"UPDATE t_parking_record SET parking_x = ?,parking_y = ?,parking_p=? "
					+ 		"WHERE member_code=? "
					+ 		"ORDER BY report_time DESC LIMIT 1";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, longitude);
			preparedStatement.setString(2, latitude);
			preparedStatement.setString(3, precision);
			preparedStatement.setString(4, openId);
			preparedStatement.execute();
		} catch (Exception e) {
			logger.error("SaveUserPosition 保存用户(报备)位置信息失败",e);
			e.printStackTrace();
		}finally{
			DBUtil.releaseResource(connection, preparedStatement, null);
		}
	}
	
	@Override
	public void SaveCallPosition(String longitude,String latitude,String precision,String openId){
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		String sql =		 "UPDATE t_calling_record SET calling_x = ?,calling_y = ?,calling_p=? "
					+		 "WHERE member_code=? "
					+ 		 "ORDER BY calling_time DESC LIMIT 1";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, longitude);
			preparedStatement.setString(2, latitude);
			preparedStatement.setString(3, precision);
			preparedStatement.setString(4, openId);
			preparedStatement.execute();
		} catch (Exception e) {
			logger.error("SaveUserPosition 保存用户(呼叫)位置信息失败",e);
			e.printStackTrace();
		}finally{
			DBUtil.releaseResource(connection, preparedStatement, null);
		}
	}
}
