import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K,V> {

    int size;

    private class Node{
        K key;
        V value;
        Node left;
        Node right;

        public Node(K k, V v){
            key = k;
            value = v;
        }
    }
    /**helper function to recursively implement put */
    private Node insert(K key, V value, Node n){
        if(n == null){
            size = size + 1;
            return new Node(key, value);
        }
        if(key.equals(n.key)){
            n.value = value;
        }
        else if(key.compareTo(n.key) < 0){
            n.left = insert(key, value, n.left);
        }
        else{
            n.right = insert(key, value, n.right);
        }
        return n;
    }
    /**helper function to recursively implement get*/
    private Node find(K key, Node n){
        if(n == null){
            return null;
        }
        if(key.equals(n.key)){
            return n;
        }
        else if (key.compareTo(n.key) < 0){
            return find(key, n.left);
        }
        else{
            return find(key, n.right);
        }
    }
    /**helper function to recursively implement KeySet */
    private Set<K> findkey(Node n){

        if(n == null){
            return null;
        }
        else{
            Set<K> set = new TreeSet<K>();
            Set<K> setl = findkey(n.left);
            Set<K> setr = findkey(n.right);
            set.add(n.key);
            if(setl != null){
                set.addAll(setl);
            }
            if(setr != null){
            set.addAll(setr);
            }
            return set;
        }
    }

    /**Helper class, a container Node[] */
    private class NodePair{
        Node parent;
        Node item;

        NodePair(Node p, Node i){
            parent = p;
            item = i;
        }

        Node get_parent(){
            return parent;
        }
        Node get_item(){
            return item;
        }
    }

    /**helper function to find the rightest node form Node n */
    private NodePair rightest(Node n){

        Node p = n.right;
        Node upper = n;
        while(p != null){
            upper = p;
            p = p.right;
        };
        NodePair pair = new NodePair(upper,p);
        return pair;
    }
    /**helper function to recursively implement delete*/
    private Node del_node(K key, Node n){

        if(n == null){
            return null;
        }
        if(key.equals(n.key)){

            if(n.left == null){
                size = size -1;
                return n.right;
            }
            else if(n.left.right == null){
                n.left.right = n.right;
                size = size -1;
                return n.left;
            }
            else{
            Node upper = rightest(n).get_parent();
            Node pre = rightest(n).get_item();
            pre.right = n.right;
            upper.right = pre.left;
            pre.left = n.left; 
            size = size -1; // don't forget it
            return pre;
            }
        }
        else if(key.compareTo(n.key) < 0){
            n.left = del_node(key, n.left);
            return n;
        }
        else{
            n.right = del_node(key, n.right);
            return n;
        }

    }

    /** Above are helper methods with private class 'Node'*/

    /** Set a root for a BSTMAP */
    Node root;

    public BSTMap(){  
        /** Create a new BSTMAP<K,V> */ 
        size = 0;
    }

    @Override
    public void put(K key, V value) {
        root = insert(key, value, root);
    }

    @Override
    public V get(K key) {
        if(find(key, root) == null){
            return null;
        }
        return find(key, root).value;
    }

    @Override
    public boolean containsKey(K key) {
        return find(key, root) != null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        return findkey(root);
    }

    @Override
    public V remove(K key) {
        Node target = find(key, root);
        if(target == null){
            return null;
        }else{
            V del_value = target.value; 
            root = del_node(key, root);
            return del_value;
        }
    }

    @Override
    public Iterator<K> iterator() {
        return this.keySet().iterator();
    }

    public void printInOrder(){
        for(K key : this.keySet()){
            System.out.println(key);
        }
    }
}
