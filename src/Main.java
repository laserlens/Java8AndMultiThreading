import model.LongWrapper;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws InterruptedException{

        //region example1
//        Runnable runnable = ()->{
//            System.out.println("I am running in: "
//                    + Thread.currentThread().getName());
//        };
//
//        Thread t = new Thread(runnable);
//        t.setName("<YourThreadNameHere>");
//        t.start();
        //t.run();//run doesn't start a new in thead but runs in the main method
        //endrgion

        LongWrapper longWrapper = new LongWrapper(0L);
        Runnable r = ()->IntStream.rangeClosed(1,1000)
                .forEach(i -> longWrapper.incrementValue());

        //region example2
//        Thread t1 = new Thread(r);
//        t1.start();
//        t1.join();
//        System.out.println("Value =" + longWrapper.getValue());
        //endregion

        //rgion example3
        //Instead of one Thread run 1000 threads
        Thread[] threads = new Thread[1_000];
        IntStream.range(0,1_000)
                .forEach((i)->{threads[i] = new Thread(r);
                                threads[i].start();});
        IntStream.range(0,threads.length)
                .forEach((i)->{try {
                                threads[i].join();
                                }catch (InterruptedException ex){
                                System.out.println("error was: " + ex);
                                }});

        System.out.println("Value= " + longWrapper.getValue());//result expected 1_000_000, but getting differet values lessthan
        //endregion

    }


}
