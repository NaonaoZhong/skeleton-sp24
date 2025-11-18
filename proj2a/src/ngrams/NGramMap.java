package ngrams;

import java.util.Collection;
import java.util.HashMap;

import edu.princeton.cs.algs4.In;

import static ngrams.TimeSeries.MAX_YEAR;
import static ngrams.TimeSeries.MIN_YEAR;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    HashMap<String, TimeSeries> wordMap;
    TimeSeries countMap;


    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        // construct wordReader
        In wordReader = new In(wordsFilename);
        wordMap = new HashMap<String, TimeSeries>();
        while(wordReader.hasNextLine()){
            String line = wordReader.readLine();
            String[] lst = line.split("\t");
            String word = lst[0];
            int year = Integer.parseInt(lst[1]);
            Double times = Double.parseDouble(lst[2]);
            if(!wordMap.containsKey(word)){
                wordMap.put(word, new TimeSeries());
            }
            TimeSeries ts = (TimeSeries) wordMap.get(word);
            ts.put(year, times);
        }
        // construct countMap
        In countReader = new In(countsFilename);
        countMap = new TimeSeries();
        while(countReader.hasNextLine()){
            String line = countReader.readLine();
            String[] lst = line.split(",");
            int year = Integer.parseInt(lst[0]);
            Double times = Double.parseDouble(lst[1]);
            countMap.put(year, times);
        }
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        TimeSeries ts = new TimeSeries(wordMap.get(word), startYear, endYear);
        return ts;
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        return countHistory(word, MIN_YEAR, MAX_YEAR);
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        TimeSeries ts = (TimeSeries) countMap.clone();
        return ts;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        TimeSeries ts;
        if(!wordMap.containsKey(word)){
            return new TimeSeries();
        }
        else{
            TimeSeries wordTs = new TimeSeries(wordMap.get(word), startYear, endYear);
            TimeSeries countTs = new TimeSeries(countMap, startYear, endYear);
            ts = wordTs.dividedBy(countTs);
        }
        return ts;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        return weightHistory(word, MIN_YEAR, MAX_YEAR);
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        TimeSeries ts = new TimeSeries();
        for(String word : words){
            ts = ts.plus(weightHistory(word, startYear, endYear));
        }
        return ts;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        return summedWeightHistory(words, MIN_YEAR, MAX_YEAR);
    }
}
