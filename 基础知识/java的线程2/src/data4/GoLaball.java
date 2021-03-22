package data4;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public interface GoLaball<T> {
    T run();
}

class GoTask<T> implements Runnable{
    T res = null;
    GoLaball<T> goLaball;
    public GoTask(GoLaball goLaball){
        this.goLaball = goLaball;
    }
    public T get(){
        while (true){
            if (res != null) return res;
        }
    }
    @Override
    public void run() {
        this.res = this.goLaball.run();
    }
}

class Main1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        GoTask futureTask1 = new GoTask<Long>(new GoLaball<Long>() {
            long res = 0;
            @Override
            public Long run() {
                for (int i = 0; i < 1000; i ++) res += i;
                return res;
            }
        });
        GoTask futureTask2 = new GoTask<Long>(new GoLaball<Long>() {
            long res = 0;
            @Override
            public Long run()   {
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


