/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordtoplistmultiimplementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 *This class manages the processing of the got URLList, collects the words of the contents in a Map.
 * @author laszlop
 */
public class WordCollector {

    private final List<URL> urlList;
    private final Set<String> skipTags;
    private final Set<Character> separators;
    private final WordStore storer;
    private final static Logger LOGGER = Logger.getLogger(WordCollector.class.getName());

    public WordCollector(List<URL> urlList, WordStore storer) {
        this.urlList = urlList;
        this.skipTags = new HashSet<>(Arrays.asList("head", "style")); // texts between these tags are ignored
        this.separators = new HashSet<>(Arrays.asList(' ', '"', '(', ')', '*', '<', '.', ':', '?', '!', ';', '-', '–', '=', '{', '}',
                '/', '_', ',', '[', ']'));
        this.storer = storer;
    }

    /**
     * Loops on the URLlist, and starts the processing for each URL.
     *
     * @throws IOException
     */
    public void processURLs() throws IOException {
        for (int i = 0; i < urlList.size(); i++) {
            URL url = urlList.get(i);
            processContent(url);
        }
    }
    
    /**
     * Opens a reader for the got URL, finds the opening tag, and starts the substantive work by calling the eatTag method.
     * @param url
     * @throws IOException 
     */

    public void processContent(URL url) throws IOException {
        LOGGER.info("Processing of the homepage " + url.toString() + " started");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String openingTag = findOpeningTag(reader);
            LOGGER.info(openingTag + " (as opening tag) identified.");
            eatTag(openingTag, reader);
        }
    }
    
    /**
     * Reads the content of the URL, the found words will be put into a Map, found opening tags start the method recursive way,
     * found closing tags close the (sub)method.
     * @param tag
     * @param reader
     * @throws IOException 
     */

    private void eatTag(String tag, BufferedReader reader) throws IOException {
        int value;
        StringBuilder word = new StringBuilder();
        while ((value = reader.read()) != -1) {
            char character = (char) value;
            if (character == '<') {
                if (!skipTags.contains(tag)) {
                    storer.store(word.toString().toLowerCase());
                }
                String nextTagString = buildTag(reader);
                if (('/' + tag).equals(nextTagString)) {
                    return;
                }
                if (!skipTags.contains(tag) && !nextTagString.startsWith("/")) {
                    eatTag(nextTagString, reader);
                }
            }
            if (separators.contains(character) || Character.isWhitespace(character)) {
                if (!skipTags.contains(tag)) {
                    storer.store(word.toString().toLowerCase());
                }
                word.setLength(0);
                continue;
            }
            word.append(character);
        }
    }
    
    /**
     * This method finds the first opening tag, this tag is needed to start the substantive eatTag method.
     * @param reader
     * @return opening tag
     * @throws IOException 
     */

    private String findOpeningTag(BufferedReader reader) throws IOException {
        int value;
        String openingTag = "";
        while ((value = reader.read()) != -1) {
            char character = (char) value;
            if (character == '<') {
                openingTag = buildTag(reader);
                return openingTag;
            }
        }
        return openingTag;
    }
    
    /**
     * This method builds up the tag from the read characters.
     * @param reader
     * @return tag
     * @throws IOException 
     */

    private String buildTag(BufferedReader reader) throws IOException {
        StringBuilder tag = new StringBuilder();
        int value;
        while ((value = reader.read()) != -1) {
            char tagChar = (char) value;
            if (tagChar == '>') {
                return tag.toString().toLowerCase();
            }
            tag.append(tagChar);
        }
        return tag.toString().toLowerCase();
    }
       
    /**
     * This method add the got word to the Set which contains the words to be ignored.
     * @param word 
     */

    public void addSkipWord(String word) {
        storer.addSkipWord(word);
    }
    
    /**
     * Prints the full list of the found words.
     */

    public void print() {
        storer.print();
    }
    
    /**
     * Prints the n-sized top-list of the found words.
     * @param n 
     */

    public void print(int n) {
        storer.print(n);
    }
}
