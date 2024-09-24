package ParallelProgramming;

import java.util.stream.*;
public class RunnableWithLambda {

    public static int[] numbers = IntStream.rangeClosed(1, 5000).toArray();  // Array of numbers from 1 to 5000
    public static int sum = 0;  // This will hold the parallel sum result
    public static int total = IntStream.rangeClosed(1, 5000).sum();  // The total sum using Stream for reference

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() ->{
            for(int i = 0;i < numbers.length/2;i++){
                add(numbers[i]);
            }
            System.out.println("Result of thread1 "+ sum);
        });

        Thread t2 = new Thread(() ->{
            for(int i = numbers.length/2;i < numbers.length;i++){
                add(numbers[i]);
            }
            System.out.println("Result of thread2 "+ sum);

        });

        System.out.println("Before joining main thread");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("After joining main thread");

    }

    public synchronized static void add(int value) {
        sum += value;
    }
    
}
