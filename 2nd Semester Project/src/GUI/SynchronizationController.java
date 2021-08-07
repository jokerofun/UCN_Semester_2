package GUI;

public class SynchronizationController {

	public synchronized void get() {
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void set() {
		notifyAll();
	}
}
