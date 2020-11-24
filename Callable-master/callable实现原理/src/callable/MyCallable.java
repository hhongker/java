package callable;

public interface MyCallable<T> {
    T myCall(CallBack<T> callBack);
}
