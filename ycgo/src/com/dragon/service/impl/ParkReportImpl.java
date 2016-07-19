package com.dragon.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import sun.awt.geom.AreaOp.AddOp;

import com.dragon.entity.ParkReportInfo;
import com.dragon.entity.SysUserInfo;
import com.dragon.service.ParkReportService;
import com.dragon.utils.SQLUtil;
import com.sun.corba.se.pept.transport.ConnectionCache;
import com.sun.crypto.provider.RSACipher;

/**
 * @Title ParkReportImpl.java
 * @Description TODO
 * @author ganhb
 * @date 2016-7-6
 */

public class ParkReportImpl implements ParkReportService {
	private static Logger logger = Logger.getLogger(ParkReportImpl.class);

	//添加报备消息 √
	@Override
	public void SaveParkReport(String openId, String message) {
		Connection connection = SQLUtil.getConnection();
		PreparedStatement preparedStatement = null;

		String sql = "insert into T_PARKING_RECORD_COPY(open_id,message)value(?,?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, openId);
			preparedStatement.setString(2, message);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			logger.error("存储用户输入消息失败:" + e);
			e.printStackTrace();
		} finally {
			SQLUtil.releaseResource(connection, preparedStatement, null);
		}
	}

	// 查询用户操作内容
	@Override
	public ParkReportInfo findReportById(String openId) {
		Connection connection = SQLUtil.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = "select message from T_PARKING_RECORD_COPY where open_id="+openId;
		ResultSet resultSet = null;
		ParkReportInfo parkReportInfo = new ParkReportInfo();

		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				parkReportInfo.setMessage(resultSet.getString("message"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.releaseResource(connection, preparedStatement, resultSet);
		}

		return parkReportInfo;
	}

	// 查询全部
	public static List<ParkReportInfo> getAllList() {
		String sqlString = "select id,open_id,cpsf,cpqh,cphm,message,report_time,parking_time from t_parking_record_copy";

		Connection connection = SQLUtil.getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		List<ParkReportInfo> parkReportInfos = new ArrayList<ParkReportInfo>();
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlString);
			while (resultSet.next()) {
				ParkReportInfo prInfo = new ParkReportInfo();
				prInfo.setId(resultSet.getInt("id"));
				prInfo.setOpenId(resultSet.getString("open_id"));
				prInfo.setCphm(resultSet.getString("cphm"));
				prInfo.setCpqh(resultSet.getString("cpqh"));
				prInfo.setCpsf(resultSet.getString("cpsf"));
				prInfo.setMessage(resultSet.getString("message"));
				prInfo.setParkingTime(resultSet.getString("parking_time"));
				prInfo.setReportTime(resultSet.getString("report_time"));
				parkReportInfos.add(prInfo);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return parkReportInfos;
	}


	//获取车牌号
	@Override
	public  SysUserInfo findCphmByOpenId(String openId){
		Connection connection =SQLUtil.getConnection();
		String sql ="select cphm from t_sys_user_info where open_id=?";
		PreparedStatement preparedStatement =null;
		ResultSet resultSet = null;
		SysUserInfo sysUserInfo = new SysUserInfo();
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, openId);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				sysUserInfo.setCphm(resultSet.getString("cphm"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			SQLUtil.releaseResource(connection, preparedStatement, resultSet);
		}
		return sysUserInfo;
		
	}
	
	//跟据车牌号码和openid获取用户报备消息
	@Override
	public  String getParkReportByOpenId(String openId,String cphm){
		Connection connection = SQLUtil.getConnection();
		PreparedStatement preparedStatement =null ; 
		ResultSet resultSet = null;
		String result =null;
		String sql = "select * from t_parking_record_copy where open_id=?and cphm =?";
	
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, openId);
			preparedStatement.setString(2, cphm);
			
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				result = resultSet.getString("message");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// test
	public static void main(String[] args) {
		String openId = "ovrT2wBve9vs4u-Axspk41nZ-tvE";
		//获取车牌号码
		SysUserInfo userInfo = new SysUserInfo();
		
	//	String cphm = getCarNumber(openId);
	//	System.out.println(cphm);
		//获取报备信息
	//	String reportMessage = getParkReportByOpenId(openId, cphm);
	//	System.out.println(reportMessage);
	}

}
