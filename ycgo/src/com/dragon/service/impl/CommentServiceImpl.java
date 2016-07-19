package com.dragon.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.dragon.entity.CommentInfo;
import com.dragon.service.CommentService;
import com.dragon.utils.SQLUtil;

/** 
 * @Title CommentServiceImpl.java
 * @Description TODO
 * @author ganhb 
 * @date 2016-7-14
 */

public class CommentServiceImpl implements CommentService {
	private static Logger logger = Logger.getLogger(CommentServiceImpl.class);
	
	// 添加评论消息
	@Override
	public void addComment(String openId,String CommentOpenId,String CommentTime,String stars) {
		Connection connection = SQLUtil.getConnection();
		String sql = "insert into T_COMMENT_RECORD(open_id,commented_open_id,comment_time,stars)value(?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, openId);
			preparedStatement.setString(2, CommentOpenId);
			preparedStatement.setString(3, CommentTime);
			preparedStatement.setString(4, stars);
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			SQLUtil.releaseResource(connection, preparedStatement, null);
		}
	}
	//保存评论人消息
	//查看评论
	public CommentInfo getComment(){
		Connection connection = SQLUtil.getConnection();
		String sql = "select * from T_COMMENT_RECORD";
		Statement statement = null ;
		ResultSet resultSet = null;
		CommentInfo commentInfo = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				commentInfo.setStars(resultSet.getString("stars"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return commentInfo;
	}	
	
	//更新评论人积分,复用updateUserPoints
	
	
}
