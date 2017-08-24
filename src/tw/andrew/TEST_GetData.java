package tw.andrew;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Properties;

public class TEST_GetData {

	public static void main(String[] args) {
		HashMap<String, String> dataMap = new HashMap<String, String>();
		LinkedList<HashMap<String, String>> dataLinkedMap = new LinkedList<HashMap<String, String>>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException cnfe) {
			System.out.println(cnfe);
		}
		
		Properties prop = new Properties();
		prop.setProperty("user", "root");
		prop.setProperty("password", "root");
		
		try (Connection conn = 
				DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/andrew", prop))
		{
			String sql = "SELECT * FROM " +
							"(SELECT * FROM factory WHERE machine = 'M1' ORDER BY id DESC LIMIT 10) " +
					     "sub ORDER BY id ASC";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			int count = 0;
			while (rs.next()) {
				count++;
				String id = rs.getString("id");
				String machine = rs.getString("machine");
				String date = rs.getString("date");
				String time = rs.getString("time");
				String temperature = rs.getString("temperature");
				String pressure = rs.getString("pressure");
				String flowrate = rs.getString("flowrate");
				
				dataMap.put("id", id);
				dataMap.put("machine", machine);
				dataMap.put("date", date);
				dataMap.put("time", time);
				dataMap.put("temperature", temperature);
				dataMap.put("pressure", pressure);
				dataMap.put("flowrate", flowrate);
				dataLinkedMap.add(dataMap);
			}
			System.out.println(dataLinkedMap);
		}catch (SQLException se) {
			System.out.println(se);
		}
		
		
		for (int i = 0; i < dataLinkedMap.size(); i++) {
			for (int j = 0; j < dataMap.size(); j++) {
				String id = dataMap.get("id");
				String machine = dataMap.get("machine");
				String date = dataMap.get("date");
				String time = dataMap.get("time");
				String temperature = dataMap.get("temperature");
				String pressure = dataMap.get("pressure");
				String flowrate = dataMap.get("flowrate");
//				System.out.println(id + " | " + machine + " | " + date + " | " + time + " | " + temperature + " | " + pressure + " | " + flowrate);
			}				
		}
		
		
		
			
//			double doubleTemp = 0.0;
//			double doubleTempSum = 0.0;
//			double doubleTempAvg = 0.0;
			
//			double doubleTemp = Double.parseDouble(temperature);				
//			doubleTempSum += doubleTemp;
//			//System.out.println(doubleTemp);
//			double doublePres = Double.parseDouble(pressure);
//			double doubleFlow = Double.parseDouble(flowrate);
//			System.out.println(id + " | " + machine + " | " + date + " | " + time + " | " + temperature + " | " + pressure + " | " + flowrate);				

			
			
//			doubleTempAvg = doubleTempSum / 5;
//			System.out.println(doubleTempAvg);
//			double sumSquare = 0.0;
//			while (rs.next()) {
//				String temperature = rs.getString("temperature");
//				double doubleTemp = Double.parseDouble(temperature);
//				double square = Math.pow((doubleTemp - doubleTempAvg), 2);
//				sumSquare += square;
//				System.out.println(doubleTemp);
//				System.out.println(sumSquare);
//			}
//			double std = Math.sqrt((sumSquare / 5));
//			System.out.println(std);
			
			
	}
}
