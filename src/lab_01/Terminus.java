package lab_01;

import static lab_01.LexicalAnalyzer.createTerminus;

/**
 * @author adkozlov
 */
public class Terminus extends Token {

    public Terminus(Token token) {
        super(createTerminus(token.getToken()), token.getDocumentId());
    }

    @Override
    public int compareTo(Token o) {
        if (super.compareTo(o) == 0) {
            return 0;
        }

        return new Integer(getDocumentId()).compareTo(o.getDocumentId());
    }
}
