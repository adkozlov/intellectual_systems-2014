package common;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * @author adkozlov
 */
public class ReverseIndex {

    private final Map<String, Set<Integer>> index;
    private final int documentsCount;

    public ReverseIndex(List<Terminus> terminuses, int documentsCount) {
        index = new TreeMap<>();
        this.documentsCount = documentsCount;

        for (Terminus terminus : terminuses) {
            String tokenString = terminus.getToken();
            Set<Integer> set = index.containsKey(tokenString) ? index.get(tokenString) : new TreeSet<Integer>();

            set.add(terminus.getDocumentId());
            index.put(terminus.getToken(), set);
        }
    }

    public Set<Integer> terminusSet(String terminus) {
        if (index.containsKey(terminus)) {
            return new TreeSet<>(index.get(terminus));
        } else {
            throw new NoSuchElementException(String.format("Index doesn't contain the terminus: %s.", terminus));
        }
    }

    public Set<Integer> universeSet() {
        Set<Integer> result = new TreeSet<>();
        for (int i = 0; i < documentsCount; i++) {
            result.add(i);
        }

        return result;
    }

    public void writeToFile(String fileName) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName));

            for (Map.Entry<String, Set<Integer>> entry : index.entrySet()) {
                writer.append(entry.getKey() + " ");

                for (Integer i : entry.getValue()) {
                    writer.append((i + 1) + " ");
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

    public static double averageLength(Collection<String> strings) {
        double totalLength = 0;
        for (String string : strings) {
            totalLength += string.length();
        }

        return totalLength / strings.size();
    }
}
