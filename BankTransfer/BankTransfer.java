package BankTransfer;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BankTransfer {
    private double balance;
    private Integer id;
    private String name;
    final Lock lock = new ReentrantLock();

    public BankTransfer(Integer id, String name, double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public boolean withdraw(double amount) {
        if (this.lock.tryLock()) {
            try {
                Thread.sleep(100);
                balance -= amount;
                return true;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
        return false;
    }

    public boolean deposit(double amount) {
        if (lock.tryLock()) {
            try {
                Thread.sleep(100);
                balance += amount;
                return true;
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
        return false;
    }

    public boolean transfer(BankTransfer to, double amount) {
        if (withdraw(amount)) {
            System.out.println("Withdrawing from " + this.name + " to " + to.name);
            if (to.deposit(amount)) {
                System.out.println("Depositing amount " + amount + " to " + to.name);
                return true;
            } else {
                System.out.println("Failed to deposit amount " + amount + " to " + to.name);
                while (!deposit(amount)) {
                    System.out.println("Retrying deposit of amount " + amount + " to " + this.name);
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "BankTransfer{id=" + id + ", name='" + name + "', balance=" + balance + "}";
    }

    public static void main(String[] args) throws InterruptedException {
        BankTransfer studentAccount = new BankTransfer(1, "Alice", 50000);
        BankTransfer collegeAccount = new BankTransfer(2, "University", 100000);
        System.out.println("Starting balance of accounts are university: " + collegeAccount.balance + " and Alice: "
                + studentAccount.balance);

        ExecutorService executor = Executors.newFixedThreadPool(10);
        Runnable task = () -> {
            System.out.println("Thread name is: " + Thread.currentThread().getName());

            try {
                while (!studentAccount.transfer(collegeAccount, 1000)) {
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            System.out.println(Thread.currentThread().getName() + " says transfer is successful");
        };

        for (int i = 0; i < 10; i++) {
            executor.submit(task);
        }

        executor.shutdown();

        while (!executor.awaitTermination(24L, TimeUnit.HOURS)) {
            System.out.println("Waiting for all threads to finish");
        }

        System.out.println("Ending balance of accounts are university: " + collegeAccount.balance + " and Alice: "
                + studentAccount.balance);
    }
}