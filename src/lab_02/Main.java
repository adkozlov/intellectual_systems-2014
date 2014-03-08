package lab_02;

import common.*;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.util.List;

/**
 * @author adkozlov
 */
public class Main {

    public static void main(String[] args) {
        try {
            List<String> documents = HTMLParser.getParagraphs(Properties.URL, Properties.REGEX);
            ReverseIndex index = new ReverseIndex(LexicalAnalyzer.analyze(Tokenizer.tokenize(documents)), documents.size());

            CharStream stream = new ANTLRFileStream("karenina.in");
            GrammarLexer lexer = new GrammarLexer(stream);
            GrammarParser parser = new GrammarParser(new CommonTokenStream(lexer));
            parser.file();
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
}
