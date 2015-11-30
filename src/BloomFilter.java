public class BloomFilter {

    int n;
    double p, m, k;

    boolean[] booleans;

    Hasher[] hashers;

    public BloomFilter(int n, double p) {
        this.n = n; // lines
        this.p = p; // Fehlerwahrscheinlichkeit
        this.m = -1 * Math.ceil((n * Math.log(p)) / (Math.pow(Math.log(2.0), 2.0)));
        this.k = Math.round((m / n) * Math.log(2.0));

        System.out.println("m: " + m);
        System.out.println("k: " + k);

        this.booleans = new boolean[(int) m];

        hashers = new Hasher[(int) k];

        for(int i = 0; i < k; i++) {
            hashers[i] = new Hasher(i+1, (int) m);
        }
    }

    public void addToFilter(String s) {
        for(Hasher h : hashers) {
            booleans[h.generateHash(s)] = true;
        }
    }

    public boolean checkIfExists(String s) {
        for(Hasher h : hashers) {
            if (!booleans[h.generateHash(s)]) return false;
        }
        return true;
    }
}
