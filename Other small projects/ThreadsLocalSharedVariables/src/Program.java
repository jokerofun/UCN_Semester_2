public class Program {

	public static void main(String args[]) 
	{
		
		System.out.println("**** Local variables ****");
		Test myclass = new Test();
		new Thread(myclass::doSomething, "thread_1").start();
		myclass.doSomething();


/*      
		System.out.println("**** Lambda expression and captured variables ****");
        for (int i = 0; i < 10; i++) {
            int tmp = i;
            new Thread(() -> System.out.println(tmp)).start();
        }
*/
	}


}
