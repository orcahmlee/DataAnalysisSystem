package tw.andrew;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class FactoryControlPanel extends JFrame {
	Button machine1Run, machine1Stop, machine1ReRun;
	Button machine2Run, machine2Stop, machine2ReRun;
	Button machine3Run, machine3Stop, machine3ReRun;
	Button machine4Run, machine4Stop, machine4ReRun;
	TextField textM1, textM2, textM3, textM4;
	
	public FactoryControlPanel() {
		super("Factory Control Panel");
		
		Machines m1 = new Machines("M1", 5000); // 
		machine1Run = new Button("M1 RUN");
		textM1 = new TextField(50);
		machine1ReRun = new Button("M1 RERUN");
		machine1Stop = new Button("M1 STOP");
		machine1Run.addActionListener(new MachineRun(m1, textM1));
		machine1ReRun.addActionListener(new MachineReRun(m1, textM1));
		machine1Stop.addActionListener(new MachineStop(m1, textM1));
		
		add(machine1Run); // The position of button decided by where you add.	
		add(textM1);
		add(machine1ReRun);
		add(machine1Stop);

		Machines m2 = new Machines("M2", 5000); // 
		machine2Run = new Button("M2 RUN");
		textM2 = new TextField(50);
		machine2ReRun = new Button("M2 RERUN");
		machine2Stop = new Button("M2 STOP");
		machine2Run.addActionListener(new MachineRun(m2, textM2));
		machine2ReRun.addActionListener(new MachineReRun(m2, textM2));
		machine2Stop.addActionListener(new MachineStop(m2, textM2));
		
		add(machine2Run); // The position of button decided by where you add.	
		add(textM2);
		add(machine2ReRun);
		add(machine2Stop);

		Machines m3 = new Machines("M3", 5000); // 
		machine3Run = new Button("M3 RUN");
		textM3 = new TextField(50);
		machine3ReRun = new Button("M3 RERUN");
		machine3Stop = new Button("M3 STOP");
		machine3Run.addActionListener(new MachineRun(m3, textM3));
		machine3ReRun.addActionListener(new MachineReRun(m3, textM3));
		machine3Stop.addActionListener(new MachineStop(m3, textM3));
		
		add(machine3Run); // The position of button decided by where you add.	
		add(textM3);
		add(machine3ReRun);
		add(machine3Stop);

		Machines m4 = new Machines("M4", 5000); // 
		machine4Run = new Button("M4 RUN");
		textM4 = new TextField(50);
		machine4ReRun = new Button("M4 RERUN");
		machine4Stop = new Button("M4 STOP");
		machine4Run.addActionListener(new MachineRun(m4, textM4));
		machine4ReRun.addActionListener(new MachineReRun(m4, textM4));
		machine4Stop.addActionListener(new MachineStop(m4, textM4));
		
		add(machine4Run); // The position of button decided by where you add.	
		add(textM4);
		add(machine4ReRun);
		add(machine4Stop);

		setLayout(new FlowLayout()); // *** Layout Manager
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setSize(800, 600);
	}
		
	class MachineRun implements ActionListener {
		private Machines m;
		private TextField text;
		MachineRun(Machines m, TextField text) {
			this.m = m;
			this.text = text;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			m.start();
			text.setText(m.getMachineName() +" is running!");
		}		
	}
	class MachineStop implements ActionListener {
		private Machines m;
		private TextField text;
		MachineStop(Machines m, TextField text) {
			this.m = m;
			this.text = text;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			m.timerStop();
			text.setText(m.getMachineName() +" was stopped!");
			System.out.println(m.getMachineName() + " STOP!");
		}		
	}
	class MachineReRun implements ActionListener {
		private Machines m;
		private TextField text;
		MachineReRun(Machines m, TextField text) {
			this.m = m;
			this.text = text;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			m.timerReRun();
			text.setText(m.getMachineName() + " is rerunning!");
			System.out.println(m.getMachineName() + " RERUN!!!");
		}		
	}
	
	public static void main(String[] args) {
		new FactoryControlPanel();
	}
}