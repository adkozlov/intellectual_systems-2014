package lab_01;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author adkozlov
 */
public class LexicalAnalyzer {

    public static String createTerminus(String token) {
        return token.toLowerCase();
    }

    public static List<Terminus> analyze(List<Token> tokens) {
        List<Terminus> result = new LinkedList<>();
        for (Token token : tokens) {
            result.add(new Terminus(token));
        }

        Collections.sort(result);
        return result;
    }

    public static Set<String> getTerminuses(List<? extends Terminus> terminuses) {
        return Tokenizer.getTokens(terminuses);
    }
}
