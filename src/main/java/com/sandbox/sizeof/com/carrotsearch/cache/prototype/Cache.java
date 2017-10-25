package com.sandbox.sizeof.com.carrotsearch.cache.prototype;

/**
 * Interface that defines common cache operations.
 *
 * <b>Note:</b> Due to the generic use of caching, it is recommended that
 * implementations allow storage of <tt>null</tt> values (for example to
 * cache methods that return {@code null}).
 */
public interface Cache<K,V> {

  /**
   * Return the cache name.
   */
  String getName();

  /**
   * Return the the underlying native cache provider.
   */
  Object getNativeCache();

  /**
   * Return the value to which this cache maps the specified key.
   * <p>Returns {@code null} if the cache contains no mapping for this key;
   * otherwise, the cached value (which may be {@code null} itself) will
   * be returned in a {@link ValueWrapper}.
   * @param key the key whose associated value is to be returned
   * @return the value to which this cache maps the specified key,
   * contained within a {@link ValueWrapper} which may also hold
   * a cached {@code null} value. A straight {@code null} being
   * returned means that the cache contains no mapping for this key.
   * @see #get(Object, Class)
   */
  V get(K key);

  /**
   * Associate the specified value with the specified key in this cache.
   * <p>If the cache previously contained a mapping for this key, the old
   * value is replaced by the specified value.
   * @param key the key with which the specified value is to be associated
   * @param value the value to be associated with the specified key
   */
  void put(K key, V value);

  /**
   * Atomically associate the specified value with the specified key in this cache
   * if it is not set already.
   * <p>This is equivalent to:
   * <pre><code>
   * Object existingValue = cache.get(key);
   * if (existingValue == null) {
   *     cache.put(key, value);
   *     return null;
   * } else {
   *     return existingValue;
   * }
   * </code></pre>
   * except that the action is performed atomically. While all out-of-the-box
   * {@link CacheManager} implementations are able to perform the put atomically,
   * the operation may also be implemented in two steps, e.g. with a check for
   * presence and a subsequent put, in a non-atomic way. Check the documentation
   * of the native cache implementation that you are using for more details.
   * @param key the key with which the specified value is to be associated
   * @param value the value to be associated with the specified key
   * @return the value to which this cache maps the specified key (which may be
   * {@code null} itself), or also {@code null} if the cache did not contain any
   * mapping for that key prior to this call. Returning {@code null} is therefore
   * an indicator that the given {@code value} has been associated with the key.
   */
  V putIfAbsent(K key, V value);

  /**
   * Evict the mapping for this key from this cache if it is present.
   * @param key the key whose mapping is to be removed from the cache
   */
  void evict(K key);

  /**
   * Remove all mappings from the cache.
   */
  void clear();
  
  /**
   * Estimate capacity of the cache (Unit: byte)
   * */
  void estimateCapacity(long capacity, MemoryUnit unit);

}
