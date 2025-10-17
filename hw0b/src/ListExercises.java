import java.util.ArrayList;
import java.util.List;

public class ListExercises {

    /** Returns the total sum in a list of integers */
	public static int sum(List<Integer> L) {
        if (L.size() == 0){
            return 0;
        }
        int total = 0;

        for(int i:L){
            total = total + i;
        }
        return total;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        List<Integer> evenlst = new ArrayList<>();
        for (int ele:L){
            if (ele%2==0){
                evenlst.add(ele);
            }
        }
        return evenlst;
    }
    public static boolean has(List<Integer> L,int a ){
        for (int ele: L){
            if (ele==a){
                return true;
            }
        }
        return false;
    }
    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
    /**write a very slow method before learning great datastructures */
        List<Integer> common = new ArrayList<>();
        for (int ele1 : L1){
            if (has(common,ele1)){
                continue;
            }
            if (has(L2,ele1)){
                common.add(ele1);
            }
        }
        return common;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        int total = 0;
        for (String word : words){
            char[] word_Array = word.toCharArray();
            for (char i : word_Array){
                if(i == c){
                    total = total + 1;
                }
            }
        }
        return total;
    }
}
