import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BloomFilterRunner {
    public static void main(String[] args) throws IOException {


        Path path = Paths.get("assets/", "words.txt");
        Path pathDeutsch = Paths.get("assets/", "deutsch.txt");

        BloomFilter bf = new BloomFilter(58111, 0.5);

        Files.lines(path).forEach(s -> bf.addToFilter(s));

        int counter = 0;
        int linesDeutsch = 71700;

        Object[] strings = Files.lines(pathDeutsch).toArray();
        for(Object s : strings) {
            if (bf.checkIfExists((String) s)) {
                counter++;
            }
        }

        System.out.println(counter + " von " + linesDeutsch + " wurden falsch erkannt: " + (double) counter/linesDeutsch + "%");

        counter = 0;
        for(boolean b : bf.booleans) {
            if (b) counter++;
        }

        System.out.println(counter + "/" + bf.booleans.length);

        /*
        int counter = 0;
        int counterInner = 0;

        Object[] strings = Files.lines(pathDeutsch).toArray();
        Object[] strings1 = Files.lines(path).toArray();


            for(Object d : strings1) {
                if (s.equals(d)) {
                    System.out.println(s);
                }
            }
        }
        */
    }
}