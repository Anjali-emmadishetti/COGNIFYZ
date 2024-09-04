public class BankAccount {

    private int balance = 1000;

    // Synchronized method to ensure thread safety
    public synchronized void withdraw(int amount) {
        if (balance >= amount) {
            System.out.println(Thread.currentThread().getName() + " withdrawing " + amount);
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " completed. Balance: " + balance);
        } else {
            System.out.println(Thread.currentThread().getName() + " insufficient funds.");
        }
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        Runnable task = () -> {
            for (int i = 0; i < 5; i++) account.withdraw(200);
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");
        Thread t3 = new Thread(task, "Thread-3");

        t1.start(); t2.start(); t3.start();
        try { t1.join(); t2.join(); t3.join(); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("Final Balance: " + account.balance);
    }
}
