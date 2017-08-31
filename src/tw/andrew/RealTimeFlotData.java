package tw.andrew;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Properties;

import org.json.JSONStringer;
import org.json.JSONWriter;


public class RealTimeFlotData {
	private String equipment;
	private int numberOfData;
	private HashMap<String, String> dataMap;
	private LinkedList<HashMap<String, String>> dataLinkedMap = new LinkedList<HashMap<String, String>>();
	
	RealTimeFlotData(String equipment, int minute) {
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
			String sql = "SELECT * FROM " +
							"(SELECT * FROM factory WHERE machine = '" + this.equipment + "' ORDER BY id DESC LIMIT "+ this.numberOfData+") " +
					     "sub ORDER BY id ASC";
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
	
	// Rewrite the data to a JSON structure which can fits the data format of Flot.
	public String getRealTimeTemperatureFlotData() {				
		String realTimeTemperatureFlotData;
		
		String object = "{";
		String endObject = "}";
		String array = "[";
		String endArray = "]";
		String colon = ":";
		String comma = ",";
		String data = "data";
		StringBuffer sb = new StringBuffer();
		
		sb.append(object);
		sb.append('"');
		sb.append(data);
		sb.append('"');
		sb.append(colon);
		sb.append(array);
		
		for (int i = 0; i < dataLinkedMap.size(); i++) {
			String timestamp = dataLinkedMap.get(i).get("timestamp");
			String temperature = dataLinkedMap.get(i).get("temperature");
			
			sb.append(timestamp);
			sb.append(comma);
			sb.append(temperature);
			sb.append(comma);
		}
		sb.deleteCharAt(sb.lastIndexOf(comma));
		sb.append(endArray);
		sb.append(endObject);
		
		realTimeTemperatureFlotData = sb.toString();
		
		return realTimeTemperatureFlotData;
	}
	
	// Rewrite the data to a JSON structure which can fits the data format of Flot.
	public String getRealTimePressureFlotData() {				
		String realTimePressureFlotData;
		
		String object = "{";
		String endObject = "}";
		String array = "[";
		String endArray = "]";
		String colon = ":";
		String comma = ",";
		String data = "data";
		StringBuffer sb = new StringBuffer();
		
		sb.append(object);
		sb.append('"');
		sb.append(data);
		sb.append('"');
		sb.append(colon);
		sb.append(array);
		
		for (int i = 0; i < dataLinkedMap.size(); i++) {
			String timestamp = dataLinkedMap.get(i).get("timestamp");
			String pressure = dataLinkedMap.get(i).get("pressure");
			
			sb.append(timestamp);
			sb.append(comma);
			sb.append(pressure);
			sb.append(comma);
		}
		sb.deleteCharAt(sb.lastIndexOf(comma));
		sb.append(endArray);
		sb.append(endObject);
		
		realTimePressureFlotData = sb.toString();
		
		return realTimePressureFlotData;
	}
	
	// Rewrite the data to a JSON structure which can fits the data format of Flot.
	public String getRealTimeFlowRateFlotData() {				
		String realTimeFlowRateFlotData;
		
		String object = "{";
		String endObject = "}";
		String array = "[";
		String endArray = "]";
		String colon = ":";
		String comma = ",";
		String data = "data";
		StringBuffer sb = new StringBuffer();
		
		sb.append(object);
		sb.append('"');
		sb.append(data);
		sb.append('"');
		sb.append(colon);
		sb.append(array);
		
		for (int i = 0; i < dataLinkedMap.size(); i++) {
			String timestamp = dataLinkedMap.get(i).get("timestamp");
			String flowrate = dataLinkedMap.get(i).get("flowrate");
			
			sb.append(timestamp);
			sb.append(comma);
			sb.append(flowrate);
			sb.append(comma);
		}
		sb.deleteCharAt(sb.lastIndexOf(comma));
		sb.append(endArray);
		sb.append(endObject);
		
		realTimeFlowRateFlotData = sb.toString();
		
		return realTimeFlowRateFlotData;
	}
		
	public int getNumberOfData() {
		return numberOfData;
	}
}
