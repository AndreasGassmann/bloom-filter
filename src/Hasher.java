import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

/**
 * Class to create an Object containing a Murmur-Hash-Function based on a
 * specific seed.
 */
public class Hasher {

	/**
	 * A reference to one specific hash-function.
	 */
    private HashFunction h;

    /**
     * The size of the data-structure of the Bloom-Filter.
     */
    private int m;

    /**
     * Creates a Hasher, that is an object containing
     * a Murmur-Hash-Function with a specific seed. The seed is passed as
     * a parameter.
     *
     * @param seed int	The desired seed for the Murmur-Hash-Function.
     * @param m	   int	The size of the Bloom-Filter's data-structure.
     */
    public Hasher(int seed, int m) {
        this.h = Hashing.murmur3_128(seed);
        this.m = m;
    }

    /**
     * Generates a hash out of a String according to Murmur-Hash3_128.
     *
     * @param s String		The String to hash.
     * @return	HashCode	The hash-value for the passed String.
     */
    public int generateHash(String s) {
        return getIntFromHash(h.hashBytes(s.getBytes()));
    }

    /**
     * Gets an int (Array-Index) out of a hashed value.
     *
     * @param c HashCode	The hash-value to get the int of.
     * @return  int			The calculated integer value.
     */
    private int getIntFromHash(HashCode c) {
        return Math.abs(c.hashCode() % m);
    }
}
