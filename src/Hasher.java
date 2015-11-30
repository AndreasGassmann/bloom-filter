import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class Hasher {
    private HashFunction h;
    private int m;

    public Hasher(int seed, int m) {
        this.h = Hashing.murmur3_128(seed);
        this.m = m;
    }

    public int generateHash(String s) {
        return getArrayIndex(h.hashBytes(s.getBytes()));
    }

    private int getArrayIndex(HashCode c) {
        return Math.abs(c.hashCode() % m);
    }
}
