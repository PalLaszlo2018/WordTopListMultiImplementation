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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author laszlop
 */
public class WordCollector  {

    private final List<URL> urlList;
    private final Set<String> skipTags; // ???
    private final Set<Character> separators;
    private final static Logger LOGGER = Logger.getLogger(WordCollector.class.getName());
    private final WordStore sorter;

    public WordCollector(List<URL> urlList, WordStore sorter) {
        LOGGER.setLevel(Level.WARNING); // DELETE for Logger info
        this.urlList = urlList;
        this.skipTags = new HashSet<>(Arrays.asList("head", "style")); // texts between these tags are ignored
        this.separators = new HashSet<>(Arrays.asList('_',' ', ',','/','"','(', ')','*', '<', '.', ':', '?', '!', ';', '-', '–', '=',
                '{', '}'));
        this.sorter = sorter;
    }

    public void processURLs() throws IOException {
        for (int i = 0; i < urlList.size(); i++) {
            URL url = urlList.get(i);
            processContent(url);
        }
    }

    public void processContent(URL url) throws IOException {
        LOGGER.info("Processing of the homepage " + url.toString() + " started");
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        String openingTag = findOpeningTag(reader);
        LOGGER.info(openingTag + " (as opening tag) identified.");
        eatTag(openingTag, reader);
        reader.close();
    }

    private void eatTag(String tag, BufferedReader reader) throws IOException {
        int value;
        StringBuilder word = new StringBuilder();
        while ((value = reader.read()) != -1) {
            char character = (char) value;
            if (character == '<') {
                if (!skipTags.contains(tag)) {
                    sorter.store(word.toString().toLowerCase());
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
                    sorter.store(word.toString().toLowerCase());
                }
                word.setLength(0);
                continue;
            }
            word.append(character);
        }
    }

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

    public void print() {
        sorter.print();
    }
    
    public void print(int n) {
        sorter.print(n);
    }

}
