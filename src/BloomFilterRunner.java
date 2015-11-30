import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BloomFilterRunner {
    public static void main(String[] args) throws IOException {

    	//Set the path to the initial text-file.
        Path path = Paths.get("assets/", "words.txt");

        //Set the path to the "test-text-file".
        Path pathDeutsch = Paths.get("assets/", "deutsch.txt");

        //Construct a new Bloom-Filter with 58111-elements and an
        //error-probability of 5%.
        BloomFilter bf = new BloomFilter(58111, 0.05);

        //Hash every line of the initial file and add the word to the data-
        //structure of the bloom-filter.
        Files.lines(path).forEach(s -> bf.addToFilter(s));

        int counter = 0;
        int linesDeutsch = 71700;

        /**
         * TEST-SECTION
         * Tests the bloom-filter.
         */

        //Calculate for every line (aka word) of the test-file if
        //it is already a member of the data-structure.
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

    }
}
