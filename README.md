# Bloom-Filter
A small open-source implementation of a bloom-filter in Java. This filter can only be used with text-files that contain one word per line.

### Use
The constructor of the Bloom-Filter expects two arguments: the number of elements as well as the desired error-probability (passed as a number between 0 and 1).

Example:
```
    BloomFilter bf = new BloomFilter(58111, 0.05);
```
Out of these two values, the Bloom-Filter calculates automatically the optimal number of required Hashing-Functions and the optimal size of the data-structure.

To fill the data-strucutre of the bloom-filter, iterate over all the strings to add to the data-structure and use the 'addToFilter(String s)'-Function like this:
```
    Files.lines(path).forEach(s -> bf.addToFilter(s));
```

To check if a given value already exists in the data-structure use the function 'checkIfExists(String StringToCheck)' like this:
```
    bf.checkIfExists((String) s);
```
