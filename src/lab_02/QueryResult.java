package lab_02;

import java.util.List;

/**
 * @author adkozlov
 */
public class QueryResult {

    private final String text;
    private final List<Integer> result;
    private final List<String> matchedWords;
    private final int count;

    QueryResult(String text, List<Integer> result, List<String> matchedWords, int count) {
        this.text = text;
        this.result = result;
        this.matchedWords = matchedWords;
        this.count = count;
    }

    public String getText() {
        return text;
    }

    public List<Integer> getResult() {
        return result;
    }

    public List<String> getMatchedWords() {
        return matchedWords;
    }

    public int getCount() {
        return count;
    }
}