package lab_01;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @author adkozlov
 */
public class Main {

    public static final String URL = "http://az.lib.ru/t/tolstoj_lew_nikolaewich/text_0080.shtml";
    public static final String FILENAME = "karenina.out";
    public static final String REGEX = "dd";

    public static void main(String[] args) {
        try {
            List<Token> tokens = Tokenizer.tokenize(HTMLParser.getParagraphs(URL, REGEX));
            Set<String> tokensSet = Tokenizer.getTokens(tokens);
            System.out.printf("Number of tokens = %d\n", tokensSet.size());
            System.out.printf("Token average length = %.3f\n", Index.averageLength(tokensSet));

            List<Terminus> terminuses = LexicalAnalyzer.analyze(tokens);
            Set<String> terminusesSet = LexicalAnalyzer.getTerminuses(terminuses);
            System.out.printf("Number of terminuses = %d\n", terminusesSet.size());
            System.out.printf("Terminus average length = %.3f\n", Index.averageLength(terminusesSet));

            Index index = new Index(terminuses);
            index.writeToFile(FILENAME);
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
}
