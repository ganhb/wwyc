package com.dragon.test.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.dragon.utils.SQLUtil;

/** 
 * @Title TextDao.java
 * @Description TODO
 * @author ganhb 
 * @date 2016-7-7
 */

public class TextDao {
	public static List<TextInfo> getAllList(){
		String sql = "select * from cz_test";
		Connection connection = SQLUtil.getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		
		List<TextInfo> tInfos = new ArrayList<TextInfo>();
		try {
			statement =connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				TextInfo textInfo = new TextInfo();
				textInfo.setId(resultSet.getInt("id"));
				textInfo.setOpenId(resultSet.getString("openid"));
				textInfo.setAddress(resultSet.getString("address"));
				textInfo.setMobilephonenumberString(resultSet.getString("mobilephonenumber"));
				tInfos.add(textInfo);
				
		
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return tInfos;
	}
	
	public static TextInfo getInfo(){
		TextInfo textinfo = new TextInfo();
		
		String sql = "select openid from cz_test";
		Connection connection = SQLUtil.getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			statement =connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				TextInfo textInfo = new TextInfo();
				textInfo.setOpenId(resultSet.getString("openid"));
				
				
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return textinfo;
	}
	
	//查询消息
	public static String getAll() {
	    Connection conn = SQLUtil.getConnection();
	    String sql = "select openid from cz_test";
	    PreparedStatement pstmt;
	    String result = null;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        int col = rs.getMetaData().getColumnCount();
	        while (rs.next()) {

	        	 result = rs.getString("openid");
	            System.out.println(rs.getString("openid"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return result;
	}
	
	
	//test
	public static void main (String[] args){
		System.out.println(getInfo().getOpenId());
		System.out.println("=================");
		System.out.println(getAll());
		
		System.out.println("=================");
		System.out.println("getAllList()="+getAllList().get(0));
	}
}
