public class SimpleThreads {

	public static void main(String[] args) {
		Thread t = new Thread(SimpleThreads::busyLoop);
		t.start();
		busyLoop();
	}

	static private void busyLoop() {
		long count;
		for (count = 0; count < 1000000000000L; count = count + 1) {
		}
	}
}