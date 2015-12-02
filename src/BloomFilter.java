/**
 * A probabilistic data structure to test whether a given element is a
 * member of a set.
 */
public class BloomFilter {

	/**
	 * The number of elements to save in our data structure.
	 */
    int n;

    /**
     * The wished error probability of the bloom filter.
     */
    double p;

    /**
     * The appropriate size of our filter.
     */
    double m;

    /**
     * The optimal number of hashing-functions.
     */
    double k;

    /**
     * The data strucutre of our bloom filter, containing true or false
     * depending if the given value has been already hashed or not.
     */
    boolean[] booleans;

    /**
     * The array containing k-hashing functions (murmur3_128-Hashing-functions).
     */
    Hasher[] hashers;

    /**
     * Constructs a Bloom-Filter for n-elements with an error-probability of p.
     *
     * The appropriate size of our filter - m - as well as the optimal size of hashing-
     * functions - k - is calculated by this constructor. For every k we construct a new
     * "Hasher" with a different seed for each and every one of these Hashers. So we get an
     * array of k-different Hashing-functions.
     *
     * @param n int		The number of elements
     * @param p	double	The desired error probability
     */
    public BloomFilter(int n, double p) {
        this.n = n;
        this.p = p;
        this.m = -1 * Math.ceil((n * Math.log(p)) / (Math.pow(Math.log(2.0), 2.0)));
        this.k = Math.round((m / n) * Math.log(2.0));

        this.booleans = new boolean[(int) m];

        hashers = new Hasher[(int) k];

        for(int i = 0; i < k; i++) {
            hashers[i] = new Hasher(i+1, (int) m);
        }
    }

    /**
     * Adds the passed String to the filter that means, hashes the String
     * k-times and sets the array-indeces of the hash-values to true.
     *
     * @param s String	The String to add to the filter.
     */
    public void addToFilter(String s) {
        for(Hasher h : hashers) {
            booleans[h.generateHashAndGetArrayIndex(s)] = true;
        }
    }

    /**
     * Checks if the passed String is contained in the data structure.
     * This function hashes the passed String k-times and checks if the values
     * at the positions of the data-structure are true. If at least one of those values
     * is false, the functions returns false, true otherwise. The function returns in
     * p-percent cases false positives.
     *
     * @param s String	The String to check if it is already registered in the data-structure.
     * @return boolean	True, if the String is already registered in the data-structure, false
     * 					otherwise.
     */
    public boolean checkIfExists(String s) {
        for(Hasher h : hashers) {
            if (!booleans[h.generateHashAndGetArrayIndex(s)]) return false;
        }
        return true;
    }
}
