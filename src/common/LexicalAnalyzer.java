package common;

import java.util.*;

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
        Set<String> result = new TreeSet<>();
        result.addAll(Tokenizer.getTokens(terminuses));

        return result;
    }
}
