package lab_01;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * @author adkozlov
 */
public class Index {

    private final Map<String, Set<Integer>> index;

    public Index(List<Terminus> terminuses) {
        index = new TreeMap<>();

        for (Terminus terminus : terminuses) {
            String tokenString = terminus.getToken();
            Set<Integer> set = index.containsKey(tokenString) ? index.get(tokenString) : new TreeSet<Integer>();

            set.add(terminus.getDocumentId());
            index.put(terminus.getToken(), set);
        }
    }

    public static final String FORMAT = "%s: %s\n";

    public void writeToFile(String fileName) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName));

            for (Map.Entry<String, Set<Integer>> entry : index.entrySet()) {
                writer.append(String.format(FORMAT, entry.getKey(), entry.getValue()));
            }
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }

    public static double averageLength(Set<String> strings) {
        double totalLength = 0;
        for (String string : strings) {
            totalLength += string.length();
        }

        return totalLength / strings.size();
    }
}
