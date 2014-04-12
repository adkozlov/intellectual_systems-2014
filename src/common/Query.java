package common;

import java.util.List;

/**
 * @author adkozlov
 */
public class Query {

    private final List<Terminus> words;

    public Query(List<String> words) {
        this.words = LexicalAnalyzer.analyze(Tokenizer.tokenize(words));
    }

    public List<Terminus> getWords() {
        return words;
    }
}
