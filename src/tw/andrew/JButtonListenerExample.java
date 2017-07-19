package tw.andrew;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JButtonListenerExample{
  static JFrame frame;

  public static void main(String[] args){
    // schedule this for the event dispatch thread (edt)
    SwingUtilities.invokeLater(new Runnable(){
      public void run(){
        displayJFrame();
      }
    });
  }

  static void displayJFrame(){
    frame = new JFrame("Our JButton listener example");

    // create our jbutton
    JButton showDialogButton = new JButton("Click Me");
    
    // add the listener to the jbutton to handle the "pressed" event
    
    ActionListener loo = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
	        JDialog d = new JDialog(frame, "Hello", true);
	        d.setLocationRelativeTo(frame);
	        d.setVisible(true);			
		}
	};
	showDialogButton.addActionListener(loo);
    
//    showDialogButton.addActionListener(new ActionListener(){
//      public void actionPerformed(ActionEvent e){
//        // display/center the jdialog when the button is pressed
//        JDialog d = new JDialog(frame, "Hello", true);
//        d.setLocationRelativeTo(frame);
//        d.setVisible(true);
//      }
//    });

    // put the button on the frame
    frame.getContentPane().setLayout(new FlowLayout());
    frame.add(showDialogButton);

    // set up the jframe, then display it
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setPreferredSize(new Dimension(300, 200));
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}