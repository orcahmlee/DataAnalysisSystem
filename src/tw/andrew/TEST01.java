package tw.andrew;

import java.util.Enumeration;

public class TEST01 {

	public static void main(String[] args) {
//		Temperature temp = new Temperature(200.0, 0.005);
//		
//		for (int i = 0; i < 10; i++) {
//			String temptemp = temp.getTemperature();
//			System.out.println(temp.tempRange);
//			System.out.println(temptemp);
//		}
//		System.out.println("----------");
//		System.out.println(temp.tempSetPoint);
//		System.out.println(temp.tempAccuracy);
//		
	
		try {
		      com.mysql.jdbc.AbandonedConnectionCleanupThread.shutdown();
		   } catch (Throwable t) {}
		   // This manually deregisters JDBC driver, which prevents Tomcat 7 from complaining about memory leaks
		   Enumeration<java .sql.Driver> drivers = java.sql.DriverManager.getDrivers();
		   while (drivers.hasMoreElements()) {
		      java.sql.Driver driver = drivers.nextElement();
		      try {
		         java.sql.DriverManager.deregisterDriver(driver);
		      } catch (Throwable t) {}
		   }
		   try { Thread.sleep(2000L); } catch (Exception e) {}
		
		System.out.println("OK");
	}

}
