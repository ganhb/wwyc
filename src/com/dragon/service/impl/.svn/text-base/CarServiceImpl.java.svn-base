package com.dragon.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.dragon.service.CarService;
import com.dragon.utils.DBUtil;

/**
 * @author ganhb
 * 车辆数据操作类 
 *
 */
public class CarServiceImpl implements CarService{

	private static final Logger logger = Logger.getLogger(CarServiceImpl.class);
	
	public  String checkCar(String openId) {
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String result = null;

		String sql =  "			SELECT cphm FROM t_sys_user_info		"
					+ "			WHERE open_id=?							";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, openId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				result = resultSet.getString("cphm");
			}
		} catch (Exception e) {
			logger.error("检查车牌号出现异常", e);
			e.printStackTrace();
		} finally {
			DBUtil.releaseResource(connection, preparedStatement, resultSet);
		}
		return result;
	}
	 
	public  void addStickerRecord(String openId,String username,String mobile,String cpsf,String cphm,String cpqh,String method,String province,String city,String county){
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = "INSERT INTO t_sticker_record (member_code,user_name,mobile_phone,cpsf,cpqh,cphm,method,province,city,county) VALUE(?,?,?,?,?,?,?,?,?,?)";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,openId);
			preparedStatement.setString(2,username);
			preparedStatement.setString(3,mobile);
			preparedStatement.setString(4,cpsf);
			preparedStatement.setString(5,cpqh);
			preparedStatement.setString(6,cphm);
			preparedStatement.setString(7,method);
			preparedStatement.setString(8,province);
			preparedStatement.setString(9,city);
			preparedStatement.setString(10,county);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			logger.error("新增用户移车名片操作失败",e);
			e.printStackTrace();
		}finally{
			DBUtil.releaseResource(connection, preparedStatement, null);
		}
		
	}
}