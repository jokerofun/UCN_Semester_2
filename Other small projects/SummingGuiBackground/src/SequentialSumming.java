import java.util.Date;

public class SequentialSumming {
		
	public String sequentialSumming(long slowFactor, int numInts) {
		long start;
		long stop;
        double elapsedTimeInSecs;
		int[] a = new int[numInts];
		int[] b = new int[numInts];
		int[] c = new int[numInts];
		fillArray(a,numInts);
        fillArray(b,numInts);
        fillArray(c,numInts);
        printArray(a);
        printArray(b);
        printArray(c);
		
        start = (new Date()).getTime();
        
        for (int i = 0; i < numInts; i++) {
            c[i]= a[i] + b[i];
            slowDown(slowFactor);
        }
        
        stop = (new Date()).getTime();
        elapsedTimeInSecs = (stop - start) / 1000.0;
        return ("Summing done in "+ elapsedTimeInSecs + " secs.");
    }
	
    private void slowDown(long slowFactor){
        int j = 0;
        while (j < slowFactor)//Slowing down
            j++;
    }	
	
	private void fillArray(int[] arrayOfIntegers, int numIntegers){
		for(int i= 0; i<numIntegers; i++)
			arrayOfIntegers[i]= i;
	}
	
	public void printArray(int[] a) {
		System.out.print("[");
        for(int i= 0; i<3; i++)
        	System.out.print(a[i] + ", ");
        System.out.print("..., ");
        for(int i= a.length-3; i<a.length; i++)
        	System.out.print(a[i] + ", ");
        System.out.println("]");
	}
	
}

