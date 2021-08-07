
public class WorkerThread extends Thread {

	private SharedObject myCopy;

	public WorkerThread(String name, SharedObject copy) {
		super(name);
		myCopy = copy;
	}

	@Override
	public void run() {
		System.out.println(this.getName() + " is updating the counter");

		synchronized (Program.lockObject) {
			int oldValue = myCopy.getCount();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
			int newValue = oldValue + 1;
			myCopy.setCount(newValue);
		}

	}

}
