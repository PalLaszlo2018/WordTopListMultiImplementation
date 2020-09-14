/*


TASK DESCRIPTION:

1. Adott egy url lista (Egyelőre konstansként deklaráljuk). Listázzuk ki
az url-eken található html tartalmakban a 10 (vagy X) leggyakrabban
előfoduló szót, ami nem html tag vagy attribútum.
Legyen továbbá egy skipword halmaz, amit nem tekintünk szónak, ahova
kötőszavakat írjuk fel. HashMap-et és HashSet-et kell használni, hogy
elég gyors legyen. (ha ismeretlen a HashMap logikája, olvass róla itt:
https://beginnersbook.com/2013/12/hashmap-in-java-with-example/ -
továbbá google segítségével derítsd ki, mitől jó egy kulcs a HashMap esetén)
Legyen egy skipTags halmaz is, ami olyan tagokat sorol fel, amik közé
írt szavakat nem vesszük figyelembe.
(pl. <tag1> dsd dsfg ds<tag2><tag2>  dsfgdsf</tag2></tag2> dsfsd<tag1>
a skipTag-ek közé kezdetben a style, head szavakat vesszük fel.

A tag-eket rekurziv módonon dolgozza fel egy eatTag() method (paramétere lehet)
 */
package wordtoplistmultiimplementation;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 *
 * @author laszlop
 */
public class WordCounter {

    private final List<URL> urlList;
    private final static int LENGTH_OF_TOPLIST = 10;
    private final WordStore sorter; 

    public WordCounter(List<URL> urlList, WordStore sorter) {
        this.urlList = urlList;
        this.sorter = sorter;
    }
    
    public void printTopList() throws IOException {
        sorter.addSkipWord("an");
        sorter.addSkipWord("and");
        sorter.addSkipWord("as");
        sorter.addSkipWord("by");
        sorter.addSkipWord("for");
        sorter.addSkipWord("if");
        sorter.addSkipWord("in");
        sorter.addSkipWord("is");
        sorter.addSkipWord("of");
        sorter.addSkipWord("on");
        sorter.addSkipWord("that");
        sorter.addSkipWord("the");
        sorter.addSkipWord("to");
        sorter.addSkipWord("with");  
        WordCollector wordCollector = new WordCollector(urlList, sorter);     
        wordCollector.processURLs();
        wordCollector.print();        
        wordCollector.print(LENGTH_OF_TOPLIST);
 
        
        
        
        
        
        
        
    }


}
