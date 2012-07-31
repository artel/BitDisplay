package BitDisplay;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class InputTxtField extends JTextField implements CaretListener {

	Main applet;
	public InputTxtField(int i, Main applet) {
		super(i);
		this.applet = applet;
		this.addCaretListener(this);
	}
	
	@Override
	public void caretUpdate(CaretEvent arg0) {
		String text = this.getText();
		int i = 0;
		
		try {
			i = Integer.parseInt(text);
		} catch (NumberFormatException e) {
			
		}
		
		applet.binaryPanel.setNum(i);
		applet.worker.scheduleUpdateNum();
	}
	
	

}
