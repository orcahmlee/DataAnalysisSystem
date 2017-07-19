package tw.andrew;
/*
 * This class extends Thread.
 * Each Thread represent one machine.
 */

import java.util.Timer;

class Machines extends Thread {
	private String name;
	private int period;
	private Timer timer;
	private InformationOfMachine info;
	
	Machines(String name, int period) {
		this.name = name; // Set the name of the machine
		this.period = period; // Set the period of time of the data being sent
	}
	@Override
	public void run() {
		timer = new Timer(); // Create a Timer
		info = new InformationOfMachine(name); // New a TimerTask
		timer.schedule(info, 0, period);	 // period: The period of time of the data being generated and sent
	}	
	public void timerStop() {
		timer.cancel(); // Stop the current timer
	}
	public void timerReRun() {
		timer.cancel(); // ***Stop the current timer. To avoid user rerun the new timer before cancel the previous timer because it can cause two TimerTask run simultaneously. 
		timer = new Timer(); // Create a new Timer
		info = new InformationOfMachine(name); // Create a new TimerTask
		timer.schedule(info, 0, period);
	}

	
	public String getMachineName() {
		return name;
	}
}
