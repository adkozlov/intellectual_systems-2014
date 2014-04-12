package common;

import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author adkozlov
 */
public final class Tokenizer {

    public static final String PUNCTUATION_REGEX = "\\p{Punct}+";
    public static final String SPACES_REGEX = "[\\s\\u00a0]";

    private static String removePunctuation(String document) {
        return document.replaceAll(PUNCTUATION_REGEX, "").replaceAll(SPACES_REGEX, " ");
    }

    public static List<Token> tokenize(String document) {
        return tokenize(document, 0);
    }

    private static List<Token> tokenize(String document, int documentId) {
        StringTokenizer stringTokenizer = new StringTokenizer(removePunctuation(document));

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

    public static List<String> getTokens(List<? extends Token> tokens) {
        List<String> result = new LinkedList<>();
        for (Token token : tokens) {
            result.add(token.getToken());
        }

        return result;
    }
}
