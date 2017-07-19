package tw.andrew;
/*
 *  This class create the data of a machine.
 */

import java.util.Timer;

// Machines extends Thread
class BUG_Machines extends Thread {
	private String name;
	private int period;
	private Timer timer = new Timer();
	
	InformationOfMachine info;
	
	BUG_Machines(String name, int period) {
		this.name = name; // Set the name of the machine
		this.period = period; // Set the period of time of the data being sent
		info = new InformationOfMachine(name);
	}
	@Override
	public void run() {
		timer.schedule(info, 0, period);	 // the period of time of the data being sent
	}	
	public void timerStop() {
		timer.cancel();
	}
	public void timerReRun() {
		timer.schedule(info, 0, period);
	}

	public String getMachineName() {
		return name;
	}
}
