/**
 * A common interface used by all hash functions for strings.  This allows
 * the HashTable class to be built to use any hash function.
 */
public interface StringHasher<E> {
    /* 
     * Returns an integer that is a hash value for the given string s.
     */   
    public int hash(String s);
}