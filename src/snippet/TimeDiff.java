package snippet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/** 
 * @Title TimeDiff.java
 * @Description 比较时间
 * @author ganhb 
 * @date 2016-7-22
 */

public class TimeDiff {

	/**
	 * @param args
	 */
	 public static void main(String[] args) { 
		    String pastTimeStamp = "1469078857"+"000";
		    Date date = new Date();
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String nowTime = sdf.format(date);
		 	String pastTime = sdf.format(new Date(Long.parseLong(pastTimeStamp)));
		 	
		 //   timeDiff(nowTime, pastTime);
		 	System.out.println(nowTime);
		 	System.out.println(pastTime);

		 /*	Date date = new Date();
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		    String nowTime = sdf.format(date);
			try {
				date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(nowTime);
				long unixTimestamp = date.getTime()/1000;
				System.out.println(unixTimestamp);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		    }*/
		}
		 
	 
	 //计算2个时间之间的时间差
	public static double timeDiff(String nowTime,String pastTime) {

		Date dateNowTemp = null; 
		Date datePastTemp = null; 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		try { 
			dateNowTemp = sdf.parse(nowTime); 
			datePastTemp = sdf.parse(pastTime); 
			
		} catch (ParseException pe){ 
		} 
		
		long longNowTemp = dateNowTemp.getTime(); 
		long longPasttemp = datePastTemp.getTime(); 
		double hours = (double)(longNowTemp-longPasttemp)/3600/1000; 
		
		return hours;
	} 

}
