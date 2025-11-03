package deque;

import java.util.Comparator;

public class MaxArrayDeque61B<T> extends ArrayDeque61B<T> {

    Comparator<T> comparator;

    public MaxArrayDeque61B(Comparator<T> c){
        super();
        this.comparator = c;
    }

    public T max(){
        if (this.getsize() == 0){
            return null;
        }
        int maxidx = 0;
        for (int i = 0; i < this.getsize(); i++){
            if (comparator.compare(this.get(i),this.get(maxidx))>0){
                maxidx = i;
            }
        }
        return this.get(maxidx);
    }
    
    public T max(Comparator<T> c){
        if (this.getsize() == 0){
            return null;
        }
        int maxidx = 0;
        for (int i = 0; i < this.getsize(); i++){
            if (c.compare(this.get(i),this.get(maxidx))>0){
                maxidx = i;
            }
        }
        return this.get(maxidx);
    }
}
