/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordtoplistmultiimplementation;

import java.util.Comparator;
import java.util.Map;

/**
 * This comparator can help sorting the word-length entries. It checks the length first (higher values prioritized), in case
 * of equal length alphabetical order will be used.
 * @author laszlop
 */
public class WordLenComparator implements Comparator<Map.Entry<String, Integer>> {
    // TODO LP: you don't use this comparator at all :)

    @Override
    public int compare(Map.Entry<String, Integer> wordLen1, Map.Entry<String, Integer> wordLen2) {
        Integer len1 = wordLen1.getValue();
        Integer len2 = wordLen2.getValue();
        if (len1 != len2) {
            return len1.compareTo(len2);
        }
        return wordLen1.getKey().compareTo(wordLen2.getKey());
    }
    
}