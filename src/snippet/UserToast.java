package snippet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.dragon.common.ConstantManager;
import com.dragon.pojo.weixin.WeixinGroup;
import com.dragon.pojo.weixin.WeixinUserInfo;
import com.dragon.pojo.weixin.WeixinUserList;
import com.dragon.service.UserService;
import com.dragon.service.impl.UserServiceImpl;
import com.dragon.utils.AdvancedUtil;
import com.dragon.utils.CommonUtil;
import com.dragon.utils.DBUtil;

/** 
 * @Title UserTest.java
 * @Description 获取所有用户基本信息，直接存在数据库中
 * 				直接运行本类就可以直接获取已经关注该公众号用户的基本信息
 * @author ganhb 
 * @date 2016-7-8
 */

public class UserToast {
	static WeixinUserInfo weixinUserInfos;
	//检查openId是否已经在数据库出现过
			public static String checkOpenId(String openId) {
				String result = null;
				String sql = "select * from t_member_user_info where member_code=\"" + openId
						+ "\"";
				Connection connection = DBUtil.getConnection();
				Statement statement = null;
				ResultSet resultSet = null;
				try {
					statement = connection.createStatement();
					resultSet = statement.executeQuery(sql);
					while (resultSet.next()) {
						result = resultSet.getString("member_code");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					DBUtil.releaseResource(connection, null, resultSet);
				}

				return result;
			}
			// 保存微信获取的基本用户信息
			public static void saveWeixinUserInfos() {
				Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = null;
				String sql =  "			INSERT INTO t_member_user_info 																	"
							+ "			(member_code,user_name,sex,origin_county,origin_province,origin_city,regist_time,source)		"
							+ "			VALUE(?,?,?,?,?,?,?,?)																			";
				try {
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, weixinUserInfos.getOpenId());
					preparedStatement.setString(2, weixinUserInfos.getNickname());
					preparedStatement.setString(3, Integer.toString(weixinUserInfos.getSex()));
					preparedStatement.setString(4, weixinUserInfos.getCountry());
					preparedStatement.setString(5, weixinUserInfos.getProvince());
					preparedStatement.setString(6, weixinUserInfos.getCity());
					preparedStatement.setString(7, weixinUserInfos.getSubscribeTime());
					preparedStatement.setString(8,Integer.toString(weixinUserInfos.getSubscribe()) );
					preparedStatement.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					DBUtil.releaseResource(connection, preparedStatement, null);
				}
			}
			
			public static void saveOpenIdToCars (){
				Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = null;
				String sql = 	"		INSERT INTO t_car_info (member_code) 	"
								+ "		VALUE(?)								";
				try{
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1,  weixinUserInfos.getOpenId());
					preparedStatement.executeUpdate();
				}catch(Exception e){
				}finally {
					DBUtil.releaseResource(connection, preparedStatement, null);
				}
				
			}
	
	public static void main (String[] args){
		String accessToken = CommonUtil.getToken(ConstantManager.APPID, ConstantManager.SECRET).getAccessToken();
		WeixinUserList weixinUserList = AdvancedUtil.getUserList(accessToken, "");
		//获取用户的openid的列表
				List<String> userList = weixinUserList.getOpenIdList();
				for (int i = 0; i < userList.size(); i++) {
					String openId = userList.get(i);
					weixinUserInfos = AdvancedUtil.getUserInfo(accessToken, openId);
					if (null == checkOpenId(openId)) {
						saveWeixinUserInfos();
						saveOpenIdToCars();
					} 
				}
		
		System.out.println("=====================关注着列表==============================");
		System.out.println("总关注个书+"+weixinUserList.getCount());
		System.out.println("本次获取用户总数+"+weixinUserList.getTotal());
		System.out.println("openid 列表+"+weixinUserList.getOpenIdList());
		//List<String> userList = weixinUserList.getOpenIdList();
		for (String ls : userList) {
			System.out.println(ls);
		}
		System.out.println("========");
		for (int i = 0; i < userList.size(); i++) {
			String ulist =userList.get(i);
			System.out.println("openid分别是"+ulist);
		}
		//for (int i = 0; i < weixinUserList.getOpenIdList().length; i++) {}
		System.out.println("next open id +"+weixinUserList.getNextOpenId());
		System.out.println("=======================关注人的基本信息============================");
		WeixinUserInfo weixinUserInfo = AdvancedUtil.getUserInfo(accessToken, "ovrT2wP80_x0hAefKdhOuqwsYk8Q ");
		System.out.println(weixinUserInfo.getNickname());
		System.out.println(weixinUserInfo.getHeadImgUrl());
		System.out.println(weixinUserInfo.getCountry());
		System.out.println(weixinUserInfo.getCity());
		System.out.println(weixinUserInfo.getOpenId());
		System.out.println(weixinUserInfo.getProvince());
		System.out.println(weixinUserInfo.getSubscribe());
		System.out.println("subscribe time is "+weixinUserInfo.getSubscribeTime());
		System.out.println(weixinUserInfo.getSex());
		
		
		
		
		System.out.println("===================================================");
		
//		WeixinGroup group = AdvancedUtil.createGroup(accessToken, "第一批测试用户");
//		System.out.println(String.format("成功创建分组：%s  id:%d", group.getName(),group.getId()));
		
		System.out.println("===================================================");
		//获取分组列表
		List<WeixinGroup> grouList = AdvancedUtil.getGroups(accessToken);
		//遍历
		for (WeixinGroup groups : grouList) {
			System.out.println(String.format("用户的iD：%s,名称:%s,用户数:%s", groups.getId(),groups.getName(),groups.getCount()));
		}
	}

}
