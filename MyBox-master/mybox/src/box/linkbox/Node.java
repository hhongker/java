package box.linkbox;

public class Node {

    private Node prev;
    private Object item;
    private Node next;

    public Node() {
    }

    public Node(Node prev, Object item, Node next) {
        this.prev = prev;
        this.item = item;
        this.next = next;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public Object getItem() {
        return item;
    }

    public void setItem(Object item) {
        this.item = item;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
