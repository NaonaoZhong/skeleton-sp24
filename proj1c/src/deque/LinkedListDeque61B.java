package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {

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
    public Iterator<T> iterator(){
        return new ArraySetIterator();
    }

    private class ArraySetIterator implements Iterator<T> {
    private int wizPos;

    public ArraySetIterator() {
        wizPos = 0;
    }

    public boolean hasNext() {
        return wizPos < size;
    }

    public T next() {
        T returnItem = get(wizPos);
        wizPos += 1;
        return returnItem;
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

    @Override 
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o instanceof LinkedListDeque61B obj){
            if(this.size()!=obj.size()){
                return false;
            }
            Node p = sentinel.next;
            for (int i=0; i<this.size(); i++){
                if (p.item != obj.get(i)){
                    return false;
                }
                p = p.next;
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
    StringBuilder sb = new StringBuilder("[");
    for (int i = 0; i < size(); i++) {
        sb.append(get(i));
        if (i != size() - 1) {
            sb.append(", ");
        }
    }
    sb.append("]");
    return sb.toString();
    }
}
