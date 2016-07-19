package com.dragon.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dragon.service.PositionService;
import com.dragon.utils.SQLUtil;

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
		Connection connection = SQLUtil.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = "update T_SYS_USER_INFO set regist_x = ?,regist_y = ?,regist_p=? where open_id=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, longitude);
			preparedStatement.setString(2, latitude);
			preparedStatement.setString(3, precision);
			preparedStatement.setString(4, openId);
			preparedStatement.execute();
		} catch (Exception e) {
			logger.error("保存用户位置信息失败");
			e.printStackTrace();
		}finally{
			SQLUtil.releaseResource(connection, preparedStatement, null);
		}
	}
}
