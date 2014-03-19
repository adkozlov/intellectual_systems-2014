package common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author adkozlov
 */
public class ForwardIndex {

    private final List<List<String>> documents;

    public ForwardIndex(List<String> notAnalyzedDocuments) {
        documents = new ArrayList<>();
        for (String document : notAnalyzedDocuments) {
            documents.add(LexicalAnalyzer.analyzeDocument(Tokenizer.tokenizeDocument(document)));
        }
    }

    public List<List<String>> getDocuments() {
        return documents;
    }

    public List<String> getTerminuses(int index) {
        return documents.get(index);
    }
}
