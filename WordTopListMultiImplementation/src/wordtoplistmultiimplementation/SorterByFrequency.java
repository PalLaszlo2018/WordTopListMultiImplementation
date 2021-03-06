/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordtoplistmultiimplementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class can be used to create sorting by frequency
 * @author laszlop
 */
public class SorterByFrequency implements WordStore {

    private final Map<String, Integer> wordFrequency = new HashMap<>();
    private final Set<String> skipWords = new HashSet<>();
    
    /**
     * This method adds the got word to the Map which contains the found valid words.
     * @param word 
     */

    @Override
    public void store(String word) {
        if (word.length() > 1 && !skipWords.contains(word)) {
            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
        }
    }
    
    /**
     * This method adds the got word to the Set which contains the words to be ignored.
     * @param word 
     */

    @Override
    public void addSkipWord(String word) {
        skipWords.add(word);
    }
    
     /**
     * Prints the full list of the found words.
     */

    @Override
    public void print() {
        System.out.println("Full frequency list: " + sortedWordsByFreq());
    }
    
     /**
     * Prints the n-sized top-list of the found words.
     * @param n 
     */

    @Override
    public void print(int n) {
        List<Map.Entry<String, Integer>> sortedList = sortedWordsByFreq();
        System.out.print("The " + n + " most used words:");
        for (int i = 0; i < n; i++) {
            System.out.print(" " + sortedList.get(i));
        }
        System.out.println("");
    }
    
     /**
     * Creates the sorted List of the entries of the Map.
     * @return 
     */
    
    private List<Map.Entry<String, Integer>> sortedWordsByFreq() {
        ArrayList<Map.Entry<String, Integer>> sortedList = new ArrayList<>(wordFrequency.entrySet());
        Collections.sort(sortedList, new WordFreqComparator());
        return sortedList;
    }
}
