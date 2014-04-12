package lab_02;

import common.Query;

import java.util.List;

/**
 * @author adkozlov
 */
public class QueryResult {

    private final String text;
    private final List<Integer> result;
    private final Query matchedWords;
    private final int count;

    QueryResult(String text, List<Integer> result, List<String> matchedWords, int count) {
        this.text = text;
        this.result = result;
        this.matchedWords = new Query(matchedWords);
        this.count = count;
    }

    public String getText() {
        return text;
    }

    public List<Integer> getResult() {
        return result;
    }

    public Query getMatchedWords() {
        return matchedWords;
    }

    public int getCount() {
        return count;
    }
}