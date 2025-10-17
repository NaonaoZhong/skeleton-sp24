import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapExercises {
    /** Returns a map from every lower case letter to the number corresponding to that letter, where 'a' is
     * 1, 'b' is 2, 'c' is 3, ..., 'z' is 26.
     */
    public static Map<Character, Integer> letterToNum() {
        char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        TreeMap<Character,Integer> map = new TreeMap<>();
        for (int i=1;i<=26;i++){
            map.put(alphabet[i-1],i);
        } 
        return map;
    }

    /** Returns a map from the integers in the list to their squares. For example, if the input list
     *  is [1, 3, 6, 7], the returned map goes from 1 to 1, 3 to 9, 6 to 36, and 7 to 49.
     */
    public static Map<Integer, Integer> squares(List<Integer> nums) {
        TreeMap<Integer,Integer> squaremap = new TreeMap<>();
        for (int num : nums){
            squaremap.put(num,num*num);
        }
        return squaremap;
    }

    /** Returns a map of the counts of all words that appear in a list of words. */
    public static Map<String, Integer> countWords(List<String> words) {
        TreeMap<String,Integer> wordmap = new TreeMap<>();
        for (String word : words){
            if (wordmap.containsKey(word)){
                wordmap.put(word,wordmap.get(word)+1);
            }
            else{
                wordmap.put(word,1);
            }
        }
        return wordmap;
    }
}
