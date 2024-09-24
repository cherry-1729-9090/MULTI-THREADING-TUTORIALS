package FastFoodRestraunt;

public class Synchronized {
    public String getLastCustomer() {
        return lastCustomer;
    }

    public void setLastCustomer(String lastCustomer) {
        this.lastCustomer = lastCustomer;
    }

    public int getNumberOfBurgersSold() {
        return numberOfBurgersSold;
    }

    public void setNumberOfBurgersSold(int numberOfBurgersSold) {
        this.numberOfBurgersSold = numberOfBurgersSold;
    }

    private String lastCustomer;
    private int numberOfBurgersSold;

    public synchronized void buyBurger(String customer) throws InterruptedException {
        alongRunningProcess();
        this.lastCustomer = customer;
        this.numberOfBurgersSold++;
        System.out.println("Customer: " + customer + " bought a burger");
    }

    public void alongRunningProcess() throws InterruptedException {
        Thread.sleep(1000);
    }

    public static void main(String[] args) throws InterruptedException {

        long startTime = System.currentTimeMillis();

        Synchronized fr = new Synchronized();

        Thread t1 = new Thread(() -> {
            try {
                fr.buyBurger("Alice");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                fr.buyBurger("John");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t3 = new Thread(() -> {
            try {
                fr.buyBurger("Rahul");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t4 = new Thread(() -> {
            try {
                fr.buyBurger("Vikram");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

        System.out.println("Total number of burgers sold are : " + fr.getNumberOfBurgersSold());
        System.out.println("Last customer is : " + fr.getLastCustomer());
        System.out.println("Time taken : " + (System.currentTimeMillis() - startTime) + "ms");

    }
}
