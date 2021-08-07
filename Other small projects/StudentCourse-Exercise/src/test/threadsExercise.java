package test;

public class threadsExercise {
	public static void main(String[] args) {
		// create your threads here, eg. with new
		MyThread thread1 = new MyThread();
		MyThread thread2 = new MyThread();
		MyThread thread3 = new MyThread();
		MyThread thread4 = new MyThread();

		System.out.println("Hello world!\n");
		// run all your threads here!
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();

		System.out.println("threads stared, goodbye cruel world \n");
	}

}
