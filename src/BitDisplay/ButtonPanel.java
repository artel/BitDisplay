package BitDisplay;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ButtonPanel extends JPanel implements ActionListener {
	Main applet;
	
	public ButtonPanel(Main applet) {
		super(new GridLayout(2, 3, 8, 8));
		this.applet = applet;
		JButton button;
		
		button = new JButton("Min");
		button.setActionCommand("min");
		button.addActionListener(this);
		add(button);
		
		button = new JButton("Zero");
		button.setActionCommand("zero");
		button.addActionListener(this);
		add(button);
		
		button = new JButton("Max");
		button.setActionCommand("max");
		button.addActionListener(this);
		add(button);
		
		button = new JButton("Decrement");
		button.setActionCommand("dec");
		button.addActionListener(this);
		add(button);
		
		button = new JButton("Increment");
		button.setActionCommand("inc");
		button.addActionListener(this);
		add(button);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if (cmd.equals("min")){
			applet.binaryPanel.setNum(Integer.MIN_VALUE);
		} else if (cmd.equals("zero")){
			applet.binaryPanel.setNum(0);
		} else if (cmd.equals("max")){
			applet.binaryPanel.setNum(Integer.MAX_VALUE);
		} else if (cmd.equals("dec")){
			applet.binaryPanel.decNum();
		} else if (cmd.equals("inc")){
			applet.binaryPanel.incNum();
		}
		else {
			return;
		}
		
		applet.updateTextField(applet.binaryPanel.getNum() + "");
		
		applet.worker.scheduleUpdateNum();
	}
	
	
}
