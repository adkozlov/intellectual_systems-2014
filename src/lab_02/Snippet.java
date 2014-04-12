package lab_02;

import common.Document;
import common.Query;
import common.Terminus;

import java.util.HashSet;
import java.util.Set;

/**
 * @author adkozlov
 */
public class Snippet implements Comparable<Snippet> {

    private final int documentIndex;

    private final Set<Terminus> matchedWords;
    private final int matchedWordsCount;
    private final double bm25;

    public Snippet(Document document, int documentIndex, Query query, double bm25) {
        this.documentIndex = documentIndex;

        matchedWords = new HashSet<>();
        int result = 0;
        for (Terminus terminus : query.getWords()) {
            for (Terminus s : document.getWords()) {
                if (terminus.equals(s)) {
                    matchedWords.add(s);
                    result++;
                }
            }
        }

        matchedWordsCount = result;
        this.bm25 = bm25;
    }

    @Override
    public int compareTo(Snippet o) {
        return new Double(o.bm25).compareTo(bm25);
    }

    @Override
    public String toString() {
        String result = "Index: " + (documentIndex + 1) + ", matched words: " + matchedWordsCount + ", BM25 = " + bm25;

        if (!matchedWords.isEmpty()) {
            result += "\n" + matchedWords;
        }

        return result;
    }
}
