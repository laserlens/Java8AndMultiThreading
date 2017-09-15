package model;

import java.util.stream.IntStream;

//class implements Producer/Consumer Pattern
//use wait and notify methods
public class ProducerConsumer {
    private static int[] buffer;
    private static int count;
    private static Object lock = new Object();

    static class Producer {

        void produce(){
            synchronized (lock){
                if (isFull(buffer)) {
                    try {
                        lock.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                }
                buffer[count++] = 1;
                lock.notify();
            }
        }
    }

    static class Consumer{

        void consume() {
            synchronized (lock){
                if(isEmpty(buffer)) {
                    try{
                        lock.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                buffer[--count] = 0;
                lock.notify();
            }
        }
    }

    static boolean isEmpty(int[] buffer){
            return count == 0;
    }

    static boolean isFull(int[] buffer){
        return count == buffer.length;
    }

    public static void main(String[] args) throws InterruptedException {
        buffer = new int[10];
        count = 0;

        Producer producer = new Producer();
        Consumer consumer = new Consumer();

        Runnable produceTask = ()-> {
            IntStream.range(0,50).forEach(i->producer.produce());
            System.out.println("Done producing");
        };

        Runnable consumeTask = ()-> {
            IntStream.range(0,45).forEach(i->consumer.consume());
            System.out.println("Done consumiong ");
        };

        Thread consumerThread = new Thread(consumeTask);
        Thread producerThread = new Thread(produceTask);

        consumerThread.start();
        producerThread.start();

        consumerThread.join();
        producerThread.join();

        System.out.println("Data in the buffer: " + count);


    }
}
