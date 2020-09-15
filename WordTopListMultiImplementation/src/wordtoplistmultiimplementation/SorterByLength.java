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
 * This class can be used to create sorting by the length of the word
 * @author laszlop
 */
public class SorterByLength implements WordStore {

    // TODO LP: I think this is not an optimal solution to store a String and its length in a hashmap
    // since the String itself contains the lenght information...so I think it is enough to store the different words once
    // please try to choose a more appropriate collection type, if you have a good idea skype me! :)
    private final Map<String, Integer> wordLength = new HashMap<>();
    private final Set<String> skipWords = new HashSet<>();
    
    /**
     * This method add the got word to the Set which contains the words to be ignored.
     * @param word 
     */

    @Override
    public void store(String word) {
        if (word.length() > 1 && !skipWords.contains(word) && !wordLength.containsKey(word)) {
            wordLength.put(word, word.length());
        }
    }
    
    /**
     * This method add the got word to the Set which contains the words to be ignored.
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
        System.out.println("Full wordlength list: " + sortedWordsByLength());
    }
    
    /**
     * Prints the n-sized toplist of the found words.
     * @param n 
     */

    @Override
    public void print(int n) {
        List<Map.Entry<String, Integer>> sortedList = sortedWordsByLength();
        System.out.print("The " + n + " longest words:");
        for (int i = 0; i < n; i++) {
            System.out.print(" " + sortedList.get(i));
        }
        System.out.println("");
    }
    
    /**
     * Creates the sorted List of the entries of the Map.
     * @return 
     */

    private List<Map.Entry<String, Integer>> sortedWordsByLength() {
        ArrayList<Map.Entry<String, Integer>> sortedList = new ArrayList<>(wordLength.entrySet());
        Collections.sort(sortedList, new WordFreqComparator());
        return sortedList;
    }

}
