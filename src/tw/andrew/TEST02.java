package tw.andrew;

import java.util.Calendar;

public class TEST02 {

	public static void main(String[] args) {
		Calendar now = Calendar.getInstance();
		int yyyy = now.get(Calendar.YEAR);
		String YYYY = Integer.toString(yyyy);
		int mo = now.get(Calendar.MONTH);
		String MO = String.format("%02d", mo);
		int dd = now.get(Calendar.DAY_OF_MONTH);
		String DD = String.format("%02d", dd);
		StringBuffer today = new StringBuffer();
		today = today.append(YYYY).append("-").append(MO).append("-").append(DD);
		
		int hh = now.get(Calendar.HOUR_OF_DAY);
		String HH = String.format("%02d", hh);
		int mm = now.get(Calendar.MINUTE);
		String MM = String.format("%02d", mm);
		int ss = now.get(Calendar.SECOND);
		String SS = String.format("%02d", ss);
		StringBuffer time = new StringBuffer();
		time = time.append(HH).append(":").append(MM).append(":").append(SS);
		
		//System.out.println(name +"=> " + today + ":" + time);
	}
	class TTT extends Thread {
		@Override
		public void run() {
			Calendar now = Calendar.getInstance();
			int yyyy = now.get(Calendar.YEAR);
			String YYYY = Integer.toString(yyyy);
			int mo = now.get(Calendar.MONTH);
			String MO = String.format("%02d", mo);
			int dd = now.get(Calendar.DAY_OF_MONTH);
			String DD = String.format("%02d", dd);
			StringBuffer today = new StringBuffer();
			today = today.append(YYYY).append("-").append(MO).append("-").append(DD);
			
			int hh = now.get(Calendar.HOUR_OF_DAY);
			String HH = String.format("%02d", hh);
			int mm = now.get(Calendar.MINUTE);
			String MM = String.format("%02d", mm);
			int ss = now.get(Calendar.SECOND);
			String SS = String.format("%02d", ss);
			StringBuffer time = new StringBuffer();
			time = time.append(HH).append(":").append(MM).append(":").append(SS);
		}
	}
	class NNN {
//		Calendar now = Calendar.getInstance();
//		int yyyy = now.get(Calendar.YEAR);
//		String YYYY = Integer.toString(yyyy);
//		int mo = now.get(Calendar.MONTH);
//		String MO = String.format("%02d", mo);
//		int dd = now.get(Calendar.DAY_OF_MONTH);
//		String DD = String.format("%02d", dd);
//		StringBuffer today = new StringBuffer();
//		today = today.append(YYYY).append("-").append(MO).append("-").append(DD);
//		
//		int hh = now.get(Calendar.HOUR_OF_DAY);
//		String HH = String.format("%02d", hh);
//		int mm = now.get(Calendar.MINUTE);
//		String MM = String.format("%02d", mm);
//		int ss = now.get(Calendar.SECOND);
//		String SS = String.format("%02d", ss);
//		StringBuffer time = new StringBuffer();
//		time = time.append(HH).append(":").append(MM).append(":").append(SS);
	}


}


