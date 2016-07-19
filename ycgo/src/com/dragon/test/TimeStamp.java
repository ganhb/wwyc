package com.dragon.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sun.corba.se.spi.orb.StringPair;

/** 
 * @Title TimeStamp.java
 * @Description TODO
 * @author ganhb 
 * @date 2016-7-8
 */

public class TimeStamp {
	//时间戳妆化为DATE
	public static void changeTimeStampToDate(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Long timeLong = new Long(1466822532);
		String timeString = format.format(timeLong);
		try {
			Date date = format.parse(timeString);
			System.out.println("格式为String 类型那 "+timeString);
			System.out.println("格式为 Date 类型 "+date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	//将date转化为时间戳
	public static void changeDateToTimeStamp(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeString = "2016-7-8 14:44:00";
		try {
			Date date = format.parse(timeString);
			System.out.println("格式为时间戳 类型那个 "+date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main (String[] agrs){
		changeTimeStampToDate();
		changeDateToTimeStamp();
	}
	
}
