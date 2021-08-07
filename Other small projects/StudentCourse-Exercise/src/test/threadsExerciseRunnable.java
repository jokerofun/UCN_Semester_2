package test;

public class threadsExerciseRunnable implements Runnable {
	public static void main(String[] args) {

		// create your threads here, eg. with new
		Thread thread1 = new Thread();
		Thread thread2 = new Thread();
		Thread thread3 = new Thread();
		Thread thread4 = new Thread();

		System.out.println("Hello world!\n");
		// run all your threads here!
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();

		System.out.println("threads stared, goodbye cruel world \n");
	}

	public void run() {
		System.out.println("My thread running!   ");
	}

}
