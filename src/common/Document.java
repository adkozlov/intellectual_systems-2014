package common;

import java.util.List;

/**
 * @author adkozlov
 */
public class Document {

    private final List<Terminus> document;
    private final int length;

    public Document(List<Terminus> document) {
        this.document = document;
        this.length = document.size();
    }

    public List<Terminus> getWords() {
        return document;
    }

    public int getLength() {
        return length;
    }
}
