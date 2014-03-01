package lab_01;

/**
 * @author adkozlov
 */
public class Token implements Comparable<Token> {

    private final String token;
    private final int documentId;

    public Token(String token, int documentId) {
        this.token = token;
        this.documentId = documentId;
    }

    public String getToken() {
        return token;
    }

    public int getDocumentId() {
        return documentId;
    }

    @Override
    public String toString() {
        return "Token{" +
                "token='" + token + '\'' +
                ", documentId=" + documentId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Token)) return false;

        Token token1 = (Token) o;

        if (token != null ? !token.equals(token1.token) : token1.token != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return token != null ? token.hashCode() : 0;
    }

    @Override
    public int compareTo(Token o) {
        return token.compareTo(o.token);
    }
}