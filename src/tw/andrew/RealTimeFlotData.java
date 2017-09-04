package tw.andrew;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Properties;

import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.json.JSONStringer;
import org.json.JSONWriter;


public class RealTimeFlotData {
	private String equipment;
	private int numberOfData;
	private HashMap<String, String> dataMap;
	private LinkedList<HashMap<String, String>> dataLinkedMap = new LinkedList<HashMap<String, String>>();
	private double[] tempTemp;
	private double[] tempPres;
	private double[] tempFlow;
	private double doubleAvgTemperature;
	private double doubleStdTemperature;
	private double doubleAvgPressure;
	private double doubleStdPressure;
	private double doubleAvgFlowRate;
	private double doubleStdFlowRate;
	private String avgTemperature;
	private String stdTemperature;
	private String avgPressure;
	private String stdPressure;
	private String avgFlowRate;
	private String stdFlowRate;
	
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
			String sql = "SELECT timestamp, temperature, pressure, flowrate FROM " +
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
			
			// Parse the data from string to double.
			// Put the data to an array for calculating the "mean" and "standard deviation".
			// Calculate the "mean" and "standard deviation" which in this array.
			tempTemp = new double[numberOfData];
			tempPres = new double[numberOfData];
			tempFlow = new double[numberOfData];
			Mean mean = new Mean();
			StandardDeviation sd = new StandardDeviation();
			for (int i = 0; i < dataLinkedMap.size(); i++) {
				
				// The array which contains the double data of temperature.
				String temperature = dataLinkedMap.get(i).get("temperature"); // Retrieve the string data from LinkedList.
				double temp = Double.parseDouble(temperature); // Parse the data from string to double.
				Arrays.fill(tempTemp, i, i+1, temp);	 // Add the double data to an array.

				// The array which contains the double data of pressure.		
				String pressure = dataLinkedMap.get(i).get("pressure");
				double pres = Double.parseDouble(pressure);
				Arrays.fill(tempPres, i, i+1, pres);	

				// The array which contains the double data of flowrate.
				String flowrate = dataLinkedMap.get(i).get("flowrate");
				double fr = Double.parseDouble(flowrate);
				Arrays.fill(tempFlow, i, i+1, fr);	
			}
			doubleAvgTemperature = mean.evaluate(tempTemp); // Calculating the "mean" of the data which in this array.
			doubleStdTemperature = sd.evaluate(tempTemp); // Calculating the "standard deviation" of the data which in this array.
			doubleAvgPressure = mean.evaluate(tempPres);
			doubleStdPressure = sd.evaluate(tempPres);
			doubleAvgFlowRate = mean.evaluate(tempFlow);
			doubleStdFlowRate = sd.evaluate(tempFlow);

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

	public String getAvgTemperature() {
		BigDecimal temp = new BigDecimal(doubleAvgTemperature).setScale(1, BigDecimal.ROUND_HALF_UP); // 取小數點後第一位，四捨五入
		avgTemperature = temp.toString();
		
		return avgTemperature;
	}
	
	public String getStdTemperature() {
		BigDecimal temp = new BigDecimal(doubleStdTemperature).setScale(3, BigDecimal.ROUND_HALF_UP); // 取小數點後第三位，四捨五入
		stdTemperature = temp.toString();
		
		return stdTemperature;
	}
	
	public String getAvgPressure() {
		BigDecimal temp = new BigDecimal(doubleAvgPressure).setScale(1, BigDecimal.ROUND_HALF_UP); // 取小數點後第一位，四捨五入
		avgPressure = temp.toString();

		return avgPressure;
	}
	
	public String getStdPressure() {
		BigDecimal temp = new BigDecimal(doubleStdPressure).setScale(3, BigDecimal.ROUND_HALF_UP); // 取小數點後第三位，四捨五入
		stdPressure = temp.toString();
		
		return stdPressure;
	}
	
	public String getAvgFlowRate() {
		BigDecimal temp = new BigDecimal(doubleAvgFlowRate).setScale(1, BigDecimal.ROUND_HALF_UP); // 取小數點後第一位，四捨五入
		avgFlowRate = temp.toString();

		return avgFlowRate;
	}
	
	public String getStdFlowRate() {
		BigDecimal temp = new BigDecimal(doubleStdFlowRate).setScale(3, BigDecimal.ROUND_HALF_UP); // 取小數點後第三位，四捨五入
		stdFlowRate = temp.toString();

		return stdFlowRate;
	}	
}
