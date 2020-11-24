package callable;


public class SimulationCallable<T> implements MyCallable<T>{

    private T value;

    public SimulationCallable(){}

    public SimulationCallable(T value) {
        this.value = value;
    }



    public T myCall(CallBack<T> callback) {
        return callback.callBack();
    }
}
