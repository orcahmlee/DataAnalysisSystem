package tw.andrew;
/*
 * This class extends Thread.
 * Each Thread represent one machine.
 */

import java.util.Timer;

class Machines extends Thread {
	private String name;
	private double temperatureSetPoint, temperatureAccuracy;
	private double pressureSetPoint, pressureAccuracy;
	private double flowRateSetPoint, flowRateAccuracy;
	private int period;
	private Timer timer;
	private InformationOfMachine info;
	
	Machines(String name, 
			double temperatureSetPoint, 
			double temperatureAccuracy, 
			double pressureSetPoint, 
			double pressureAccuracy, 
			double flowRateSetPoint, 
			double flowRateAccuracy,
			int period) {
		this.name = name; // Set the name of the machine
		this.temperatureSetPoint = temperatureSetPoint; // The temperatureSetPoint of this machine
		this.temperatureAccuracy = temperatureAccuracy; // The temperatureAccuracy of this machine
		this.pressureSetPoint = pressureSetPoint; // The pressureSetPoint of this machine
		this.pressureAccuracy = pressureAccuracy; // The pressureAccuracy of this machine
		this.flowRateSetPoint = flowRateSetPoint; // The flowRateSetPoint of this machine
		this.flowRateAccuracy = flowRateAccuracy; // The flowRateAccuracy of this machine
		this.period = period; // Set the period of time of the data being sent
	}
	@Override
	public void run() {
		timer = new Timer(); // Create a Timer
		info = new InformationOfMachine(name,
				temperatureSetPoint,temperatureAccuracy,
				pressureSetPoint,pressureAccuracy,
				flowRateSetPoint,flowRateAccuracy
				); // New a TimerTask
		timer.schedule(info, 0, period);	 // period: The period of time of the data being generated and sent
	}	
	public void timerStop() {
		timer.cancel(); // Stop the current timer
	}
	public void timerReRun() {
		timer.cancel(); // ***Stop the current timer. To avoid user rerun the new timer before cancel the previous timer because it can cause two TimerTask run simultaneously. 
		timer = new Timer(); // Create a new Timer
		info = new InformationOfMachine(name,
				temperatureSetPoint,temperatureAccuracy,
				pressureSetPoint,pressureAccuracy,
				flowRateSetPoint,flowRateAccuracy
				); // Create a new TimerTask
		timer.schedule(info, 0, period);
	}
	
	public String getMachineName() {
		return name;
	}
}