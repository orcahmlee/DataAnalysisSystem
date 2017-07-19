package tw.andrew;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.TimerTask;

//Information extends TimerTask. It's can automatically generate by a certain period of time
class InformationOfMachine extends TimerTask {
	private String name;
	InformationOfMachine(String name) {
		this.name = name; // the name of the machine
	}
	@Override
	public void run() {
		// Current date and time
		DateTime DateTime = new DateTime();
		String today = DateTime.getToday();
		String time = DateTime.getTime();
		
		// The temperature of machine
		Temperature temp = new Temperature();
		String temperature = temp.getTemperature();
		
		// The pressure of machine
		Pressure pres = new Pressure();
		String pressure = pres.getPressure();
		
		// The flow rate of machine
		FlowRate flow = new FlowRate();
		String flowrate = flow.getFlowRate();
		
//		// Get the connector drive of MySQL
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			System.out.println(e);
//		}
//		
//		// Get the connection with MySQL
//		Properties prop = new Properties();
//		prop.put("user", "root");
//		prop.put("password", "root");
//		// Autoclose the Connection
//		try (Connection conn = 
//				DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/andrew", prop);)
//		{
//			String sql = 
//					"INSERT INTO factory (machine, date, time, temperature, pressure, flowrate) values (?,?,?,?,?,?)";
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, name);
//			pstmt.setString(2, today);
//			pstmt.setString(3, time);
//			pstmt.setString(4, temperature);
//			pstmt.setString(5, pressure);
//			pstmt.setString(6, flowrate);
//			pstmt.addBatch();
//			pstmt.executeBatch();
//			System.out.println(name + " : Sent, OK!");			
//		} catch (SQLException e) {
//			System.out.println(e);
//		}
		
		
		System.out.println(name + " => ");
		System.out.println("TODAY is: " + today);
		System.out.println("NOW is: " + time);
		System.out.println("TEMPERATURE: " + temperature);
		System.out.println("PRESSURE: " + pressure);
		System.out.println("FLOWRATE: " + flowrate);
	}
}