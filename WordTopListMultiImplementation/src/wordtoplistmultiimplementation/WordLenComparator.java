/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordtoplistmultiimplementation;

import java.util.Comparator;

/**
 * This comparator can help sorting the words by their lengths. (higher values prioritized).
 * @author laszlop
 */
public class WordLenComparator implements Comparator<String> {

    @Override
    public int compare(String word1, String word2) {
        if (word1.length() < word2.length()) {
            return 1;
        } else if (word1.length() > word2.length()) {
            return -1;
        } else {
            return 0;
        }
    }
    
}