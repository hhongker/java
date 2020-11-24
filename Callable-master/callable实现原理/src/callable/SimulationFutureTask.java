package callable;

@SuppressWarnings("all")
public class SimulationFutureTask<T> implements Runnable{

    private MyCallable myCallable = null;
    private T outCome = null;

    public SimulationFutureTask(MyCallable<T> myCallable){
        this.myCallable = myCallable;
    }

    @Override
    public void run() {
        outCome = (T)myCallable.myCall(new CallBack<Integer>() {
            @Override
            public Integer callBack() {
                int j = 0;
                for(int i = 0; i < 1000; i ++){
                    j += i;
                }
                return j;
            }
        });
    }

    public T get() {
        while (true){//写循环是为了不返回null
            if(outCome != null){
                return outCome;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
