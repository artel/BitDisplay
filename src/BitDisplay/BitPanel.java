package BitDisplay;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BitPanel extends JPanel implements MouseListener { 
	private JTextField textField = new JTextField(1);
	private BinaryPanel binaryPanel;
	public BitPanel(BinaryPanel binaryPanel) {
		super(new FlowLayout(FlowLayout.CENTER, 0, 0));
		this.binaryPanel = binaryPanel;
		textField.setEditable(false);
		textField.setHorizontalAlignment(JTextField.CENTER);
		textField.setBackground(Color.white);
		textField.setBorder(BorderFactory.createLineBorder(Color.black));
		textField.addMouseListener(this);
		add(textField);
	}
	
	public void setToZero() {
		synchronized(binaryPanel) {
			textField.setText("0");
			textField.setForeground(Color.red);
		}
	}
	
	public void setToOne() {
		synchronized(binaryPanel) {
			textField.setText("1");
			textField.setForeground(Color.green);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (textField.getText().equals("0")) {
			setToOne();
		}
		else {
			setToZero();
		}
		
		//TODO: request worker to do this binaryPanel.updateClick();
		binaryPanel.applet.worker.scheduleUpdateClick();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public String getText() {
		synchronized(binaryPanel) {
			return textField.getText();
		}
	}

}
