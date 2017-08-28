package tw.andrew;
/*
 * This class collects the informations of one machine.
 * This class send the informations to database.
 * This class extends TimerTask.
 * It's can automatically generate and send data to database by a certain period of time
 */
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.TimerTask;

class InformationOfMachine extends TimerTask {
	private String name;
	private double temperatureSetPoint, temperatureAccuracy;
	private double pressureSetPoint, pressureAccuracy;
	private double flowRateSetPoint, flowRateAccuracy;
	
	InformationOfMachine(String name, 
			double temperatureSetPoint, 
			double temperatureAccuracy, 
			double pressureSetPoint, 
			double pressureAccuracy, 
			double flowRateSetPoint, 
			double flowRateAccuracy) {
		this.name = name; // The name of this machine
		this.temperatureSetPoint = temperatureSetPoint; // The temperatureSetPoint of this machine
		this.temperatureAccuracy = temperatureAccuracy; // The temperatureAccuracy of this machine
		this.pressureSetPoint = pressureSetPoint; // The pressureSetPoint of this machine
		this.pressureAccuracy = pressureAccuracy; // The pressureAccuracy of this machine
		this.flowRateSetPoint = flowRateSetPoint; // The flowRateSetPoint of this machine
		this.flowRateAccuracy = flowRateAccuracy; // The flowRateAccuracy of this machine
	}
	@Override
	public void run() {
		// Current date and time
		DateTime DateTime = new DateTime();
		String today = DateTime.getToday();
		String time = DateTime.getTime();
		String hour = DateTime.getHour();
		String minute = DateTime.getMinute();
		String second = DateTime.getSecond();
		String timestamp = DateTime.getTimestamp();
		
		// The temperature of machine
		Temperature temp = new Temperature(temperatureSetPoint, temperatureAccuracy);
		String temperature = temp.getTemperature();
		
		// The pressure of machine
		Pressure pres = new Pressure(pressureSetPoint, pressureAccuracy);
		String pressure = pres.getPressure();
		
		// The flow rate of machine
		FlowRate flow = new FlowRate(flowRateSetPoint, flowRateAccuracy);
		String flowrate = flow.getFlowRate();
		
		// Get the connector drive of MySQL
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
		
		// Get the connection with MySQL
		Properties prop = new Properties();
		prop.put("user", "root");
		prop.put("password", "root");
		
		// Autoclose the Connection
		try (Connection conn = 
				DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/andrew", prop);)
		{
			String sql = 
					"INSERT INTO factory (machine, date, time, hour, minute, second, timestamp, temperature, pressure, flowrate) values (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, today);
			pstmt.setString(3, time);
			pstmt.setString(4, hour);
			pstmt.setString(5, minute);
			pstmt.setString(6, second);
			pstmt.setString(7, timestamp);
			pstmt.setString(8, temperature);
			pstmt.setString(9, pressure);
			pstmt.setString(10, flowrate);
			pstmt.addBatch();
			pstmt.executeBatch();
			System.out.println(name + " : Sent, OK!");			
		} catch (SQLException e) {
			System.out.println(e);
		}
				
//		System.out.println(name + " => ");
//		System.out.println("TODAY is: " + today);
//		System.out.println("NOW is: " + time);
//		System.out.println("TEMPERATURE: " + temperature);
//		System.out.println("PRESSURE: " + pressure);
//		System.out.println("FLOWRATE: " + flowrate);
	}
}