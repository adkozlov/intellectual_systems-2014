package lab_02;

import common.*;

/**
 * @author adkozlov
 */
public class BM25 {

    private final ReverseIndex reverseIndex;
    private final ForwardIndex forwardIndex;

    private final double k1;
    private final double b;

    public BM25(ReverseIndex reverseIndex, ForwardIndex forwardIndex, double k1, double b) {
        this.reverseIndex = reverseIndex;
        this.forwardIndex = forwardIndex;
        this.k1 = k1;
        this.b = b;
    }

    public BM25(ReverseIndex reverseIndex, ForwardIndex forwardIndex) {
        this(reverseIndex, forwardIndex, Properties.DEFAULT_K1, Properties.DEFAULT_B);
    }

    public double getScore(Document document, Query query) {
        double result = 0;

        for (Terminus q : query.getWords()) {
            int f = frequency(q, document);
            result += idf(q) * f * (k1 + 1) / (f + k1 * (1 - b + b * document.getLength() / forwardIndex.getAverageLength()));
        }

        return result;
    }

    public static int frequency(Terminus q, Document document) {
        int result = 0;

        for (Terminus t : document.getWords()) {
            result += q.equals(t) ? 1 : 0;
        }

        return result;
    }

    public double idf(Terminus q) {
        return Math.log(forwardIndex.getDocumentsCount() / reverseIndex.terminusSet(q.getToken()).size());
    }
}
