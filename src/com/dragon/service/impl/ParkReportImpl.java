package com.dragon.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.MidiDevice.Info;

import org.apache.log4j.Logger;

import com.dragon.pojo.ParkReportInfo;
import com.dragon.pojo.SysUserInfo;
import com.dragon.service.ParkReportService;
import com.dragon.utils.Assert;
import com.dragon.utils.DBUtil;
import com.sun.jmx.trace.Trace;

/**
 * @Title ParkReportImpl.java
 * @Description TODO
 * @author ganhb
 * @date 2016-7-6
 */

public class ParkReportImpl implements ParkReportService {
	
	private static Logger logger = Logger.getLogger(ParkReportImpl.class);

	// 添加报备消息 √（带时间）(车牌号等附加信息没有加上去)(没有添加地理位置信息)
	@Override
	public void SaveParkReport(String openId, String message, String reportTime, String cphm, String cpsf) {
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;

		String sql =    "	INSERT INTO t_parking_record(member_code,message,report_time,cphm,cpsf)		"
				     +  "   VALUE(?,?,?,?,?)																";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, openId);
			preparedStatement.setString(2, message);
			preparedStatement.setString(3, reportTime);
			preparedStatement.setString(4, cphm);
			preparedStatement.setString(5, cpsf);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {			
			e.printStackTrace();
			logger.error("添加报备消息操作失败:" , e);
		} finally {
			DBUtil.releaseResource(connection, preparedStatement, null);
		}
	}

	// 查询用户操作内容 （没用）
	@Override 
	public ParkReportInfo findReportById(String openId) {
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = "select message from T_PARKING_RECORD_COPY where open_id=" + openId;
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
			DBUtil.releaseResource(connection, preparedStatement, resultSet);
		}

		return parkReportInfo;
	}

	// 查询全部(NO USE)
	public static List<ParkReportInfo> getAllList() {
		String sqlString = 		"		SELECT id,open_id,cpsf,cpqh,cphm,message,report_time,parking_time	 "
							+   "		FROM t_parking_record_copy											 ";

		Connection connection = DBUtil.getConnection();
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
		}finally {
			DBUtil.releaseResource(connection, null, null);
			if(null != statement){
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return parkReportInfos;
	}

	// （获取车牌号码）
	@Override
	public SysUserInfo findCphmByOpenId(String openId) {
		Connection connection = DBUtil.getConnection();
		String sql = "SELECT cphm,cpsf,cpqh FROM t_car_info WHERE member_code=?  GROUP BY car_id ASC limit 1";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		SysUserInfo sysUserInfo = new SysUserInfo();
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, openId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				sysUserInfo.setCphm(resultSet.getString("cphm"));
				sysUserInfo.setCpsf(resultSet.getString("cpsf"));
				sysUserInfo.setCpqh(resultSet.getString("cpqh"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.releaseResource(connection, preparedStatement, resultSet);
		}
		return sysUserInfo;

	}
	
	//根据openId来获取车牌号(车表)

	// 跟据车牌号码和openid获取用户报备消息(NO USE)
	@Override
	public String getParkReportByOpenId(String openId, String cphm) {
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String result = null;
		String sql = "SELECT * from t_parking_record WHERE open_id=? AND cphm =?";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, openId);
			preparedStatement.setString(2, cphm);

			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				result = resultSet.getString("message");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.releaseResource(connection, preparedStatement, resultSet);
		}
		return result;
	}

	// 引用的时候到时候需要把时间转化，消息模板时间戳功能已经实现(NO USE)
	@Override
	public String getMsgByCphmAndOpenId(String cphm, String openId) {
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String resultMsg = null;

		String sql = "		SELECT message 								"+ 
				     "        FROM t_parking_record      				"+
		             "  	 WHERE open_id = ? AND cphm = ?				"+
				     "	  ORDER BY report_time DESC LIMIT 1				";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, openId);
			preparedStatement.setString(2, cphm);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				resultMsg = resultSet.getString("message");
			}
			
		} catch (Exception e) {
			logger.error("getMsgByCphmAndOpenId 方法操作失败",e);
			e.printStackTrace();
			
		} finally {
			DBUtil.releaseResource(connection, preparedStatement, resultSet);
		}
		return resultMsg;
	}

	// 根据车牌号获取报备消息，分组，对方的
	@Override
	public String getMsgByCphm(String cphm) {
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String resultMsg = null;
		Assert.notNull(cphm,"获取报备消息时，车牌号码不能为空！");


		String sql =  "		SELECT message FROM t_parking_record 	 		" 
					+ "		where  cphm = ?									"
					+ "		ORDER BY report_time DESC LIMIT 1				";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, cphm);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				resultMsg = resultSet.getString("message");
			}
		} catch (Exception e) {
			logger.error("getMsgByCphm 车牌号获取报备消息，对方的 操作失败",e);
			e.printStackTrace();
		} finally {
			DBUtil.releaseResource(connection, preparedStatement, resultSet);
		}

		return resultMsg;
	}

	// 查询报备时间
	@Override
	public String getTimeByCphm(String cphm) {
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String timeResult = null;

		String sql = 	"		SELECT report_time FROM t_parking_record	 			" 
					  + "		where  cphm = ?											"
					  + "		ORDER BY report_time DESC LIMIT 1						";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, cphm);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				timeResult = resultSet.getString("report_time");
			}
		} catch (Exception e) {
			logger.error("getTimeByCphm 查询报备时间失败",e);
			e.printStackTrace();
		} finally {
			DBUtil.releaseResource(connection, preparedStatement, resultSet);
		}

		return timeResult;
	}

	// 模板消息用，根据车牌号获取手机号码
	@Override
	public String getPhoneNumberByCphm(String cphm) {
		String phoneNumber = null;
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		String sql = "SELECT contact_mobile_phone FROM t_car_info WHERE cphm=?";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, cphm);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				phoneNumber = resultSet.getString("contact_mobile_phone");
			}
		} catch (Exception e) {
			logger.error("getPhoneNumberByCphm 根据车牌号获取手机号码",e);
		}finally {
			DBUtil.releaseResource(connection, preparedStatement, resultSet);
		}
		return phoneNumber;
	}
	
	//第二手机
	@Override
	public String getPhoneNumberSecondByCphm(String cphm){
		String phoneNumberSecond = null;
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		String sql = "SELECT contact_mobile_phone_remark FROM t_car_info WHERE cphm=?";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, cphm);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				phoneNumberSecond = resultSet.getString("contact_mobile_phone_remark");
			}
		} catch (Exception e) {
			logger.error("getPhoneNumberByCphm 根据车牌号获取手机号码",e);
		}finally {
			DBUtil.releaseResource(connection, preparedStatement, resultSet);
		}
		return phoneNumberSecond;
		}
	
	//根据openId来获取自己的手机号码（呼叫过程用）
	@Override
	public String getMobileByself(String openId){
		String mobileMyself = null;
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql = "select contact_mobile_phone from t_car_info where member_code=?";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, openId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				mobileMyself = resultSet.getString("contact_mobile_phone");
			}
		} catch (Exception e) {
			logger.error("getPhoneNumberByCphm 根据车牌号获取手机号码",e);
		}finally {
			DBUtil.releaseResource(connection, preparedStatement, resultSet);
		}
		return mobileMyself;
	}
	
}
