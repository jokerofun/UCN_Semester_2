public class Program {

	private static final int INITIAL_VALUE = 10;
	public static final Object lockObject = new Object();

	public static void main(String[] args) throws InterruptedException {

		SharedObject shared = new SharedObject(INITIAL_VALUE);
		System.out.println("Initial count: " + shared.getCount());

		WorkerThread thread1 = new WorkerThread("worker1", shared);
		WorkerThread thread2 = new WorkerThread("worker2", shared);
		System.out.println("Starting threads...");
		thread1.start();
		thread2.start();
		thread1.join();
		thread2.join();

		System.out.println("Counter is: " + shared.getCount() + ", it should be: " + (2 + INITIAL_VALUE));
	}

}
