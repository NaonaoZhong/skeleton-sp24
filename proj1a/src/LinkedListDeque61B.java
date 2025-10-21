import java.util.List;
import java.util.ArrayList; // import the ArrayList class

public class LinkedListDeque61B<T> implements Deque61B<T>{

    private Node sentinel;
    public int size;

    public LinkedListDeque61B(){
        sentinel = new Node(null,null,null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    private class Node{
        T item;
        Node prev;
        Node next;
        Node (T i, Node l, Node n){
            item = i;
            prev = l;
            next = n;
        }
    }
    @Override
    public void addFirst(T x) {
        if (x == null){
            throw new IllegalArgumentException("Can't add null to the LinkedListDeque61B");
        }
        Node first = new Node(x,sentinel,sentinel.next);
        sentinel.next.prev = first; 
        sentinel.next = first;
        size++;
    }

    @Override
    public void addLast(T x) {
        if (x == null){
            throw new IllegalArgumentException("Can't add null to the LinkedListDeque61B");
        }
        Node prev = new Node(x, sentinel.prev, sentinel);
        sentinel.prev.next = prev;
        sentinel.prev = prev;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node p = sentinel;
        while(p.next.item != null){
            returnList.add(p.next.item);
            p = p.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if (sentinel.next.item != null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size > 0){
        T firstItem = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return firstItem;
        }
        else{
            return null;
        }
    }

    @Override
    public T removeLast() {
        if (size > 0){
        T lastItem = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return lastItem;
        }
        else{
            return null;
        }

    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size - 1){
            throw new IndexOutOfBoundsException();
        }
        Node p = sentinel;
        for (int i = 0; i < index+1; i++){
            p = p.next;
        }
        return p.item;
    }

    @Override
    public T getRecursive(int index) {

        if (index < 0 || index > size - 1){
            throw new IndexOutOfBoundsException();
        }
        return getRecursiveHelper(index).item;
    }

    private Node getRecursiveHelper(int idx){
        if (idx == 0){
            return sentinel.next;
        }
        return getRecursiveHelper(idx-1).next;
    }
}
