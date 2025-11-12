package hashmap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 *  A hash table-backed Map implementation.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author Naonao Zhong
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private double loadFactor;
    private int N;
    private int M;
    private LinkedList<Node> storage;
    /** Constructors */
    public MyHashMap() {
        this(16, 0.75);
     }

    public MyHashMap(int initialCapacity) {
        this(initialCapacity, 0.75);
     }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        N = 0;
        this.loadFactor = loadFactor;
        M = initialCapacity;
        buckets = new Collection[initialCapacity];
        storage = new LinkedList<>();
        for(int i = 0; i < initialCapacity; i++){
            buckets[i] = createBucket();
        }
     }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *  Note that that this is referring to the hash table bucket itself,
     *  not the hash map itself.
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        // Change the bucket's Datastructure easily
        return new LinkedList<>();
    }

    /** Iterable class of MyHashMap */
    private class MyHashMapIterator implements Iterator<K>{
        Iterator<Node>[] iterators = (Iterator<Node>[]) new Object[M];
        int current = 0;
        Iterator<Node> iter;

        MyHashMapIterator() {
            for(int i = 0; i < M; i++){
                iterators[i] = buckets[i].iterator();
            }
            iter = iterators[current];
        }

        @Override
        public boolean hasNext() {
            if(iter.hasNext()){
                return true;
            }
            while(!iter.hasNext()){
                if(current == M){
                    return false;
                }
                else{
                    current = current + 1;
                    iter = iterators[current];
                }
                if(iter.hasNext()){
                    return true;
                }
            }
            return false;
        }

        @Override
        public K next() {
            if(iter.hasNext()){
                return iter.next().key;
            }
            while(!iter.hasNext()){
                if(current == M){
                    return null;
                }
                else{
                    current = current + 1;
                    iter = iterators[current];
                }
                if(iter.hasNext()){
                    return iter.next().key;
                }
            }
            return null;
        }

    }


    @Override
    public Iterator<K> iterator() {
        return new MyHashMapIterator();
    }


    /** helper method resize */
    private void resize(){
        M = 2*M;
        N = 0;
        Iterator<Node> iter = storage.iterator();
        storage = new LinkedList<>();
        buckets = new Collection[M];
        storage = new LinkedList<>();
        for(int i = 0; i < M; i++){
            buckets[i] = createBucket();
        }
        while(iter.hasNext()){
            Node node = iter.next();
            put(node.key, node.value);
        }
    }
    @Override
    public void put(K key, V value) {
        if(N+1.0 > M * loadFactor){
            resize();
        }
        Node node = new Node(key, value);
        int group = Math.floorMod(key.hashCode(),M);
        Iterator<Node> iter = buckets[group].iterator();
        while(iter.hasNext()){
            Node p = iter.next();
            if(key.equals(p.key)){
                p.value = value;
                return;
            }   
        }
        buckets[group].add(node);
        storage.add(node);
        N = N + 1;
    }

    @Override
    public V get(K key) {
        int group = Math.floorMod(key.hashCode(),M);
        Iterator<Node> iter = buckets[group].iterator();
        while(iter.hasNext()){
            Node p = iter.next();
            if(key.equals(p.key)){
                return p.value;
            }     
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        int group = Math.floorMod(key.hashCode(),M);
        Iterator<Node> iter = buckets[group].iterator();
        while(iter.hasNext()){
            Node p = iter.next();
            if(key.equals(p.key)){
                return true;
            }     
        }
        return false;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public void clear() {
        N = 0;
        for(int i = 0; i < M; i++){
            buckets[i] = createBucket();
        }
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for(int i = 0; i < M; i++){
            Iterator<Node> iter = buckets[i].iterator();
            while(iter.hasNext()){
                set.add(iter.next().key);
            }
        }
        return set;
    }

    @Override
    public V remove(K key) {
        int group = Math.floorMod(key.hashCode(),M);
        Iterator<Node> iter = buckets[group].iterator();
        V value;
        while(iter.hasNext()){
            Node p = iter.next();
            if(key.equals(p.key)){
                value = p.value;
                buckets[group].remove(p);
                return value;
            }
        }
        N = N - 1;
        return null;
    }

    // Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

}
