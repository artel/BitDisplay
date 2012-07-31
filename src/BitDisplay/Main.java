package BitDisplay;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JApplet;
import javax.swing.SwingUtilities;

public class Main extends JApplet {

	protected Worker worker = new Worker(this);
	protected BinaryPanel binaryPanel = new BinaryPanel(this);
	protected InputPanel inputPanel = new InputPanel(this);
	protected Container contentPane = getContentPane(); 
	
	@Override
	public void init() {
		contentPane.setLayout(new BorderLayout());
		contentPane.add(binaryPanel, BorderLayout.NORTH);
		contentPane.add(inputPanel, BorderLayout.CENTER);
		worker.start();
	}
	
	@Override
	public void start() {
		worker.running = true;
	}
	
	@Override
	public void stop() {
		worker.running = false;
	}
	
	protected void updateTextField(final String s) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				inputPanel.textField.setText(s);
			}
			
		});
	}
}
