package com.dragon.pojo;

/**
 * @Title CommentInfo.java
 * @Description TODO
 * @author ganhb
 * @date 2016-7-14
 */

public class CommentInfo {
	private int id;
	private String openId;
	// 被评论人的
	private String commentOpenId;
	private String callingRecordId;
	private String commentTime;
	private String Stars;
	private String arriveTime;
	private int points;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getCommentOpenId() {
		return commentOpenId;
	}

	public void setCommentOpenId(String commentOpenId) {
		this.commentOpenId = commentOpenId;
	}

	public String getCallingRecordId() {
		return callingRecordId;
	}

	public void setCallingRecordId(String callingRecordId) {
		this.callingRecordId = callingRecordId;
	}

	public String getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}

	public String getStars() {
		return Stars;
	}

	public void setStars(String stars) {
		Stars = stars;
	}

	public String getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

}
