package FactorySupervisor;

public class ParallelProgramming {
    public static void main(String[] args) {
        worker1 w1 = new worker1();
        worker2 w2 = new worker2();

        Thread t1 = new Thread(w1);
        Thread t2 = new Thread(w2);
        t1.start();
        t2.start();
    }
}


class worker1 implements Runnable{

    @Override
    public void run() {
       executeTask();
    }

    public void executeTask(){
        try{
            for(int i = 0;i <= 10;i++){
                Thread.sleep(100);
                System.out.println("Worker1 is executing the task "+i);
            }
        }
        catch(InterruptedException e){
            System.out.println(e);
        }
    }

}

class worker2 implements Runnable{

    @Override
    public void run() {
       executeTask();
    }

    public void executeTask(){
        try{
            for(int i = 0;i <= 10;i++){
                Thread.sleep(100);
                System.out.println("Worker2 is executing the task "+ i);
            }
        }
        catch(InterruptedException e){
            System.out.println(e);
        }
    }

}