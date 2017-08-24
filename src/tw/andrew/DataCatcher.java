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

public class DataCatcher {
	private String equipment;
	private int numberOfData;
	private HashMap<String, String> dataMap;
	private LinkedList<HashMap<String, String>> dataLinkedMap = new LinkedList<HashMap<String, String>>();
	
	DataCatcher(String equipment, int minute) {
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
				String id = rs.getString("id");
				String machine = rs.getString("machine");
				String date = rs.getString("date");
				String time = rs.getString("time");
				String temperature = rs.getString("temperature");
				String pressure = rs.getString("pressure");
				String flowrate = rs.getString("flowrate");
				// Put the data into HashMap
				dataMap = new HashMap<String, String>();
				dataMap.put("id", id);
				dataMap.put("machine", machine);
				dataMap.put("date", date);
				dataMap.put("time", time);
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
	
	public LinkedList<HashMap<String, String>> getDataLinkedMap() {
		return dataLinkedMap;
	}
	
	public int getNumberOfData() {
		return numberOfData;
	}
	
	public String getAvgOfTemperature() {
		double[] tempTemp = new double[numberOfData];
		for (int i = 0; i < dataLinkedMap.size(); i++) {
			String temperature = dataLinkedMap.get(i).get("temperature");
			double temp = Double.parseDouble(temperature);
			Arrays.fill(tempTemp, i, i+1, temp);			
		}
		Mean mean = new Mean();
		double dAvg = mean.evaluate(tempTemp);
		BigDecimal bAvg = new BigDecimal(dAvg).setScale(1, BigDecimal.ROUND_HALF_UP);
		String avg = bAvg.toString();
		
		return avg;
	}
	
	public String getStdOfTemperature() {
		double[] tempTemp = new double[numberOfData];
		for (int i = 0; i < dataLinkedMap.size(); i++) {
			String temperature = dataLinkedMap.get(i).get("temperature");
			double temp = Double.parseDouble(temperature);
			Arrays.fill(tempTemp, i, i+1, temp);			
		}
		StandardDeviation sd = new StandardDeviation();
		double dStd = sd.evaluate(tempTemp);
		BigDecimal bStd = new BigDecimal(dStd).setScale(4, BigDecimal.ROUND_HALF_UP);
		String std = bStd.toString();
		
		return std;
	}
	
	public String getAvgOfPressure() {
		double[] tempPres = new double[numberOfData];
		for (int i = 0; i < dataLinkedMap.size(); i++) {
			String pressure = dataLinkedMap.get(i).get("pressure");
			double temp = Double.parseDouble(pressure);
			Arrays.fill(tempPres, i, i+1, temp);			
		}
		Mean mean = new Mean();
		double dAvg = mean.evaluate(tempPres);
		BigDecimal bAvg = new BigDecimal(dAvg).setScale(1, BigDecimal.ROUND_HALF_UP);
		String avg = bAvg.toString();
		
		return avg;
	}
	
	public String getStdOfPressure() {
		double[] tempPres = new double[numberOfData];
		for (int i = 0; i < dataLinkedMap.size(); i++) {
			String pressure = dataLinkedMap.get(i).get("pressure");
			double temp = Double.parseDouble(pressure);
			Arrays.fill(tempPres, i, i+1, temp);			
		}
		StandardDeviation sd = new StandardDeviation();
		double dStd = sd.evaluate(tempPres);
		BigDecimal bStd = new BigDecimal(dStd).setScale(4, BigDecimal.ROUND_HALF_UP);
		String std = bStd.toString();
		
		return std;
	}
	
	public String getAvgOfFlowRate() {
		double[] tempFlow = new double[numberOfData];
		for (int i = 0; i < dataLinkedMap.size(); i++) {
			String flowrate = dataLinkedMap.get(i).get("flowrate");
			double temp = Double.parseDouble(flowrate);
			Arrays.fill(tempFlow, i, i+1, temp);			
		}
		Mean mean = new Mean();
		double dAvg = mean.evaluate(tempFlow);
		BigDecimal bAvg = new BigDecimal(dAvg).setScale(1, BigDecimal.ROUND_HALF_UP);
		String avg = bAvg.toString();
		
		return avg;
	}
	
	public String getStdOfFlowRate() {
		double[] tempFlow = new double[numberOfData];
		for (int i = 0; i < dataLinkedMap.size(); i++) {
			String flowrate = dataLinkedMap.get(i).get("flowrate");
			double temp = Double.parseDouble(flowrate);
			Arrays.fill(tempFlow, i, i+1, temp);			
		}
		StandardDeviation sd = new StandardDeviation();
		double dStd = sd.evaluate(tempFlow);
		BigDecimal bStd = new BigDecimal(dStd).setScale(4, BigDecimal.ROUND_HALF_UP);
		String std = bStd.toString();
		
		return std;
	}
}
