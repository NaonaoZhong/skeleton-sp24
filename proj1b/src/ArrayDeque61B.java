import java.util.ArrayList;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {

    private T[] array;
    private int arraySize;
    private int nextFirst;
    private int nextLast;
    private int size;
    public final int Factor = 5;

    public ArrayDeque61B() {
        arraySize = 8;
        nextFirst = 0;
        nextLast = 1;
        size = 0;
        array = (T[]) new Object[8];
    }

    private void resize(){
        T[] newarray = (T[]) new Object[arraySize * Factor];
        for (int i = 0; i < arraySize; i++){
            newarray[i+1] = get(i);
        }
        nextFirst = 0;
        nextLast = arraySize + 1;
        arraySize = arraySize * Factor;
        array = newarray;
    }

    @Override
    public void addFirst(T x) {
        if (size >= arraySize){
            resize();
        }

        array[nextFirst] = x;
        size = size + 1;
        nextFirst = Math.floorMod(nextFirst - 1, arraySize);
    }

    @Override
    public void addLast(T x) {
        if (size >= arraySize){
            resize();
        }

        array[nextLast] = x;
        size = size + 1;
        nextLast = Math.floorMod(nextLast + 1, arraySize);
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0){
            return null;
        }
        int array_idx = Math.floorMod((nextFirst + index + 1),arraySize);
        return array[array_idx];
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<T>();
        for (int i=0; i<size; i++){
            returnList.add(get(i));
        }
        return returnList;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size > 0){
        int p = Math.floorMod(nextFirst + 1, arraySize);
        T first = array[p];
        array[p] = null;
        size = size - 1;
        nextFirst = p;
        return first;
        }
        else{
            return null;
        }
    }

    @Override
    public T removeLast() {
        if (size > 0){
        int p = Math.floorMod(nextLast - 1, arraySize);
        T last = array[p];
        array[p] = null;
        size = size - 1;
        nextLast = p;
        return last;
        }
        else{
            return null;
        }
    }

    @Override
    public boolean isEmpty() {
        if (size == 0){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }  
}
