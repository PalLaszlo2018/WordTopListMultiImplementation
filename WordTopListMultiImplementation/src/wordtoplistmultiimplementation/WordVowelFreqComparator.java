/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordtoplistmultiimplementation;

import java.util.Comparator;
import java.util.Map;

/**
 * This comparator helps sorting the word - vowel frequency entries. It checks the vowel frequency first (higher values prioritized),
 * in case of equal frequencies alphabetical order will be used.
 * @author laszlop
 */
public class WordVowelFreqComparator implements Comparator<Map.Entry<String, Double>> {

    @Override
    public int compare(Map.Entry<String, Double> wordFreq1, Map.Entry<String, Double> wordFreq2) {
        long freq1 = Math.round(1_000_000 * wordFreq1.getValue());
        long freq2 = Math.round(1_000_000 * wordFreq2.getValue());
        if (freq1 < freq2) {
            return 1;
        } else if (freq1 > freq2) {
            return -1;
        } else {
            return wordFreq1.getKey().compareTo(wordFreq2.getKey());
        }
    }
    
}