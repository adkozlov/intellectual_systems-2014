package lab_02;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author adkozlov
 */
public class Snippet implements Comparable<Snippet> {

    private final int documentIndex;

    private final Set<String> matchedWords;
    private final int matchedWordsCount;

    public Snippet(List<String> document, int documentIndex, Collection<String> terminuses) {
        this.documentIndex = documentIndex;

        matchedWords = new HashSet<>();
        int result = 0;
        for (String terminus : terminuses) {
            for (String s : document) {
                if (terminus.equals(s)) {
                    matchedWords.add(s);
                    result++;
                }
            }
        }

        matchedWordsCount = result;
    }

    @Override
    public int compareTo(Snippet o) {
        return -new Integer(matchedWordsCount).compareTo(matchedWordsCount);
    }

    @Override
    public String toString() {
        String result = "Index: " + (documentIndex + 1) + ", matched words: " + matchedWordsCount;

        if (!matchedWords.isEmpty()) {
            result += "\n" + matchedWords;
        }

        return result;
    }
}
