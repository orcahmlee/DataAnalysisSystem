package tw.andrew;
/*
 * This class create new data of date and current time.
 */

import java.util.Calendar;

class DateTime {
	Calendar now = Calendar.getInstance();
	int yyyy = now.get(Calendar.YEAR);
	String YYYY = Integer.toString(yyyy);
	int mo = now.get(Calendar.MONTH) + 1;
	String MO = String.format("%02d", mo);
	int dd = now.get(Calendar.DAY_OF_MONTH);
	String DD = String.format("%02d", dd);
	
	int hh = now.get(Calendar.HOUR_OF_DAY);
	String HH = String.format("%02d", hh);
	int mm = now.get(Calendar.MINUTE);
	String MM = String.format("%02d", mm);
	int ss = now.get(Calendar.SECOND);
	String SS = String.format("%02d", ss);
	
	long t = now.getTimeInMillis();
	String timestamp = Long.toString(t);
	
	String getToday() {
		StringBuffer sbToday = new StringBuffer();
		sbToday = sbToday.append(YYYY).append("-").append(MO).append("-").append(DD);
		String today = sbToday.toString();
		
		return today;
	}
	
	String getTime() {
		StringBuffer sbTime = new StringBuffer();
		sbTime = sbTime.append(HH).append(":").append(MM).append(":").append(SS);
		String time = sbTime.toString();
		
		return time;
	}	
	
	String getHour() {
		return HH;
	}
	
	String getMinute() {
		return MM;
	}
	
	String getSecond() {
		return SS;
	}
	
	String getTimestamp() {
		return timestamp;
	}

}