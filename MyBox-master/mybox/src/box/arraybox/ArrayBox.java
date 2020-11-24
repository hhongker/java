package box.arraybox;

import box.Box;
import box.BoxIndexOutOfBoundsException;

public class ArrayBox<T> implements Box {

    private static int DEFAULT_LENGTH = 16;
    private Object[] arrBox;
    private int size = 0;//有效长度

    public ArrayBox(){
        arrBox = new Object[DEFAULT_LENGTH];
    }
    public ArrayBox(int len){
        arrBox = new Object[len];
    }






    private Object[] copy(Object[] oldObj, Object[] newObj){
        for (int i = 0; i < oldObj.length; i ++){
            newObj[i] = oldObj[i];
        }
        return newObj;
    }

    private Object[] grow(){
        int newLen = 2;
        if(size != 1){
            newLen = size + (size >> 1);
        }
        Object[] object = new Object[newLen];
        return copy(arrBox,object);
    }

    private boolean helpAdd(Object elemnet){
        if(size >= arrBox.length){
            arrBox = grow();
        }
        arrBox[size ++] = elemnet;
        return true;
    }

    private void checkIndex(int index){
        if(index < 0 || index >= size){
            throw new BoxIndexOutOfBoundsException(index+"必须在0到"+(size-1)+"之中");
        }
    }

    private void cover(int index){
        for(int i = index; i < size; i ++){
            arrBox[i] = arrBox[i+1];
        }
        arrBox[-- size] = null;
    }



    @Override
    public boolean add(Object element) {
        return helpAdd(element);
    }

    @Override
    public T remvoe(int index) {
        T get = get(index);
        cover(index);
        return get;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T)arrBox[index];
    }

    @Override
    public int size() {
        return size;
    }
}
