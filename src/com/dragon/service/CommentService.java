package com.dragon.service;

import com.dragon.pojo.CommentInfo;

/** 
 * @Title CommentService.java
 * @Description TODO
 * @author ganhb 
 * @date 2016-7-14
 */

public interface CommentService {
	
	void addComment(String openId,String CommentOpenId,String CommentTime,String stars);	
	CommentInfo getComment();
}
