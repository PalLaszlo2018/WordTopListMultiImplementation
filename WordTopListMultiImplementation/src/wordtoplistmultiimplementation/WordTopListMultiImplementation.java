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
 * This application allows you to give some URL-s, and it will collect the X most used words of the content of the given homepages.
 * You can add skipwords, which will not be collected. You can define the value of X as well.
 * @author laszlop
 */
public class WordTopListMultiImplementation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MalformedURLException, IOException {
        System.out.println("WordTopListRecursive application started.");
        List<URL> urlList = new ArrayList<>();
        urlList.add(new URL("https://justinjackson.ca/words.html"));
//        urlList.add(new URL("http://abouthungary.hu/"));
//        urlList.add(new URL("https://www.javatpoint.com/java-tutorial"));
//        urlList.add(new URL("https://www.bbc.com/"));
        System.out.println("Checked URL-s: " + urlList);
        
        WordStore wordStoreFreq = new SorterByFrequency();
              
        WordCollector wordCollectorFreq = new WordCollector(urlList, wordStoreFreq);
        wordCollectorFreq.addSkipWord("an");
        wordCollectorFreq.addSkipWord("and");
        wordCollectorFreq.addSkipWord("by");
        wordCollectorFreq.addSkipWord("for");
        wordCollectorFreq.addSkipWord("if");
        wordCollectorFreq.addSkipWord("in");
        wordCollectorFreq.addSkipWord("is");
        wordCollectorFreq.addSkipWord("it");
        wordCollectorFreq.addSkipWord("of");
        wordCollectorFreq.addSkipWord("on");
        wordCollectorFreq.addSkipWord("that");
        wordCollectorFreq.addSkipWord("the");
        wordCollectorFreq.addSkipWord("to");
        wordCollectorFreq.addSkipWord("with");
        
        wordCollectorFreq.processURLs();
        wordCollectorFreq.print(10);
        
        System.out.println("\n");
        
        WordStore wordCollectorLen = new SorterByLength();     
        WordCollector wordStoringLen = new WordCollector(urlList, wordCollectorLen);
        wordStoringLen.addSkipWord("an");
        wordStoringLen.addSkipWord("and");
        wordStoringLen.addSkipWord("by");
        wordStoringLen.addSkipWord("for");
        wordStoringLen.addSkipWord("if");
        wordStoringLen.addSkipWord("in");
        wordStoringLen.addSkipWord("is");
        wordStoringLen.addSkipWord("it");
        wordStoringLen.addSkipWord("of");
        wordStoringLen.addSkipWord("on");
        wordStoringLen.addSkipWord("that");
        wordStoringLen.addSkipWord("the");
        wordStoringLen.addSkipWord("to");
        wordStoringLen.addSkipWord("with");
        
        wordStoringLen.processURLs();
        wordStoringLen.print(10);
        
        
        
        
    }

}
