package tw.andrew;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Properties;

public class HistoryFlotData {
	private String equipment;
	private String startDate, startHour, startMinute;
	private String endDate, endHour, endMinute;
	private int numberOfData;
	private HashMap<String, String> dataMap;
	private LinkedList<HashMap<String, String>> dataLinkedMap = new LinkedList<HashMap<String, String>>();
	
	HistoryFlotData(String equipment, String startDate, String endDate, String startHour, String endHour, String startMinute, String endMinute) {
		this.equipment = equipment; // The name of Machine
		this.startDate = startDate;
		this.endDate = endDate;
		this.startHour = startHour;
		this.endHour = endHour;
		this.startMinute = startMinute;
		this.endMinute = endMinute;
				
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch (ClassNotFoundException cnfe) {
			System.out.println(cnfe);
		}
		
		Properties prop = new Properties();
		prop.setProperty("user", "root");
		prop.setProperty("password", "root");
		
		// Auto-closeable
		try (Connection conn = 
				DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/andrew", prop))
		{
			// MySQL query: 
			String sql = 
					"SELECT * FROM factory " +
					"WHERE machine = '" + this.equipment + "' and " + 
					"date BETWEEN '" + this.startDate + "' AND '" + this.endDate + "' and " +
					"time BETWEEN '" + this.startHour + ":" + this.startMinute + ":00'" + " AND '" + this.endHour + ":" + this.endMinute + ":00'"; 
		    
		    PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
						
			while (rs.next()) {
				String timestamp = rs.getString("timestamp");
				String temperature = rs.getString("temperature");
				String pressure = rs.getString("pressure");
				String flowrate = rs.getString("flowrate");
				// Put the data into HashMap
				dataMap = new HashMap<String, String>();
				dataMap.put("timestamp", timestamp);
				dataMap.put("temperature", temperature);
				dataMap.put("pressure", pressure);
				dataMap.put("flowrate", flowrate);
				// Add the HashMAp into LinkedList
				dataLinkedMap.add(dataMap);
			}
		}catch (SQLException se) {
			System.out.println(se);
		}
	}
	
	// Rewrite the data to a string which fits the data format of Flot.
	public String getTemperatureFlotData() {				
		String temperatureFlotData;
		String head = "[";
		String tail = "]";
		String comma = ",";
		StringBuffer sb = new StringBuffer();
		
		sb.append(head);	
		for (int i = 0; i < dataLinkedMap.size(); i++) {
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
	// Rewrite the data to a string which fits the data format of Flot.
	public String getPressureFlotData() {				
		String pressureFlotData;
		String head = "[";
		String tail = "]";
		String comma = ",";
		StringBuffer sb = new StringBuffer();
		
		sb.append(head);	
		for (int i = 0; i < dataLinkedMap.size(); i++) {
			String timestamp = dataLinkedMap.get(i).get("timestamp");
			String pressure = dataLinkedMap.get(i).get("pressure");
			
			sb.append(head);
			sb.append(timestamp);
			sb.append(comma);
			sb.append(pressure);
			sb.append(tail);
			sb.append(comma);
		}
		sb.deleteCharAt(sb.lastIndexOf(comma));
		sb.append(tail);
		
		pressureFlotData = sb.toString();
		
		return pressureFlotData;
	}
	// Rewrite the data to a string which fits the data format of Flot.
	public String getFlowRateFlotData() {				
		String flowRateFlotData;
		String head = "[";
		String tail = "]";
		String comma = ",";
		StringBuffer sb = new StringBuffer();
		
		sb.append(head);	
		for (int i = 0; i < dataLinkedMap.size(); i++) {
			String timestamp = dataLinkedMap.get(i).get("timestamp");
			String flowrate = dataLinkedMap.get(i).get("flowrate");
			
			sb.append(head);
			sb.append(timestamp);
			sb.append(comma);
			sb.append(flowrate);
			sb.append(tail);
			sb.append(comma);
		}
		sb.deleteCharAt(sb.lastIndexOf(comma));
		sb.append(tail);
		
		flowRateFlotData = sb.toString();
		
		return flowRateFlotData;
	}

}
