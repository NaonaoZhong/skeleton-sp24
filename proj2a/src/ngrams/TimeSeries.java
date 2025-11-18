package ngrams;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * An object for mapping a year number (e.g. 1996) to numerical data. Provides
 * utility methods useful for data analysis.
 *
 * @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {

    /** If it helps speed up your code, you can assume year arguments to your NGramMap
     * are between 1400 and 2100. We've stored these values as the constants
     * MIN_YEAR and MAX_YEAR here. */
    public static final int MIN_YEAR = 1400;
    public static final int MAX_YEAR = 2100;

    /**
     * Constructs a new empty TimeSeries.
     */
    public TimeSeries() {
        super();
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     * inclusive of both end points.
     */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();
        // modify keySet() can modify the whole ts at the same time
        for(Integer key : ts.keySet()){
            if(key >= startYear && key <= endYear){
                this.put(key, ts.get(key));
            }
        }
    }

    /**
     * Returns all years for this TimeSeries (in any order).
     */
    public List<Integer> years() {
        return new ArrayList<Integer>(keySet());
    }

    /**
     * Returns all data for this TimeSeries (in any order).
     * Must be in the same order as years().
     */
    public List<Double> data() {
        List<Double> v = new ArrayList<>(); 
        Iterator<Integer> years = keySet().iterator();
        while(years.hasNext()){
            v.add(get(years.next()));
        }
        return v;
    }

    /**
     * Returns the year-wise sum of this TimeSeries with the given TS. In other words, for
     * each year, sum the data from this TimeSeries with the data from TS. Should return a
     * new TimeSeries (does not modify this TimeSeries).
     *
     * If both TimeSeries don't contain any years, return an empty TimeSeries.
     * If one TimeSeries contains a year that the other one doesn't, the returned TimeSeries
     * should store the value from the TimeSeries that contains that year.
     */
    public TimeSeries plus(TimeSeries ts) {

        TimeSeries mixTs = new TimeSeries();
        Set<Integer> onlythis = new TreeSet<Integer>();
        onlythis.addAll(keySet());
        onlythis.removeAll(ts.keySet());
        Set<Integer> onlyts = new TreeSet<Integer>();
        onlyts.addAll(ts.keySet());
        onlyts.removeAll(keySet());
        Set<Integer> both = new TreeSet<Integer>();
        both.addAll(keySet());
        both.retainAll(ts.keySet());

        for(Integer key : onlythis){
            mixTs.put(key, get(key));
        }
        for(Integer key : both){
            mixTs.put(key, get(key) + ts.get(key));
        }
        for(Integer key : onlyts){
            mixTs.put(key, ts.get(key));
        }
        return mixTs;
    }

    /**
     * Returns the quotient of the value for each year this TimeSeries divided by the
     * value for the same year in TS. Should return a new TimeSeries (does not modify this
     * TimeSeries).
     *
     * If TS is missing a year that exists in this TimeSeries, throw an
     * IllegalArgumentException.
     * If TS has a year that is not in this TimeSeries, ignore it.
     */
    public TimeSeries dividedBy(TimeSeries ts) {

        TimeSeries divTs = new TimeSeries();
        Set<Integer> onlythis = new TreeSet<Integer>();
        onlythis.addAll(keySet());
        onlythis.removeAll(ts.keySet());
        Set<Integer> both = new TreeSet<Integer>();
        both.addAll(keySet());
        both.retainAll(ts.keySet());
        if(!onlythis.isEmpty()){
            throw new IllegalArgumentException();
        }
        for(Integer key : both){
            divTs.put(key, get(key)/ts.get(key));
        }
        return divTs;
    }
}
