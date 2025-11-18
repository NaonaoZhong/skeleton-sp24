package main;

import java.util.ArrayList;
import java.util.List;

import org.knowm.xchart.XYChart;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;
import plotting.Plotter;

public class HistoryHandler extends NgordnetQueryHandler{
    NGramMap nMap;
    public HistoryHandler(NGramMap map){
        nMap = map;   
    }

    @Override
    public String handle(NgordnetQuery q) {

        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        ArrayList<TimeSeries> lts = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();

        for(String word : words){
           lts.add(nMap.weightHistory(word, startYear, endYear));
           labels.add(word);
        }
        
        XYChart chart = Plotter.generateTimeSeriesChart(labels, lts);
        String encodedImage = Plotter.encodeChartAsString(chart);

        return encodedImage;
    }
}
