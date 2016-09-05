package com.dragon.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.dragon.pojo.car.CarInfo;
import com.dragon.service.UserService;
import com.dragon.utils.DBUtil;

/**
 * @Title UserServiceImpl.java
 * @Description TODO 用户操作详情
 * @author ganhb
 * @date 2016-7-5
 */

public class UserServiceImpl implements UserService {

	private static Logger logger = Logger.getLogger(UserServiceImpl.class);
	//保存用户签到信息（NO USE）
	@Override
	public void SaveWeixinUser(String openId) {
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		String sql =  "			INSERT INTO  t_sys_user_info (open_id,subscribe_time,subscribe_status) "
					+ "			VALUE (?,now(),1)													   ";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, openId);
			preparedStatement.execute();
		} catch (SQLException e) {
			logger.error("保存用户签到信息错误，season=>" + e);
			e.printStackTrace();
		} finally {
			DBUtil.releaseResource(connection, preparedStatement, null);
		}
	}

	@Override//(NO USE)
	public void saveWeixinSign(String openId, int signPoints) {
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;

		String sqlString = 	"		INSERT INTO  t_user_signed (open_id,sign_time,sign_points)	"
						  + " 		VALUE (?,now(),?)											";
		try {
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1, openId);
			preparedStatement.setInt(2, signPoints);
			preparedStatement.execute();
		} catch (SQLException e) {
			logger.error("签到存储失败，【error】=>" + e);
			e.printStackTrace();
		} finally {
			DBUtil.releaseResource(connection, preparedStatement, null);
		}
	}

	@Override//(NO USE)
	public void updateUserPoints(String openId, int signPoints) {
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;

		String sqlString = "UPDATE t_sys_user_info SET integral_points=integral_points+? WHERE open_id=?";
		try {
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setInt(1, signPoints);
			preparedStatement.setString(2, openId);
			preparedStatement.execute();
		} catch (SQLException e) {
			logger.error("更新操作失败【error】" + e);
			e.printStackTrace();
		} finally {
			DBUtil.releaseResource(connection, preparedStatement, null);
		}
	}

	@Override//(NO USE)
	public boolean isTodaySigned(String openId) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean result = false;
		String sqlString =	"		SELECT count(*) AS signCounts 											"
						  + "		FROM t_user_signed 														"
						  + "		WHERE open_id=? 														"
						  + "		AND date_format(sign_time,'%Y-%m-%d') = date_format(now(),'%Y-%m-%d')	";
		try {
			ps = conn.prepareStatement(sqlString);
			ps.setString(1, openId);
			rs = ps.executeQuery();
			int signCounts = 0;
			if (rs.next()) {
				signCounts = rs.getInt("signCounts");
			}

			if (1 == signCounts) {
				result = true;
			}
		} catch (SQLException e) {
			logger.error("判断签到操作失败【error】" + e);
			e.printStackTrace();
		} finally {
			DBUtil.releaseResource(conn, ps, rs);
		}
		return result;
	}

	@Override//(NO USE)
	public boolean isWeekSigned(String openId, String monday) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean result = false;

		String sqlString = 		  "			SELECT count(*) AS signCounts 								 "
								+ "			FROM t_user_signed WHERE									 "
								+ " 		open_id=? 													 "
								+ "			AND sign_time BETWEEN str_to_date(?,'%Y-%m-%d %H:%i:%s')	 "
								+ "			AND now()													 ";
		try {
			ps = conn.prepareStatement(sqlString);
			ps.setString(1, openId);
			ps.setString(2, monday);
			rs = ps.executeQuery();
			int signCounts = 0;
			if (rs.next()) {
				signCounts = rs.getInt("signCounts");
			}
			if (7 == signCounts) {
				result = true;
			}
		} catch (SQLException e) {
			logger.error("判断连续签到操作失败【error】" + e);
			e.printStackTrace();
		} finally {
			DBUtil.releaseResource(conn, ps, rs);
		}
		return result;
	}

	@Override//(NO USE)
	public int queryUserPoints(String openId) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		String sqlString = "select integral_points from T_SYS_USER_INFO where open_id=?";
		try {
			ps = conn.prepareStatement(sqlString);
			ps.setString(1, openId);
			rs = ps.executeQuery();
			if (rs.next()) {
				result = rs.getInt("integral_points");
			}
		} catch (SQLException e) {
			logger.error("查询操作失败【error】" + e);
			e.printStackTrace();
		} finally {
			DBUtil.releaseResource(conn, ps, rs);
		}
		return result;
	}

	// 注册使用（车辆表）
	@Override 
	public void updateSysUserInfo(int carId, String userName, String mobilePhone, String cpsf, String cphm,String cllx,String clpp,String clxh,String cjh,String registTime,String insuranceTime) {
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = 	   "UPDATE t_car_info SET contact_name=?,contact_mobile_phone=?,cpsf=?,cphm=?,"
						+  "cllx=?,clpp=?,clxh=?,cjh=?,regist_time=?,insurance_time=?"
						+  "WHERE car_id = ? ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, mobilePhone);
			preparedStatement.setString(3, cpsf);
			preparedStatement.setString(4, cphm);
			preparedStatement.setString(5, cllx);
			preparedStatement.setString(6, clpp);
			preparedStatement.setString(7, clxh);
			preparedStatement.setString(8, cjh);
			preparedStatement.setString(9, registTime);
			preparedStatement.setString(10,insuranceTime);
			preparedStatement.setInt(11, carId);
			preparedStatement.execute();
		} catch (Exception e) {
			logger.error("updateSysUserInfo 注册该用户的操作失败",e);
		} finally {
			DBUtil.releaseResource(connection, preparedStatement, null);
		}
	}
	
	// 注册使用（会员表）
		@Override 
		public void updateMemberUserInfo(String openId, String userName, String mobilePhone, String sex,String address) {
			Connection connection = DBUtil.getConnection();
			PreparedStatement preparedStatement = null;
			String sql = 	   "		UPDATE t_member_user_info SET user_name=?,mobile_phone=?,sex=?,address=?,last_update_time=unix_timestamp()  "
							+  "		WHERE member_code = ? 																   ";

			try {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, userName);
				preparedStatement.setString(2, mobilePhone);
				preparedStatement.setString(3,sex);
				preparedStatement.setString(4, address);
				preparedStatement.setString(5, openId);
				preparedStatement.execute();
			} catch (Exception e) {
				logger.error("updateSysUserInfo 注册该用户的操作失败",e);
			} finally {
				DBUtil.releaseResource(connection, preparedStatement, null);
			}
		}
	
	//用户增加新的车
	@Override
	public  void insertSysUserInfo (String openId, String userName, String mobilePhone, String cpsf, String cphm,String cllx,String clpp,String clxh,String cjh,String registTime,String insuranceTime){
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		String sql =    "INSERT INTO t_car_info (member_code, contact_name,contact_mobile_phone,cpsf,cphm,"
					+   "cllx,clpp,clxh,cjh,regist_time,insurance_time)VALUE(?,?,?,?,?,?,?,?,?,?,?)";
				
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, openId);
			preparedStatement.setString(2, userName);
			preparedStatement.setString(3, mobilePhone);
			preparedStatement.setString(4, cpsf);
			preparedStatement.setString(5, cphm);
			preparedStatement.setString(6, cllx);
			preparedStatement.setString(7, clpp);
			preparedStatement.setString(8, clxh);
			preparedStatement.setString(9, cjh);
			preparedStatement.setString(10, registTime);
			preparedStatement.setString(11,insuranceTime);
			preparedStatement.execute();
		} catch(Exception e){
			logger.error("insertSysUserInfo 添加用户的车辆操作失败",e);
		} finally{
			DBUtil.releaseResource(connection, preparedStatement, null);
		}
	}

	// 判断用户是否已经出现在数据库中
	@Override
	public int checkSysUserInfo(String openId) {
		int result = 0;
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		String sql = 	"		SELECT count(*) FROM t_member_user_info 	 			"
						+ "		WHERE member_code = ?									";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, openId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				result = Integer.parseInt(resultSet.getString("count(*)"));
			}
		} catch (SQLException e) {
			logger.error("checkSysUserInfo 检查用户是否存在操作异常\n" + e);
			e.printStackTrace();
		}finally {
			DBUtil.releaseResource(connection, preparedStatement, null);
		}
		return result;
	}

	// 保存微信获取的基本用户信息
	@Override
	public void saveWeixinUserInfo(String openId, String nickName, String sex,String country, String province, String city, String subscribeTime, String subscribeStatus) {
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		String sql =  "			INSERT INTO t_member_user_info 																	"
					+ "			(member_code,user_name,sex,origin_county,origin_province,origin_city,regist_time,source)		"
					+ "			VALUE(?,?,?,?,?,?,?,?)																			";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, openId);
			preparedStatement.setString(2, nickName);
			preparedStatement.setString(3, sex);
			preparedStatement.setString(4, country);
			preparedStatement.setString(5, province);
			preparedStatement.setString(6, city);
			preparedStatement.setString(7, subscribeTime);
			preparedStatement.setString(8, subscribeStatus);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			logger.error("saveWeixinUserInfo 保存微信用户基本信息存储失败" , e);
			e.printStackTrace();
		} finally {
			DBUtil.releaseResource(connection, preparedStatement, null);
		}
	}
	
	//保存用户的openid到车辆表中
	@Override
	public  void saveOpenIdToCar (String openId){
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = 	"		INSERT INTO t_car_info (member_code) 	"
						+ "		VALUE(?)								";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, openId);
			preparedStatement.executeUpdate();
		}catch(Exception e){
			logger.error("saveOpenIdToCar 保存用户openid到车辆表失败",e);
		}finally {
			DBUtil.releaseResource(connection, preparedStatement, null);
		}
	}
	
	//保存用户的openid到报备表中
	@Override
	public  void saveOpenIdToRecord (String openId){
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = 	"		INSERT INTO t_parking_record (member_code) 	"
						+ "		VALUE(?)								";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, openId);
			preparedStatement.executeUpdate();
		}catch(Exception e){
			logger.error("saveOpenIdToRecord 保存用户openid到报备表失败",e);
		}finally {
			DBUtil.releaseResource(connection, preparedStatement, null);
		}
	}
	// 根据openId获取personname
	@Override
	public String getPersonNameByOpenId(String openId) {
		String resultName = null;
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql =  "		SELECT 	user_name				"
					+ "		 FROM 	t_member_user_info		"
					+ "		WHERE 	member_code = ?			";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, openId);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				resultName = resultSet.getString("user_name");
			}
		} catch (Exception e) {
			logger.error("getPersonNameByOpenId 不能根据openid获取到姓名",e);
			e.printStackTrace();
		} finally {
			DBUtil.releaseResource(connection, preparedStatement, resultSet);
		}
		return resultName;
	}

	@Override
	public String getPersonCphmByOpenId(String openId) {
		String resultCphm = null;
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = 	"		SELECT cphm FROM t_car_info 	"
						+ "		WHERE member_code=?					";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, openId);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				resultCphm = resultSet.getString("cphm");
			}
		} catch (Exception e) {
			logger.error("getPersonCphmByOpenId 不能根据获取到cphm",e);
			e.printStackTrace();
		} finally {
			DBUtil.releaseResource(connection, preparedStatement, resultSet);
		}
		return resultCphm;
	}

	//还没有考虑多车多号码的情况，这个得改
	@Override
	public String getPersonMobileByOpenId(String openId) {
		String resultMobile = null;
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = 		"		SELECT contact_mobile_phone FROM t_car_info 		"
							+ "		WHERE member_code = ? 	GROUP BY 	car_id	ASC LIMIT 1						";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, openId);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				resultMobile = resultSet.getString("contact_mobile_phone");
			}
		} catch (Exception e) {
			logger.error("getPersonMobileByOpenId 不能根据openid获取到姓名",e);
			e.printStackTrace();
		} finally {
			DBUtil.releaseResource(connection, preparedStatement, resultSet);
		}
		return resultMobile;
	}
	
	@Override
	public String getMobileByIdToMember(String openId) {
		String resultMobile = null;
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = 		"		SELECT mobile_phone FROM t_member_user_info		 		"
							+ "		WHERE member_code = ? 									";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, openId);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				resultMobile = resultSet.getString("mobile_phone");
			}
		} catch (Exception e) {
			logger.error("getMobileByIdToMember 不能根据openid获取到姓名",e);
			e.printStackTrace();
		} finally {
			DBUtil.releaseResource(connection, preparedStatement, resultSet);
		}
		return resultMobile;
	}

	@Override
	public String getPersonSexByOpenId(String openId) {
		String resultSex = null;
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql =	 "		SELECT sex FROM t_member_user_info 		"
						+ "		WHERE member_code = ?					";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, openId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				resultSex = resultSet.getString("sex");
			}
		} catch (Exception e) {
			logger.error("getPersonSexByOpenId 不能获取到性别",e);
			e.printStackTrace();
		} finally {
			DBUtil.releaseResource(connection, preparedStatement, resultSet);
		}
		return resultSex;
	}
	
	@Override
	public String getPersonCpsfByOpenId(String openId) {
		String resultCpsf = null;
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql =	 "		SELECT cpsf FROM t_car_info 			"
						+ "		WHERE member_code = ?					";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, openId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				resultCpsf = resultSet.getString("cpsf");
			}
		} catch (Exception e) {
			logger.error("getPersonSexByOpenId 不能获取到性别",e);
			e.printStackTrace();
		} finally {
			DBUtil.releaseResource(connection, preparedStatement, resultSet);
		}
		return resultCpsf;
	}
	
	public String getAddressByOpenId(String openId){
		String address = null;
		Connection connection = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT address FROM t_member_user_info WHERE member_code = ?";
		try{
			ps = connection.prepareStatement(sql);
			ps.setString(1, openId);
			rs = ps.executeQuery();
			while(rs.next()){
				address = rs.getString("address");
			}
		} catch (Exception e){
			logger.error("查询用户地址信息失败",e);
			e.printStackTrace();
		}finally{
			DBUtil.releaseResource(connection, ps, rs);
		}
		return address;
	}
	
	//查询用户所有车辆信息表
	public  List<CarInfo> getAllCarList(String openId){
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<CarInfo> list = new ArrayList<CarInfo>();
		String sql = "SELECT * from t_car_info where member_code = ?";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, openId);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				CarInfo carInfo = new CarInfo();
				carInfo.setCarId(resultSet.getInt("car_id"));
				carInfo.setContactName(resultSet.getString("contact_name"));
				carInfo.setContactMobilePhone(resultSet.getString("contact_mobile_phone"));
				carInfo.setCpsf(resultSet.getString("cpsf"));
				carInfo.setCphm(resultSet.getString("cphm"));
				
				list.add(carInfo);
			}
		}catch (Exception e){
			logger.error("getAllCarList 查询用户所有车辆信息表操作失败",e);
			e.printStackTrace();
		} finally{
			DBUtil.releaseResource(connection, preparedStatement, resultSet);
		}
		return list;
	}
	
	

	public static CarInfo getAllCar(String openId) {
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		CarInfo carInfo = new CarInfo();
		String sql = "SELECT * from t_car_info where member_code = ?";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, openId);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				carInfo.setCarId(resultSet.getInt("car_id"));
				carInfo.setContactName(resultSet.getString("contact_name"));
				carInfo.setContactMobilePhone(resultSet.getString("contact_mobile_phone"));
				carInfo.setCpsf(resultSet.getString("cpsf"));
				carInfo.setCphm(resultSet.getString("cphm"));
				
			}
		}catch (Exception e){
			logger.error("getAllCar 查询用户所有车辆信息表操作失败",e);
			e.printStackTrace();
		} finally{
			DBUtil.releaseResource(connection, preparedStatement, resultSet);
		}
		return carInfo;
	}
	
	
	//通过车牌号获取车辆id
	public int getCarIdByCphm(String cpsf,String cphm){
		
		int carId = 0;
		/*Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql =	 "SELECT car_id FROM t_car_info "
					+ 	 "WHERE cpsf = ? and cphm = ?";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, cpsf);
			preparedStatement.setString(2, cphm);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				carId = resultSet.getInt("car_id");
			}
		}catch (Exception e){
			logger.error("getCarIdByCphm 通过车牌号获取车辆id",e);
			e.printStackTrace();
		} finally{
			DBUtil.releaseResource(connection, preparedStatement, resultSet);
		}*/
		return carId;
	}
	
	public static List<Map> getCarIdByCphms(String cpsf,String cphm){
		List<Map> list = new ArrayList<Map>();
		
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql =	 "SELECT car_id FROM t_car_info "
					+ 	 "WHERE cpsf = ? and cphm = ?";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, cpsf);
			preparedStatement.setString(2, cphm);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				Map map = new HashMap();
				map.put("CARID", resultSet.getString("car_id"));
				list.add(map);
				
			}
		}catch (Exception e){
			logger.error("getCarIdByCphm 通过车牌号获取车辆id",e);
			e.printStackTrace();
		} finally{
			DBUtil.releaseResource(connection, preparedStatement, resultSet);
		}
		
		return list;
	}
}
