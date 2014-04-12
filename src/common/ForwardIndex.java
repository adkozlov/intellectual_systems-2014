package common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author adkozlov
 */
public class ForwardIndex {

    private final List<Document> documents;
    private final double averageLength;

    public ForwardIndex(List<String> notAnalyzedDocuments) {
        int sumLength = 0;

        documents = new ArrayList<>();
        for (String document : notAnalyzedDocuments) {
            Document d = new Document(LexicalAnalyzer.analyze(Tokenizer.tokenize(document)));
            documents.add(d);

            sumLength += d.getLength();
        }

        averageLength = sumLength / getDocumentsCount();
    }

    public Document getDocument(int index) {
        return documents.get(index);
    }

    public int getDocumentsCount() {
        return documents.size();
    }

    public Document getTerminuses(int index) {
        return documents.get(index);
    }

    public double getAverageLength() {
        return averageLength;
    }
}
