package com.dragon.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.dragon.service.SMSService;
import com.dragon.utils.DBUtil;
import com.sun.corba.se.spi.orb.StringPair;

/** 
 * @Title SMSServiceImpl.java
 * @Description TODO
 * @author ganhb 
 * @date 2016-7-5
 */

public class SMSServiceImpl implements SMSService {
private static Logger logger = Logger.getLogger(SMSServiceImpl.class);
	@Override
	public void saveCallSMS(String openId, String message,String cphm,String cpsf,String calling_result) {
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
	//	ResultSet resultSet=null;
		String sql = "INSERT INTO t_calling_record("
					+	"member_code,"
				//	+	"car_id,"
					+	"message,"
					+	"calling_time,"
					+	"cphm,"
					+	"cpsf,"
					+	"calling_result"
					+	")"
					+	"VALUE"
					+	"(?,?,unix_timestamp(),?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, openId);
			preparedStatement.setString(2, message);
			preparedStatement.setString(3,cphm);
			preparedStatement.setString(4, cpsf);
			preparedStatement.setString(5, calling_result);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			logger.error("存储用户呼叫记录操作失败了，[reason]\n" , e);
			e.printStackTrace();
		} finally {
			DBUtil.releaseResource(connection, preparedStatement, null);
		}
	}

	@Override//（NO USE）查看用户的短信留言
	public void findByOpenId(String openId) {
		Connection connection = DBUtil.getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		
		String sql="select message from T_CALLING_RECORD where open_id="+openId;
		
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
		}finally {
			DBUtil.releaseResource(connection, null, null);
			if(null == statement){
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	//更具用户的openId来获取用户的手机hao
	@Override
	public  String getPhoneNumberByOpenId(String openId){
		String phoneNumber = null;
		Connection connection =DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql = "SELECT mobile_phone FROM t_member_user_info WHERE member_code = ?";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, openId);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				phoneNumber = resultSet.getString("mobile_phone");
			}
			
		} catch (Exception e) {
			logger.error("更具用户的openId来获取用户的手机号操作失败",e);
		}finally{
			DBUtil.releaseResource(connection, preparedStatement, resultSet);
		}
		
		return phoneNumber;
	}
	
	//根据cphm查询对方用户的openId
	@Override
	public  String getOpenIdByCphm(String cphm){
		String resultOpenId = null;
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = "select open_id from t_sys_user_info where cphm = ?";
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, cphm);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				resultOpenId = resultSet.getString("open_id");
			}
		} catch (Exception e) {
			logger.error("查询不到对方用户的openId",e);
		}finally{
			DBUtil.releaseResource(connection, preparedStatement, resultSet);
		}
		return resultOpenId;
	}
	
	//根据车牌号获取报备的时间（NO USE），改方法应该在parkport里面实现
	@Override
	public  String getTimeByCphm(String cphm){
		String resultTime = null;
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = "select report_time from t_parking_record_copy where cphm=? GROUP BY report_time DESC limit 1";
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, cphm);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				resultTime = resultSet.getString("report_time");
			}
		} catch (Exception e) {
			logger.error("查询报备时间错误");
		}finally {
			DBUtil.releaseResource(connection, preparedStatement, resultSet);
		}
		return resultTime;
	}
	
}