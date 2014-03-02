package lab_01;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @author adkozlov
 */
public class Main {

    public static final String URL = "http://az.lib.ru/t/tolstoj_lew_nikolaewich/text_0080.shtml";
    public static final String PATH = System.getProperty("user.dir") + System.getProperty("file.separator");
    //public static final String INPUT_FILENAME = "karenina.html";
    public static final String OUTPUT_FILENAME = "karenina.idx";
    public static final String REGEX = "dd";

    public static void main(String[] args) {
        try {
            List<Token> tokens = Tokenizer.tokenize(HTMLParser.getParagraphs(URL, REGEX));
            Set<String> tokensSet = Tokenizer.getTokens(tokens);
            System.out.printf("Number of tokens = %d\n", tokensSet.size());
            System.out.printf("Token average length = %.3f\n", ReverseIndex.averageLength(tokensSet));

            List<Terminus> terminuses = LexicalAnalyzer.analyze(tokens);
            Set<String> terminusesSet = LexicalAnalyzer.getTerminuses(terminuses);
            System.out.printf("Number of terminuses = %d\n", terminusesSet.size());
            System.out.printf("Terminus average length = %.3f\n", ReverseIndex.averageLength(terminusesSet));

            ReverseIndex index = new ReverseIndex(terminuses);
            index.writeToFile(OUTPUT_FILENAME);

            System.out.printf("Reverse index is written into \'%s%s\'", PATH, OUTPUT_FILENAME);
            System.out.println();
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
}
