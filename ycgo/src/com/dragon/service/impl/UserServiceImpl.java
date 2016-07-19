package com.dragon.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.dragon.entity.SysUserInfo;
import com.dragon.service.UserService;
import com.dragon.utils.SQLUtil;

/** 
 * @Title UserServiceImpl.java
 * @Description TODO
 * @author ganhb 
 * @date 2016-7-5
 */

public class UserServiceImpl implements UserService {

	private static Logger logger = Logger.getLogger(UserServiceImpl.class);
	@Override
	public void saveUserInfo(String openId, String userName,
			String mobilePhone, String cpsf,String cpqh, String cphm) {
		Connection connection = SQLUtil.getConnection();
		String sql = "insert into T_SYS_USER_INFO (open_id,user_name,mobile_phone,cpsf,cpqh,cphm) value (?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, openId);
			preparedStatement.setString(2, userName);
			preparedStatement.setString(3, mobilePhone);
			preparedStatement.setString(4, cpsf);
			preparedStatement.setString(5, cpqh);
			preparedStatement.setString(6, cphm);
			preparedStatement.execute();
		} catch (SQLException e) {
			logger.error("保存用户车辆操作失败，",e);
			e.printStackTrace();
		}finally{
			SQLUtil.releaseResource(connection, preparedStatement, null);
		}
	}
	@Override
	public void SaveWeixinUser(String openId) {
		Connection connection = SQLUtil.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = "insert into  T_SYS_USER_INFO(open_id,subscribe_time,subscribe_status) value (?,now(),1)";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, openId);
			preparedStatement.execute();
		} catch (SQLException e) {
			logger.error("保存用户签到信息错误，season=>" + e);
			e.printStackTrace();
		} finally {
			SQLUtil.releaseResource(connection, preparedStatement, null);
		}
	}
	@Override
	public void saveWeixinSign(String openId, int signPoints) {
		Connection connection = SQLUtil.getConnection();
		PreparedStatement preparedStatement = null;

		String sqlString = "insert into  T_USER_SIGNED(open_id,sign_time,sign_points) value (?,now(),?)";
		try {
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1, openId);
			preparedStatement.setInt(2, signPoints);
			preparedStatement.execute();
		} catch (SQLException e) {
			logger.error("签到存储失败，【error】=>" + e);
			e.printStackTrace();
		} finally {
			SQLUtil.releaseResource(connection, preparedStatement, null);
		}
	}
	@Override
	public void updateUserPoints(String openId, int signPoints) {
		Connection connection = SQLUtil.getConnection();
		PreparedStatement preparedStatement = null;

		String sqlString = "update T_SYS_USER_INFO set integral_points=integral_points+? where open_id=?";
		try {
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setInt(1, signPoints);
			preparedStatement.setString(2, openId);
			preparedStatement.execute();
		} catch (SQLException e) {
			logger.error("更新操作失败【error】" + e);
			e.printStackTrace();
		} finally {
			SQLUtil.releaseResource(connection, preparedStatement, null);
		}
	}
	@Override
	public boolean isTodaySigned(String openId) {
		Connection conn = SQLUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean result = false;

		String sqlString = "select count(*) as signCounts from T_USER_SIGNED "
				+ "where open_id=? and date_format(sign_time,'%Y-%m-%d') = date_format(now(),'%Y-%m-%d')";
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
			SQLUtil.releaseResource(conn, ps, rs);
		}
		return result;
	}
	@Override
	public boolean isWeekSigned(String openId, String monday) {
		Connection conn = SQLUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean result = false;

		String sqlString = "select count(*) as signCounts from T_USER_SIGNED where"
				+ " open_id=? and sign_time between str_to_date(?,'%Y-%m-%d %H:%i:%s') and now()";
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
			SQLUtil.releaseResource(conn, ps, rs);
		}
		return result;
	}
	@Override
	public int queryUserPoints(String openId) {
		Connection conn = SQLUtil.getConnection();
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
			SQLUtil.releaseResource(conn, ps, rs);
		}
		return result;
	}
	//保存注册信息(5个字段)
	public static void saveRegMsg(String openId,String userName,String mobilePhone,String cpsf,String cphm){
		Connection connection =SQLUtil.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = "insert into t_sys_user_info (open_id,user_name,mobile_phone,cpsf,cpqh) value (?,?,?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, openId);
			preparedStatement.setString(2, userName);
			preparedStatement.setString(3, mobilePhone);
			preparedStatement.setString(4, cpsf);
			preparedStatement.setString(5, cphm);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			SQLUtil.releaseResource(connection, preparedStatement, null);
		}
	}
}
