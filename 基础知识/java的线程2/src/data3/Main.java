package data3;

import com.sun.deploy.util.SyncAccess;

import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {


    /**
     * 线程二使用join方法，会使得线程一等待join规定等待的毫秒数。、
     * 如果join中没给时间，则一直等待。相当于直接使用了run方法，变成了单线程的
     * 如果线程二被锁定了，线程一还是会一直等。
     */



    static Thread thread1 = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println(11111+"sssss");
            try {
                thread2.start();
                thread2.join(16);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(11111+"eeeee");
        }
    });
    static Thread thread2 = new Thread(new Runnable() {
        @Override
        public void run() {

            
//            synchronized (thread2) {//锁定了线程二，则线程一还是会一直等
                System.out.println(2222 + "sssss");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(2222 + "eeeee");
//            }

        }
    });


    public static void main(String[] args) {
        thread1.start();
    }
}
