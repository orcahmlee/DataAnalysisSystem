package tw.andrew;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Properties;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

public class HistoryFlotData {
	private String equipment;
	private String startDate, startHour, startMinute;
	private String endDate, endHour, endMinute;
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
	
	HistoryFlotData(String equipment, String startDate, String endDate, String startHour, String endHour, String startMinute, String endMinute) {
		this.equipment = equipment; // The name of Machine	
		this.startDate = startDate;		
		this.endDate = endDate;
		
		int intStartHour = Integer.parseInt(startHour);
		String strStartHour = Integer.toString(intStartHour);
		this.startHour = (intStartHour < 10) ? ("0" + strStartHour) : (startHour);
		
		int intEndHour = Integer.parseInt(endHour);
		String strEndHour = Integer.toString(intEndHour);
		this.endHour = (intEndHour < 10) ? ("0" + strEndHour) : (endHour);		
		
		int intStartMinute = Integer.parseInt(startMinute);
		String strStartMinute = Integer.toString(intStartMinute);
		this.startMinute = (intStartMinute < 10) ? ("0" + strStartMinute) : (startMinute);		
		
		int intEndMinute = Integer.parseInt(endMinute);
		String strEndMinute = Integer.toString(intEndMinute);
		this.endMinute = (intEndMinute < 10) ? ("0" + strEndMinute) : (endMinute);		
				
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
			// // MySQL query: get the count of the data
			String count = 
					"SELECT COUNT(*) FROM factory " +
					"WHERE machine = '" + this.equipment + "' and " + 
					"datetime BETWEEN '" + this.startDate + "," + this.startHour + ":" + this.startMinute + ":00' " +
							   "AND '" + this.endDate + "," + this.endHour + ":" + this.endMinute + ":00' ";
			PreparedStatement ps = conn.prepareStatement(count);
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				numberOfData = res.getInt(1); // Assign the "Count" to "numberOfData".
			}
			
			// MySQL query: 
			String sql = 
					"SELECT * FROM factory " +
					"WHERE machine = '" + this.equipment + "' and " + 
					"datetime BETWEEN '" + this.startDate + "," + this.startHour + ":" + this.startMinute + ":00' " +
							   "AND '" + this.endDate + "," + this.endHour + ":" + this.endMinute + ":00' ";
		    
		    PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
						
