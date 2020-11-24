package callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@SuppressWarnings("all")
public class Main {
//    static Callable<Integer> callable = new Callable<Integer>() {
//        @Override
//        public Integer call() throws Exception {
//            int j = 0;
//            for(int i = 0; i < 1000; i  ++){
//                j += i;
//            }
//            return j;
//        }
//    };
//    static FutureTask<Integer> futureTask = new FutureTask<Integer>(callable);
//    public static void main(String[] args) {
//        try {
//            new Thread(Main.futureTask).start();
//            System.out.println(Main.futureTask.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//    }
//    多个future执行的时候，get取值时候应该让所有线程都start后，一起get，否则get会阻塞下面的线程执行，则会变成单线程


    //callable原理
    public static void main(String[] args) {
        SimulationCallable simulationCallable = new SimulationCallable();
        SimulationFutureTask simulationFutureTask = new SimulationFutureTask(simulationCallable);
        new Thread(simulationFutureTask).start();
        System.out.println(simulationFutureTask.get());
    }






}
