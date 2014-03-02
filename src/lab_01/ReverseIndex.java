package lab_01;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * @author adkozlov
 */
public class ReverseIndex {

    private final Map<String, Set<Integer>> index;

    public ReverseIndex(List<Terminus> terminuses) {
        index = new TreeMap<>();

        for (Terminus terminus : terminuses) {
            String tokenString = terminus.getToken();
            Set<Integer> set = index.containsKey(tokenString) ? index.get(tokenString) : new TreeSet<Integer>();

            set.add(terminus.getDocumentId());
            index.put(terminus.getToken(), set);
        }
    }

    public void writeToFile(String fileName) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName));

            for (Map.Entry<String, Set<Integer>> entry : index.entrySet()) {
                writer.append(entry.getKey() + " ");

                for (Integer i : entry.getValue()) {
                    writer.append(i.toString() + " ");
                }

                writer.append("\n");
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
