package com.dragon.entity;


/**
 * @Title ParkReportInfo.java
 * @Description TODO
 * @author ganhb
 * @date 2016-7-6
 */

public class ParkReportInfo {
	@Override
	public String toString() {
		return "ParkReportInfo [id=" + id + ", openId=" + openId + ", cpsf="
				+ cpsf + ", cpqh=" + cpqh + ", cphm=" + cphm + ", message="
				+ message + ", parkingTime=" + parkingTime + ", reportTime="
				+ reportTime + "]";
	}

	private int id;
	private String openId;
	private String cpsf;
	private String cpqh;
	private String cphm;
	private String message;
	private String parkingTime;
	private String reportTime;

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

	public String getCpsf() {
		return cpsf;
	}

	public void setCpsf(String cpsf) {
		this.cpsf = cpsf;
	}

	public String getCpqh() {
		return cpqh;
	}

	public void setCpqh(String cpqh) {
		this.cpqh = cpqh;
	}

	public String getCphm() {
		return cphm;
	}

	public void setCphm(String cphm) {
		this.cphm = cphm;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getParkingTime() {
		return parkingTime;
	}

	public void setParkingTime(String parkingTime) {
		this.parkingTime = parkingTime;
	}

	public String getReportTime() {
		return reportTime;
	}

	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}

}
