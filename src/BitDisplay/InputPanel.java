package BitDisplay;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class InputPanel extends JPanel {
	protected JTextField textField;
	private Main applet;
	
	public InputPanel(Main main) {
		super(new BorderLayout());
		this.applet = main;
		textField = new InputTxtField(10, applet);
		textField.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
		add(textField, BorderLayout.WEST);
		
		add(new ButtonPanel(applet), BorderLayout.CENTER);
	}
}
