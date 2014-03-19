package lab_02;

import common.*;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author adkozlov
 */
public class Main {

    public static void main(String[] args) {
        try {
            List<String> documents = HTMLParser.getParagraphs(Properties.URL, Properties.REGEX);
            // HTMLParser.getParagraphsFromFile(Properties.PATH + Properties.HTML_FILENAME, Properties.REGEX);

            ReverseIndex reverseIndex = new ReverseIndex(documents);

            CharStream stream = new ANTLRFileStream(Properties.QUERIES_INPUT_FILENAME);
            GrammarLexer lexer = new GrammarLexer(stream);
            GrammarParser parser = new GrammarParser(new CommonTokenStream(lexer));

            List<QueryResult> results = parser.file(reverseIndex).result;
            ForwardIndex forwardIndex = new ForwardIndex(documents);

            for (QueryResult result : results) {
                System.out.println("Query: " + result.getText());

                List<Snippet> snippets = new ArrayList<>();
                for (int index : result.getResult()) {
                    snippets.add(new Snippet(forwardIndex.getTerminuses(index), index, result.getMatchedWords()));
                }
                Collections.sort(snippets);

                for (int i = 0; i < snippets.size() && i < result.getCount(); i++) {
                    System.out.println(snippets.get(i));
                }

                System.out.println();
            }
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
}
