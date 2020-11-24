package box;

public interface Box<T> {

    boolean add(T element);
    T remvoe(int index);
    T get(int index);
    int size();
}
