package FactorySupervisor;

public class SupervisorExample {
    public static void main(String[] args) throws InterruptedException {
        worker1 w1 = new worker1();
        worker2 w2 = new worker2();

        w1.executeTask();
        w2.executeTask();
    }
}



class worker1 {
    public void executeTask() throws InterruptedException {
        for (int i = 0; i <= 10; i++) {
            Thread.sleep(100);
            System.out.println("worker 1 is executing task "+i);

        }
    }
}

class worker2 {
    public void executeTask() throws InterruptedException {
        for (int i = 0; i <= 10; i++) {
            Thread.sleep(100);
            System.out.println("worker 2 is executing task "+i);
        }
    }
}




// Thrown when a thread is waiting, sleeping, or otherwise occupied, and the thread is interrupted, either before or during the activity. 
// Occasionally a method may wish to test whether the current thread has been interrupted, and if so, to immediately throw this exception. 
// The following code can be used to achieve this effect: