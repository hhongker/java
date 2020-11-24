package box.linkbox;

import box.Box;
import box.BoxIndexOutOfBoundsException;

public class LinkBox<T> implements Box {


    private Node first;
    private Node last;
    private int size;

    private  void linkLast(T element){
        Node l = last;
        Node newNode = new Node(l,element,null);
        last = newNode;
        if(l == null){//说明是第一个节点进来
            first = newNode;
        }else {
            l.setNext(newNode);
        }
        size ++;
    }

    private Node getNode(int index){
        checkIndex(index);
        Node targetNode;
        if(index < (size>>1)){//从前往后找寻比较快
            targetNode = first;
            for(int i=0;i<index;i++){
                targetNode = targetNode.getNext();
            }
        }else{//从后往前找
            targetNode = last;
            for(int i=size-1;i>index;i--){
                targetNode = targetNode.getPrev();
            }
        }
        return targetNode;
    }

    private Object unlink(Node targetNode){
        Object oldValue = targetNode.getItem();
        Node prev = targetNode.getPrev();
        Node next = targetNode.getNext();

        if(prev==null){//当前node就是第一个
            first = next;
        }else{
            prev.setNext(next);
            targetNode.setPrev(null);
        }
        if(next==null){//当前node就是最后一个
            last = prev;
        }else{
            next.setPrev(prev);
            targetNode.setNext(null);
        }
        size--;
        return oldValue;
    }

    private void checkIndex(int index){
        if(index < 0 || index >= size){
            throw new BoxIndexOutOfBoundsException(index+"必须在0到"+(size-1)+"之中");
        }
    }

    @Override
    public boolean add(Object element) {
        this.linkLast((T)element);
        return true;
    }

    @Override
    public T remvoe(int index) {
        this.checkIndex(index);
        Node targetNode = this.getNode(index);
        Object oldValue = this.unlink(targetNode);
        return (T)oldValue;
    }

    @Override
    public T get(int index) {
        this.checkIndex(index);
        Node targetNode = this.getNode(index);
        return (T)targetNode.getItem();
    }

    @Override
    public int size() {
        return size;
    }
}
