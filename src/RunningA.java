import model.A;

public class RunningA {
    public static void main(String[] args) throws InterruptedException {
        A a = new A();

        Runnable r1 = (a::a);
        Runnable r2 = (a::b);

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        //thease two threads can cause a deadlock because they nead the key the other has
        t1.start();
        t2.start();

        t1.join();
        t2.join();

    }
}
