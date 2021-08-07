
public class Test {

	int instvar = 0;

	public void doSomething()
	{
		int localvar = 0;
		for (int i = 0; i < 5; i++) System.out.println(Thread.currentThread().getName() + " - Forloop " + i);
		System.out.println();

		System.out.println(Thread.currentThread().getName() + " - Value localvar: " + localvar++);
		System.out.println(Thread.currentThread().getName() + " - Value instvar: " + instvar++);
	
	}
	
}
