package tw.andrew;
/*
 *  This class create the data of a machine.
 */

import java.util.Timer;

// Machines extends Thread
class BUG_Machines extends Thread {
	private String name;
	private int period;
	private Timer timer;
	
	InformationOfMachine info;
	
	BUG_Machines(String name, int period) {
		this.name = name; // Set the name of the machine
		this.period = period; // Set the period of time of the data being sent
	}
	@Override
	public void run() {
		timer = new Timer();
		info = new InformationOfMachine(name);
		timer.schedule(info, 0, period);	 // the period of time of the data being sent
	}	
	public void timerStop() {
		timer.cancel();
	}
	public void timerReRun() {
//		int n = timer.purge();
//		System.out.println(n);
		timer = new Timer();
		info = new InformationOfMachine(name);
		timer.schedule(info, 0, period);
	}

	public String getMachineName() {
		return name;
	}
}
