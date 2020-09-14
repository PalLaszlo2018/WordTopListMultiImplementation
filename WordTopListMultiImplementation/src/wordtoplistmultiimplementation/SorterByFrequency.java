/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordtoplistmultiimplementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author laszlop
 */
public class SorterByFrequency implements WordStore {

    private final Map<String, Integer> wordFrequency = new HashMap<>();
    private final Set<String> skipTags;

    public SorterByFrequency() {
        this.skipTags = new HashSet<>(Arrays.asList("head", "style")); // texts between these tags are ignored
    }

    @Override
    public void store(String word) {
        if (word.length() > 1 && !skipTags.contains(word)) {
            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
        }
    }

    @Override
    public void addSkipWord(String word) {
        skipTags.add(word);
    }

    @Override
    public void print() {
        System.out.println("Full frequency list: " + sortedWordFreq());
    }

    @Override
    public void print(int n) {
        List<Map.Entry<String, Integer>> sortedList = sortedWordFreq();
        System.out.print("The " + n + " most used words:");
        for (int i = 0; i < n; i++) {
            System.out.print(" " + sortedList.get(i));
        }
        System.out.println("");
    }

    private List<Map.Entry<String, Integer>> sortedWordFreq() {
        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            sortedList.add(entry);
            WordFreqComparator wordFreqComp = new WordFreqComparator();
            Collections.sort(sortedList, wordFreqComp);
            Collections.reverse(sortedList);
        }
        return sortedList;
    }

}