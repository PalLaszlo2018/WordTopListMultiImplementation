/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordtoplistmultiimplementation;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author laszlop
 */
public class WordTopListMultiImplementation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MalformedURLException, IOException {

        System.out.println("WordTopListMultiImplementation application started.");
        List<URL> urlList = new ArrayList<>();
        urlList.add(new URL("https://justinjackson.ca/words.html"));
        urlList.add(new URL("http://abouthungary.hu/"));
        urlList.add(new URL("https://www.javatpoint.com/java-tutorial"));
        urlList.add(new URL("https://www.bbc.com/"));
        System.out.println("Checked URL-s: " + urlList);
        WordStore freqSorter = new SorterByFrequency();
        WordCounter wordCounterF = new WordCounter(urlList, freqSorter);
        wordCounterF.printTopList();
        WordStore lengthSorter = new SorterByLength();
        WordCounter wordCounterL = new WordCounter(urlList, lengthSorter);
        wordCounterL.printTopList();

    }

}
