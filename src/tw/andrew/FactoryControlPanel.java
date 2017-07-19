package tw.andrew;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class FactoryControlPanel extends JFrame {
	Button machine1Run, machine1Stop, machine1ReRun, machine2Run, machine2Stop;
	TextField textM1, textM2, textM3, textM4;
	
	public FactoryControlPanel() {
		super("Factory Control Panel");
		
		
		
		Machines m1 = new Machines("M1", 4000);
		machine1Run = new Button("M1 RUN");
		add(machine1Run);
		textM1 = new TextField(70);
		add(textM1);
		machine1Run.addActionListener(new MachineRun(m1, textM1));

		machine1ReRun = new Button("M1 RERUN");
		add(machine1ReRun);
		machine1ReRun.addActionListener(new MachineReRun(m1, textM1));
		
		machine1Stop = new Button("M1 STOP");
		add(machine1Stop);
		machine1Stop.addActionListener(new MachineStop(m1, textM1));

		
//		Machines m2 = new Machines("M2", 4000);
//		machine2Run = new Button("M2 RUN");
//		add(machine2Run);
//		textM2 = new TextField(70);
//		add(textM2);
//		machine2Run.addActionListener(new MachineRun(m2, textM2));
//		
//		machine2Stop = new Button("M2 STOP");
//		add(machine2Stop);
//		machine2Stop.addActionListener(new MachineStop(m2, textM2));
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout()); // *** Layout Manager
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
			text.setText(m.getMachineName() +" stopped!");
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
			text.setText(m.getMachineName() + " reruned!");
			System.out.println(m.getMachineName() + " RERUN!!!");
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		new FactoryControlPanel();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
