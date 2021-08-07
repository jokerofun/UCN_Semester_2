//FEN 2015.04.26
//GIBE 2017
//GIBE 2020 small changes, sum instead of reduce

import java.util.*;

public class Main {
    static long slowFactor = 100;
	static int n =  3000000;
	static List<Integer> l = new ArrayList<>();
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

        fillList(l);
        printList(l);
        
        long start;
        double elapsedTimeInSecs;
        int count;
      
        System.out.println("Start sequentially (simple loop)");
        start = System.currentTimeMillis();
        count= sequentialCounting();
        elapsedTimeInSecs = (System.currentTimeMillis() - start) / 1000.0;
        System.out.println("Done sequentially in "+ elapsedTimeInSecs + " secs.");
        System.out.println("No of primes: " + count);
        System.out.println();
        
        System.out.println("Start sequential using map-reduce");
        start = System.currentTimeMillis();
        count = sequentialCountingMapReduce();
        elapsedTimeInSecs = (System.currentTimeMillis() - start) / 1000.0;
        System.out.println("Done sequential using map-reduce in "+ elapsedTimeInSecs + " secs.");
        System.out.println("No of primes: " + count);
        System.out.println();
        
        System.out.println("Start parallel using map-reduce");

        start = System.currentTimeMillis();
        count = parallelCounting();
        elapsedTimeInSecs = (System.currentTimeMillis() - start) / 1000.0;
        System.out.println("Done parallel using map-reduce in "+ elapsedTimeInSecs + " secs.");
        System.out.println("No of primes: " + count);
        scanner.close();
	}

	static int parallelCounting() {
		return l.parallelStream()
        	.mapToInt(i -> {
        		if(isPrime(i))
        			return 1;
        		else
        			return 0;
        	})
        	.sum();
	}

	static int sequentialCountingMapReduce() {
		return l.stream()
        	.mapToInt(i -> {
        		if(isPrime(i))
        			return 1;
        		else
        			return 0;
        	})
        	.sum();
	}
	
    static int sequentialCounting(){
        int count= 0;
    	for (int i = 0; i < n; i++)
        {
            if(isPrime(l.get(i)))
            	count++;
        }
    	return count;
    }
    
    static boolean isPrime(int n)
    {
        if (n == 2)
        {
            return true;//2 is the only even prime
        }
        else
        {
            if (n % 2 == 0) //even: not a prime
            {
                return false;
            }
            else
            {
                int start = 3; //start in 3, looking only at odds: 3, 5, 7,...
                int end = (int)Math.ceil(Math.sqrt(n));//No need to look after the square root
                int i = start;
                while (i <= end)
                {
                    slowDown(slowFactor);
                    if (n % i == 0)
                        return false;  // it's not prime, return now:
                    i = i + 2; //skip evens
                }
                return true;  // it's prime!
            }
        }
    }
    
    public static void printList(List<Integer> l) {
		System.out.print("[");
        for(int i= 0; i<3; i++)
        	System.out.print(l.get(i) + ", ");
        System.out.print("\b..., ");
        for(int i= l.size()-3; i<l.size(); i++)
        	System.out.print(l.get(i) + ", ");
        System.out.println("\b\b]");
	}
	
    static void slowDown(long slowFactor){
        int j = 0;
        while (j < slowFactor)//Slowing down
            j++;
    }
    
	static void fillList(List<Integer> l){
		for(int i= 0; i<n; i++)
			l.add(i);
	}

}

