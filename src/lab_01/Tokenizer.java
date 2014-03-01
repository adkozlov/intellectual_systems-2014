package lab_01;

import java.util.*;

/**
 * @author adkozlov
 */
public final class Tokenizer {

    public static final String PUNCTUATION_REGEX = "\\p{Punct}+";
    public static final String SPACES_REGEX = "[\\s\\u00a0]";

    private static List<Token> tokenize(String document, int documentId) {
        String documentWithoutPunctuation = document.replaceAll(PUNCTUATION_REGEX, "").replaceAll(SPACES_REGEX, " ");
        StringTokenizer stringTokenizer = new StringTokenizer(documentWithoutPunctuation);

        List<Token> result = new LinkedList<>();
        while (stringTokenizer.hasMoreTokens()) {
            result.add(new Token(stringTokenizer.nextToken(), documentId));
        }

        return result;
    }

    public static List<Token> tokenize(List<String> documents) {
        List<Token> result = new LinkedList<>();
        int i = 0;
        for (String document : documents) {
            result.addAll(Tokenizer.tokenize(document, i++));
        }

        return result;
    }

    public static Set<String> getTokens(List<? extends Token> tokens) {
        Set<String> result = new TreeSet<>();
        for (Token token : tokens) {
            result.add(token.getToken());
        }

        return result;
    }
}
