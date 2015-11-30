# Bloom-Filter
A small open-source implementation of a bloom-filter in Java.

### Use
The constructor of the Bloom-Filter expects two arguments: the number of elements as well as the desired error-probability (passed as a number between 0 and 1).
Example:
    BloomFilter bf = new BloomFilter(58111, 0.05);

Out of these two values, the Bloom-Filter calculates automatically the optimal number of required Hashing-Functions and the optimal size of the data-structure.
