package BitDisplay;

public class Worker extends Thread {
	protected boolean running = true;

	private boolean updateClick = false;
	private boolean updateNum = false;
	
	Main applet; 
	
	public Worker(Main applet) {
		this.applet = applet;
	}

	@Override 
	public void run() {
		while (true) {
			if (running) {
				if (updateClick) {
					applet.binaryPanel.updateClick();
					updateClick = false;
				}
				
				else if (updateNum) {
					applet.binaryPanel.updateNum();
					updateNum = false;
				}
				
				else {
					try {
						synchronized(this) {
							wait();
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

	public synchronized void scheduleUpdateClick() {
		updateClick = true;
		updateNum = false;
		notifyAll();
	}
	
	public synchronized void scheduleUpdateNum() {
		updateClick = false;
		updateNum = true;
		notifyAll();
	}
}
