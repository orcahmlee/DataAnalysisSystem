package tw.andrew;

import java.util.HashMap;
import java.util.LinkedList;

public class TEST_DataCatcher {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		
		DataCatcher dc = new DataCatcher("M1", 60);
		LinkedList<HashMap<String, String>> dataLinkedMap = dc.getDataLinkedMap();
		
		System.out.println("Data Get: " + (System.currentTimeMillis()-start));
		
//		System.out.println(dataLinkedMap);
//		System.out.println(dataLinkedMap.size());
//		System.out.println("--------------------------------------------");
//
//		for (int i = 0; i < dataLinkedMap.size(); i++) {
//			String id = dataLinkedMap.get(i).get("id");
//			String machine = dataLinkedMap.get(i).get("machine");
//			String date = dataLinkedMap.get(i).get("date");
//			String time = dataLinkedMap.get(i).get("time");
//			String temperature = dataLinkedMap.get(i).get("temperature");
//			String pressure = dataLinkedMap.get(i).get("pressure");
//			String flowrate = dataLinkedMap.get(i).get("flowrate");
//			System.out.println(id + " | " + machine + " | " + date + " | " + time + " | " + temperature + " | " + pressure + " | " + flowrate);
//			System.out.println(temperature);
//		}
		
		System.out.println("Number of Data: " + dc.getNumberOfData());
		System.out.println("---------------------------");		
		System.out.println("Avg of Temp: " + dc.getAvgOfTemperature());
		System.out.println("Std of Temp: " + dc.getStdOfTemperature());
		System.out.println("---------------------------");
		System.out.println("Avg of Pres: " + dc.getAvgOfPressure());
		System.out.println("Std of Pres: " + dc.getStdOfPressure());
		System.out.println("---------------------------");
		System.out.println("Avg of Flow: " + dc.getAvgOfFlowRate());
		System.out.println("Std of Flow: " + dc.getStdOfFlowRate());
		System.out.println("Data Analysis: " + (System.currentTimeMillis()-start));
	}
}
