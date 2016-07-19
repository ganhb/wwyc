package com.dragon.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.apache.log4j.Logger;

import com.dragon.pojo.snsuser.SNSUserInfo;
import com.dragon.pojo.token.WeixinOauth2Token;
import com.dragon.pojo.weixin.WeixinUserInfo;
import com.dragon.pojo.weixin.WeixinUserList;
import com.dragon.utils.AdvancedUtil;
import com.dragon.utils.CommonUtil;
import com.dragon.utils.SQLUtil;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

/**
 * @Title UserInfoDao.java
 * @Description TODO
 * @author ganhb
 * @date 2016-7-3
 */

public class UserInfoDao {
	private static Logger logger = Logger.getLogger(UserInfoDao.class);

	// save userinfo
	public static void saveUserInfo(String openId) {
		Connection connection = null;
		connection = SQLUtil.getConnection();
		String sql = "insert into cz_test (openId,adress,mobilephonenumber)value (?,?,?) ";

		String accessToken = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxa03d062f5ae74167&secret=072ca0cabb79357c5c2421a25902ec4a";

		SNSUserInfo userInfo = AdvancedUtil.getSNSUserInfo(accessToken, openId);
		System.out.println("userInfo.openId=" + openId);
		System.out.println("userInfo.getopenId=" + userInfo.getOpenId());
		System.out.println("userInfo.getNickname=" + userInfo.getNickname());
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, openId);
			preparedStatement.setString(2, userInfo.getNickname());
			preparedStatement.setInt(3, userInfo.getSex());
			preparedStatement.setString(4, userInfo.getCountry());
			preparedStatement.setString(5, userInfo.getCity());
			preparedStatement.setString(6, userInfo.getProvince());
			preparedStatement.execute();
		} catch (SQLException e) {
			logger.error("errorMessage" + e);
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	// test
	public static void saveTestInfo(String openid, String address,
			String mobilephonenumber) {
		Connection connection = SQLUtil.getConnection();
		String sqlString = "insert into cz_test (openid,address,mobilephonenumber)value (?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1, openid);
			preparedStatement.setString(2, address);
			preparedStatement.setString(3, mobilephonenumber);
			preparedStatement.execute();
		} catch (SQLException e) {
			logger.error("[error]=>" + e);
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				logger.error("[error]close失败");
				e.printStackTrace();
			}
		}
	}

	public static void saveSNSUserInfo() {
		Connection connection = SQLUtil.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = "insert into t_sys_user_info(open_id,user_name,sex,place_of_origin,subscribe_time,subscribe_status)value(?,?,?,?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			// preparedStatement.setString(1, "open_id");
			preparedStatement.setString(1, weixinUserInfo.getOpenId());
			preparedStatement.setString(2, weixinUserInfo.getNickname());
			preparedStatement.setInt(3, weixinUserInfo.getSex());
			preparedStatement.setString(4, weixinUserInfo.getCity());
			preparedStatement.setString(5, weixinUserInfo.getSubscribeTime());
			preparedStatement.setInt(6, weixinUserInfo.getSubscribe());
			preparedStatement.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			SQLUtil.releaseResource(connection, preparedStatement, null);
		}
	}
	//检查openId是否已经在数据库出现过
	public static String checkOpenId(String openId) {
		String result = null;
		String sql = "select * from t_sys_user_info where open_id=\"" + openId
				+ "\"";
		Connection connection = SQLUtil.getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				result = resultSet.getString("open_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.releaseResource(connection, null, resultSet);
		}

		return result;
	}

	static WeixinUserInfo weixinUserInfo;

	public static void main(String[] args) {
		String accessToken = CommonUtil.getToken("wxa03d062f5ae74167",
				"072ca0cabb79357c5c2421a25902ec4a").getAccessToken();
		WeixinUserList weixinUserList = AdvancedUtil.getUserList(accessToken,
				"");
		//获取用户的openid的列表
		List<String> userList = weixinUserList.getOpenIdList();
		for (int i = 0; i < userList.size(); i++) {
			String openId = userList.get(i);
			weixinUserInfo = AdvancedUtil.getUserInfo(accessToken, openId);
			System.out.println("main 的 openid=="+openId);
			System.out.println("weixinUserInfo 的内容=="+weixinUserInfo.getNickname());
			
			if (null == checkOpenId(openId)) {
				 saveSNSUserInfo();
			} else {
				System.out.println("用户已经存在,存在的用户的openID为：" + openId);
			}
		}

	}

}
