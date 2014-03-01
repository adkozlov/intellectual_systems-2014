package lab_01;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author adkozlov
 */
public final class HTMLParser {

    private HTMLParser() {

    }

    public static final String CHARSET_NAME = "windows-1251";

    private static Elements getElements(Document document, String regex) {
        return document.select(regex);
    }

    private static List<String> getStrings(Elements elements) {
        List<String> result = new LinkedList<>();
        for (Element element : elements) {
            result.add(element.text());

        }

        return result;
    }

    private static List<String> getStrings(Document document, String regex) {
        return getStrings(getElements(document, regex));
    }

    public static List<String> getParagraphs(String url, String regex) throws IOException {
        Document document = Jsoup.connect(url).maxBodySize(0).get();

        return getStrings(document, regex);
    }

    public static List<String> getParagraphsFromFile(String fileName, String regex) throws IOException {
        Document document = Jsoup.parse(new File(fileName), CHARSET_NAME);

        return getStrings(document, regex);
    }
}
