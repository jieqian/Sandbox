package com.sandbox.murmurhash3;


/**
 * This class represents a common API for hashing functions.
 */
public abstract class Hash {
  /** Constant to denote invalid hash type. */
  public static final int INVALID_HASH = -1;
  /** Constant to denote {@link MurmurHash3}. */
  public static final int MURMUR_HASH3  = 2;
  
  /**
   * This utility method converts String representation of hash function name
   * to a symbolic constant. Currently two function types are supported,
   * "murmur".
   * @param name hash function name
   * @return one of the predefined constants
   */
  public static int parseHashType(String name) {
    if ("murmur3".equalsIgnoreCase(name)) {
      return MURMUR_HASH3;
    } else {
      return INVALID_HASH;
    }
  }
  
  /**
   * Get a singleton instance of hash function of a given type.
   * @param type predefined hash type
   * @return hash function instance, or null if type is invalid
   */
  public static Hash getInstance(int type) {
	  return MurmurHash3.getInstance();
  }
  
  protected static long seedLong() {
      return System.nanoTime();
  }
  
  protected static int seedInt() {
      return ((Long)System.nanoTime()).hashCode();
  }
  
  /**
   * Calculate a hash using all bytes from the input argument, and
   * current time as seed.
   * @param bytes input bytes
   * @return hash value
   */
  public int hash(byte[] bytes) {
    return hash(bytes, bytes.length, seedInt());
  }
  
  /**
   * Calculate a hash using all bytes from the input argument,
   * and a provided seed value.
   * @param bytes input bytes
   * @param seed seed value
   * @return hash value
   */
  public int hash(byte[] bytes, int seed) {
    return hash(bytes, bytes.length, seed);
  }
  
  /**
   * Calculate a hash using bytes from 0 to <code>length</code>, and
   * the provided seed value
   * @param bytes input bytes
   * @param length length of the valid bytes to consider
   * @param seed seed value
   * @return hash value
   */
  public abstract int hash(byte[] bytes, int length, int seed);
  
  /**
   * Calculate a hash using all bytes from the input argument, and
   * a seed of -1.
   * @param bytes input bytes
   * @return hash value
   */
  public long hash64(byte[] bytes) {
    return hash64(bytes, bytes.length, seedInt());
  }
  
  /**
   * Calculate a hash using all bytes from the input argument,
   * and a provided seed value.
   * @param bytes input bytes
   * @param seed seed value
   * @return hash value
   */
  public long hash64(byte[] bytes, int seed) {
    return hash64(bytes, bytes.length, seed);
  }
  
  /**
   * Calculate a hash using bytes from 0 to <code>length</code>, and
   * the provided seed value
   * @param bytes input bytes
   * @param length length of the valid bytes to consider
   * @param seed seed value
   * @return hash value
   */
  public abstract long hash64(byte[] bytes, int length, int seed);
  
  public abstract int hash(Object o);
  public abstract long hash64(Object o);
}
