package tw.andrew;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Properties;

public class TemperatureFlotData {
	private String equipment;
	private int numberOfData;
	private HashMap<String, String> dataMap;
	private LinkedList<HashMap<String, String>> dataLinkedMap = new LinkedList<HashMap<String, String>>();
	
	TemperatureFlotData(String equipment, int minute) {
		this.equipment = equipment; // The name of Machine
		this.numberOfData = (minute * 60) / 5; // The number of data you wanna get
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch (ClassNotFoundException cnfe) {
			System.out.println(cnfe);
		}
		
		Properties prop = new Properties();
		prop.setProperty("user", "root");
		prop.setProperty("password", "root");
		
		try (Connection conn = 
				DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/andrew", prop))
		{
			// MySQL query: select the last N data
			String sql = "SELECT * FROM (" + 
					"SELECT machine, time, timestamp, temperature FROM factory WHERE machine = '" + this.equipment + "' ORDER BY id DESC LIMIT " + this.numberOfData + ") " +
					"sub ORDER BY timestamp ASC";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String machine = rs.getString("machine");
				String time = rs.getString("time");
				String timestamp = rs.getString("timestamp");
				String temperature = rs.getString("temperature");
				// Put the data into HashMap
				dataMap = new HashMap<String, String>();
				dataMap.put("machine", machine);
				dataMap.put("time", time);
				dataMap.put("timestamp", timestamp);
				dataMap.put("temperature", temperature);
				// Add the HashMAp into LinkedList
				dataLinkedMap.add(dataMap);		
			}
		}catch (SQLException se) {
			System.out.println(se);
		}
	}
	
	public LinkedList<HashMap<String, String>> getDataLinkedMap() {
		return dataLinkedMap;
	}
	
	public int getNumberOfData() {
		return numberOfData;
	}
	
	public String getTemperatureFlotData() {				
		String temperatureFlotData;
		String head = "[";
		String tail = "]";
		String comma = ",";
		StringBuffer sb = new StringBuffer();
		
		sb.append(head);	
		for (int i = 0; i < dataLinkedMap.size(); i++) {
			String machine = dataLinkedMap.get(i).get("machine");
			String time = dataLinkedMap.get(i).get("time");
			String timestamp = dataLinkedMap.get(i).get("timestamp");
			String temperature = dataLinkedMap.get(i).get("temperature");
			
			sb.append(head);
			sb.append(timestamp);
			sb.append(comma);
			sb.append(temperature);
			sb.append(tail);
			sb.append(comma);
		}
		sb.deleteCharAt(sb.lastIndexOf(comma));
		sb.append(tail);
		
		temperatureFlotData = sb.toString();
		
		return temperatureFlotData;
	}

}