			while (rs.next()) {
				String machine = rs.getString("machine");
				String date = rs.getString("date");
				String time = rs.getString("time");
				String timestamp = rs.getString("timestamp");
				String temperature = rs.getString("temperature");
				String pressure = rs.getString("pressure");
				String flowrate = rs.getString("flowrate");
				// Put the data into HashMap
				dataMap = new HashMap<String, String>();
				dataMap.put("machine", machine);
				dataMap.put("date", date);
				dataMap.put("time", time);
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

	public LinkedList<HashMap<String, String>> getDataLinkedMap() {
		return dataLinkedMap;
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

	// Calculating the mean, standard deviation and PDF of the data which selected by user.
	// Rewrite the PDF data to a string which fits the data format of Flot.
	public String getPDFOfTemperature() {
		double mean = doubleAvgTemperature;
		double sd = doubleStdTemperature;
		double x1, x2, x3, x4, x5, x6, x7;
		double y1, y2, y3, y4, y5, y6, y7;
		
		y1 = y2 = y3 = y4 = y5 = y6 = y7 = 0.0;
		NormalDistribution nd = new NormalDistribution(mean, sd);
		LinkedList<Object> xList = new LinkedList<Object>();
		LinkedList<Object> yList = new LinkedList<Object>();
				
		x1 = mean - (3 * sd); xList.add(x1);
		x2 = mean - (2 * sd); xList.add(x2);
		x3 = mean - (1 * sd); xList.add(x3);
		x4 = mean;            xList.add(x4);
		x5 = mean + (1 * sd); xList.add(x5);
		x6 = mean + (2 * sd); xList.add(x6);
		x7 = mean + (3 * sd); xList.add(x7);
				
		yList.add(y1); yList.add(y2); yList.add(y3);
		yList.add(y4);
		yList.add(y5); yList.add(y6); yList.add(y7);		
		
		String pDFOfTemperature;
		String head = "[";
		String tail = "]";
		String comma = ",";
		StringBuffer sb = new StringBuffer();

		sb.append(head);	
		for (int i = 0; i < xList.size(); i++) {
			double x = (Double)xList.get(i);
			double y = (Double)yList.get(i);
			y = nd.density(x);
			
			sb.append(head);
			sb.append(x);
			sb.append(comma);
			sb.append(y);
			sb.append(tail);
			sb.append(comma);
		}
		sb.deleteCharAt(sb.lastIndexOf(comma));
		sb.append(tail);
		
		pDFOfTemperature = sb.toString();

		return pDFOfTemperature;
	}
	
	// Calculating the mean, standard deviation and PDF of the data which selected by user.
	// Rewrite the PDF data to a string which fits the data format of Flot.
	public String getPDFOfPressure() {
		double mean = doubleAvgPressure;
		double sd = doubleStdPressure;
		double x1, x2, x3, x4, x5, x6, x7;
		double y1, y2, y3, y4, y5, y6, y7;
		
		y1 = y2 = y3 = y4 = y5 = y6 = y7 = 0.0;
		NormalDistribution nd = new NormalDistribution(mean, sd);
		LinkedList<Object> xList = new LinkedList<Object>();
		LinkedList<Object> yList = new LinkedList<Object>();
				
		x1 = mean - (3 * sd); xList.add(x1);
		x2 = mean - (2 * sd); xList.add(x2);
		x3 = mean - (1 * sd); xList.add(x3);
		x4 = mean;            xList.add(x4);
		x5 = mean + (1 * sd); xList.add(x5);
		x6 = mean + (2 * sd); xList.add(x6);
		x7 = mean + (3 * sd); xList.add(x7);
				
		yList.add(y1); yList.add(y2); yList.add(y3);
		yList.add(y4);
		yList.add(y5); yList.add(y6); yList.add(y7);		
		
		String pDFOfPressure;
		String head = "[";
		String tail = "]";
		String comma = ",";
		StringBuffer sb = new StringBuffer();

		sb.append(head);	
		for (int i = 0; i < xList.size(); i++) {
			double x = (Double)xList.get(i);
			double y = (Double)yList.get(i);
			y = nd.density(x);
			
			sb.append(head);
			sb.append(x);
			sb.append(comma);
			sb.append(y);
			sb.append(tail);
			sb.append(comma);
		}
		sb.deleteCharAt(sb.lastIndexOf(comma));
		sb.append(tail);
		
		pDFOfPressure = sb.toString();
		
		return pDFOfPressure;
	}

	// Calculating the mean, standard deviation and PDF of the data which selected by user.
	// Rewrite the PDF data to a string which fits the data format of Flot.
	public String getPDFOfFlowRate() {
		double mean = doubleAvgFlowRate;
		double sd = doubleStdFlowRate;
		double x1, x2, x3, x4, x5, x6, x7;
		double y1, y2, y3, y4, y5, y6, y7;
		
		y1 = y2 = y3 = y4 = y5 = y6 = y7 = 0.0;
		NormalDistribution nd = new NormalDistribution(mean, sd);
		LinkedList<Object> xList = new LinkedList<Object>();
		LinkedList<Object> yList = new LinkedList<Object>();
				
		x1 = mean - (3 * sd); xList.add(x1);
		x2 = mean - (2 * sd); xList.add(x2);
		x3 = mean - (1 * sd); xList.add(x3);
		x4 = mean;            xList.add(x4);
		x5 = mean + (1 * sd); xList.add(x5);
		x6 = mean + (2 * sd); xList.add(x6);
		x7 = mean + (3 * sd); xList.add(x7);
				
		yList.add(y1); yList.add(y2); yList.add(y3);
		yList.add(y4);
		yList.add(y5); yList.add(y6); yList.add(y7);		
		
		String pDFOfFlowRate;
		String head = "[";
		String tail = "]";
		String comma = ",";
		StringBuffer sb = new StringBuffer();

		sb.append(head);	
		for (int i = 0; i < xList.size(); i++) {
			double x = (Double)xList.get(i);
			double y = (Double)yList.get(i);
			y = nd.density(x);
			
			sb.append(head);
			sb.append(x);
			sb.append(comma);
			sb.append(y);
			sb.append(tail);
			sb.append(comma);
		}
		sb.deleteCharAt(sb.lastIndexOf(comma));
		sb.append(tail);
		
		pDFOfFlowRate = sb.toString();
		
		return pDFOfFlowRate;
	}
	
	// Rewrite the Normal Distribution data to a string which fits the data format of Flot.
	public String getNormalDistributionOfTemperature() {
		double mean = doubleAvgTemperature;
		double sd = 1.0;
		double x1, x2, x3, x4, x5, x6, x7;
		double y1, y2, y3, y4, y5, y6, y7;
		
		y1 = y2 = y3 = y4 = y5 = y6 = y7 = 0.0;
		NormalDistribution nd = new NormalDistribution(mean, sd);
		LinkedList<Object> xList = new LinkedList<Object>();
		LinkedList<Object> yList = new LinkedList<Object>();
				
		x1 = mean - (3 * sd); xList.add(x1);
		x2 = mean - (2 * sd); xList.add(x2);
		x3 = mean - (1 * sd); xList.add(x3);
		x4 = mean;            xList.add(x4);
		x5 = mean + (1 * sd); xList.add(x5);
		x6 = mean + (2 * sd); xList.add(x6);
		x7 = mean + (3 * sd); xList.add(x7);
				
		yList.add(y1); yList.add(y2); yList.add(y3);
		yList.add(y4);
		yList.add(y5); yList.add(y6); yList.add(y7);		
		
		String normalDistributionOfTemperature;
		String head = "[";
		String tail = "]";
		String comma = ",";
		StringBuffer sb = new StringBuffer();

		sb.append(head);	
		for (int i = 0; i < xList.size(); i++) {
			double x = (Double)xList.get(i);
			double y = (Double)yList.get(i);
			y = nd.density(x);
			
			sb.append(head);
			sb.append(x);
			sb.append(comma);
			sb.append(y);
			sb.append(tail);
			sb.append(comma);
		}
		sb.deleteCharAt(sb.lastIndexOf(comma));
		sb.append(tail);
		
		normalDistributionOfTemperature = sb.toString();
		
		return normalDistributionOfTemperature;
	}

	// Rewrite the Normal Distribution data to a string which fits the data format of Flot.
	public String getNormalDistributionOfPressure() {
		double mean = doubleAvgPressure;
		double sd = 1.0;
		double x1, x2, x3, x4, x5, x6, x7;
		double y1, y2, y3, y4, y5, y6, y7;
		
		y1 = y2 = y3 = y4 = y5 = y6 = y7 = 0.0;
		NormalDistribution nd = new NormalDistribution(mean, sd);
		LinkedList<Object> xList = new LinkedList<Object>();
		LinkedList<Object> yList = new LinkedList<Object>();
				
		x1 = mean - (3 * sd); xList.add(x1);
		x2 = mean - (2 * sd); xList.add(x2);
		x3 = mean - (1 * sd); xList.add(x3);
		x4 = mean;            xList.add(x4);
		x5 = mean + (1 * sd); xList.add(x5);
		x6 = mean + (2 * sd); xList.add(x6);
		x7 = mean + (3 * sd); xList.add(x7);
				
		yList.add(y1); yList.add(y2); yList.add(y3);
		yList.add(y4);
		yList.add(y5); yList.add(y6); yList.add(y7);		
		
		String normalDistributionOfPressure;
		String head = "[";
		String tail = "]";
		String comma = ",";
		StringBuffer sb = new StringBuffer();

		sb.append(head);	
		for (int i = 0; i < xList.size(); i++) {
			double x = (Double)xList.get(i);
			double y = (Double)yList.get(i);
			y = nd.density(x);
			
			sb.append(head);
			sb.append(x);
			sb.append(comma);
			sb.append(y);
			sb.append(tail);
			sb.append(comma);
		}
		sb.deleteCharAt(sb.lastIndexOf(comma));
		sb.append(tail);
		
		normalDistributionOfPressure = sb.toString();
		
		return normalDistributionOfPressure;
	}

	// Rewrite the Normal Distribution data to a string which fits the data format of Flot.
	public String getNormalDistributionOfFlowRate() {
		double mean = doubleAvgFlowRate;
		double sd = 1.0;
		double x1, x2, x3, x4, x5, x6, x7;
		double y1, y2, y3, y4, y5, y6, y7;
		
		y1 = y2 = y3 = y4 = y5 = y6 = y7 = 0.0;
		NormalDistribution nd = new NormalDistribution(mean, sd);
		LinkedList<Object> xList = new LinkedList<Object>();
		LinkedList<Object> yList = new LinkedList<Object>();
				
		x1 = mean - (3 * sd); xList.add(x1);
		x2 = mean - (2 * sd); xList.add(x2);
		x3 = mean - (1 * sd); xList.add(x3);
		x4 = mean;            xList.add(x4);
		x5 = mean + (1 * sd); xList.add(x5);
		x6 = mean + (2 * sd); xList.add(x6);
		x7 = mean + (3 * sd); xList.add(x7);
				
		yList.add(y1); yList.add(y2); yList.add(y3);
		yList.add(y4);
		yList.add(y5); yList.add(y6); yList.add(y7);		
		
		String normalDistributionOfFlowRate;
		String head = "[";
		String tail = "]";
		String comma = ",";
		StringBuffer sb = new StringBuffer();

		sb.append(head);	
		for (int i = 0; i < xList.size(); i++) {
			double x = (Double)xList.get(i);
			double y = (Double)yList.get(i);
			y = nd.density(x);
			
			sb.append(head);
			sb.append(x);
			sb.append(comma);
			sb.append(y);
			sb.append(tail);
			sb.append(comma);
		}
		sb.deleteCharAt(sb.lastIndexOf(comma));
		sb.append(tail);
		
		normalDistributionOfFlowRate = sb.toString();
		
		return normalDistributionOfFlowRate;
	}

}
