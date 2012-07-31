package BitDisplay;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.nio.ByteBuffer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class BinaryPanel extends JPanel {
	
	public static final int BITS = 32;
	
	private int num = 0;
	
	private JPanel[] bytePanel = new JPanel[BITS / 8];			//8 bits grouped into a byte
	private JPanel[] byteSet = new JPanel[BITS / 8];	//Container panel for the above panel
	private BitPanel[] bitPanels = new BitPanel[BITS];		//All the individual bits
	private JTextField[] byteFields = new JTextField[BITS / 8];	//converts a set of 8 bits to a byte
	protected Main applet;
	
	public BinaryPanel(Main main) {
		this.applet = main;
		//Create the container panel for each byte set
		for (int i = 0; i < byteSet.length; ++i) {
			byteSet[i] = new JPanel(new BorderLayout());
			byteSet[i].setBorder(BorderFactory.createLineBorder(Color.gray));
			this.add(byteSet[i]);
		}
		
		//Create each panel object, and set its border
		for (int i = 0; i < bytePanel.length; ++i) {
			bytePanel[i] = new JPanel();
			byteSet[i].add(bytePanel[i], BorderLayout.NORTH);
		}
		
		//Create each textfield object, add it to the correct panel.
		for (int i = 0; i < bitPanels.length; ++i) {
			BitPanel bp = new BitPanel(this);
			bitPanels[i] = bp;
			bytePanel[i / 8].add(bp, BorderLayout.NORTH);
		}
		
		
		for (int i = 0; i < byteFields.length; ++i) {
			JTextField jtf;
			jtf = new JTextField(3);
			jtf.setEditable(false);
			jtf.setHorizontalAlignment(JTextField.CENTER);
			byteFields[i] = jtf;
			byteSet[i].add(jtf, BorderLayout.CENTER);
		}

		main.worker.scheduleUpdateNum();
	}

	private void updateBitFields() {
		String bits = Integer.toBinaryString(num);
		int moreBits = BITS - bits.length();
		String zeroes = new String(new char[moreBits]).replace("\0", "0");
		bits = zeroes + bits;
		char[] chars = bits.toCharArray();
		updateTxtField(chars);
	}
	
	private void updateTxtField(char[] chars) {
		if (chars.length == bitPanels.length) {
			for (int i = 0; i < chars.length; ++i) {
				char c = chars[i];
				if (c == '0') {
					bitPanels[i].setToZero();
				}
				else if (c == '1') {
					bitPanels[i].setToOne();
				}
			}
		}
		else {
			throw new UnsupportedOperationException("Bit string does not match the number of bits");
		}
	}

	private void updateByteFields() {
		final ByteBuffer bf = ByteBuffer.allocate(BITS / 8);
		bf.putInt(num);
		bf.position(0);
		
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < byteFields.length; ++i) {
					byteFields[i].setText(bf.get()+"");
				}
			}
			
		});
		
	}

	public void updateClick() {
		System.out.println(Thread.currentThread().getName() + " updated click");
		String bitsReversed = "";
		
		synchronized(this) {
		
			for (int i = 0; i < bitPanels.length; ++i) {
				bitsReversed = bitPanels[i].getText() + bitsReversed;
			}
		
		}
		char[] chars = bitsReversed.toCharArray();
		
		boolean positive = true;
		if (chars[chars.length-1] == '1') {
			positive = false;
		}
		
		int value = 0;
		for (int i = 0; i < chars.length; ++i) {
			if (positive) {
				if (chars[i] == '1') {
					value += Math.pow(2, i);
				}
			}
			else {
				if (chars[i] == '0') {
					value -= Math.pow(2, i);
				}
			}
		}
		if (!positive) {
			value -= 1;
		}
		num = value;
		updateNum();
		
		applet.updateTextField(num + "");
	}
	
	public synchronized void setNum(int i) {
		this.num = i;
	}
	
	public synchronized void updateNum() {
		System.out.println(Thread.currentThread().getName() + " updated nums");
		updateBitFields();
		updateByteFields();
	}

	public synchronized void decNum() {
		num--;
	}
	
	public synchronized void incNum() {
		num++;
	}

	public int getNum() {
		return num;
	}

}
