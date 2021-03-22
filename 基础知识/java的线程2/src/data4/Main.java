package data4;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Main {

    /**
     * get方法会阻塞
     */

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask1 = new FutureTask<Long>(new Callable<Long>() {
            long res = 0;
            @Override
            public Long call() throws Exception {
                for (int i = 0; i < 1000; i ++) res += i;
                return res;
            }
        });
        FutureTask futureTask2 = new FutureTask<Long>(new Callable<Long>() {
            long res = 0;
            @Override
            public Long call() throws Exception {
                for (int i = 0; i < 1000; i ++) res += i;
                return res;
            }
        });

        Thread thread1 = new Thread(futureTask1);
        Thread thread2 = new Thread(futureTask2);


        thread1.start();
        thread2.start();

        System.out.println(futureTask1.get());
        System.out.println(futureTask2.get());
    }
}
