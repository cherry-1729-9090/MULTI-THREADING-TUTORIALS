package ParallelProgramming;
import java.util.stream.*;

public class ParallelSum  {

    public static int[] numbers = IntStream.rangeClosed(1, 5000).toArray();  // Array of numbers from 1 to 5000
    public static int sum = 0;  // This will hold the parallel sum result
    public static int total = IntStream.rangeClosed(1, 5000).sum();  // The total sum using Stream for reference

    public static void main(String[] args) throws InterruptedException {
        worker1 w1 = new worker1(numbers);  // Create worker1 for first half
        worker2 w2 = new worker2(numbers);  // Create worker2 for second half

        Thread t1 = new Thread(w1);  // Thread for worker1
        Thread t2 = new Thread(w2);  // Thread for worker2

        t1.start();  // Start thread t1
        t2.start();  // Start thread t2

        t1.join();  // Wait for thread t1 to finish
        t2.join();  // Wait for thread t2 to finish

        System.out.println("Sum of 5000 integers on parallel calculation will be " + sum);
        System.out.println("Sum of 5000 integers on initial calculation is " + total);
    }
    
    // Synchronized to prevent race condition when multiple threads access the shared resource 'sum'
    public synchronized static void add(int toAdd) {
        sum += toAdd;
    }

}


class worker1 implements Runnable {
    int[] array;
    int sum = 0;

    public worker1(int[] array) {
        this.array = array;
    }

    @Override
    public void run() {
        // Sum the first half of the array
        for (int i = 0; i < array.length / 2; i++) {
            sum += array[i];
        }
        ParallelSum.add(sum);  // Add the result to the shared sum in ParallelSum class
    }
}


class worker2 implements Runnable {
    int[] array;
    int sum = 0;

    public worker2(int[] array) {
        this.array = array;
    }

    @Override
    public void run() {
        // Sum the second half of the array
        for (int i = array.length / 2; i < array.length; i++) {
            sum += array[i];
        }
        ParallelSum.add(sum);  // Add the result to the shared sum in ParallelSum class
    }
}
