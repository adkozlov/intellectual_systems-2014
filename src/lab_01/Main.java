package lab_01;

import common.*;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @author adkozlov
 */
public class Main {

    public static void main(String[] args) {
        try {
            List<String> documents = HTMLParser.getParagraphs(Properties.URL, Properties.REGEX);

            List<Token> tokens = Tokenizer.tokenize(documents);
            List<String> tokensList = Tokenizer.getTokens(tokens);
            System.out.printf("Number of tokens = %d\n", tokensList.size());
            System.out.printf("Token average length = %.3f\n", ReverseIndex.averageLength(tokensList));

            List<Terminus> terminuses = LexicalAnalyzer.analyze(tokens);
            Set<String> terminusesSet = LexicalAnalyzer.getTerminuses(terminuses);
            System.out.printf("Number of terminuses = %d\n", terminusesSet.size());
            System.out.printf("Terminus average length = %.3f\n", ReverseIndex.averageLength(terminusesSet));

            ReverseIndex index = new ReverseIndex(terminuses, documents.size());
            index.writeToFile(Properties.INDEX_OUTPUT_FILENAME);

            System.out.printf("Reverse index is written into \'%s%s\'", Properties.PATH, Properties.INDEX_OUTPUT_FILENAME);
            System.out.println();
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
}
