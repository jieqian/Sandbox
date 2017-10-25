package com.sandbox.sizeof.com.carrotsearch.cache.prototype;

/**
 * Common base class for {@link Cache} implementations that need to adapt
 * {@code null} values (and potentially other such special values) before
 * passing them on to the underlying store.
 *
 * <p>Transparently replaces given {@code null} user values with an internal
 * {@link NullValue#INSTANCE}, if configured to support {@code null} values
 * (as indicated by {@link #isAllowNullValues()}.
 */
public abstract class AbstractValueAdaptingCache<K,V> implements Cache<K,V> {

  private final boolean allowNullValues;


  /**
   * Create an {@code AbstractValueAdaptingCache} with the given setting.
   * @param allowNullValues whether to allow for {@code null} values
   */
  protected AbstractValueAdaptingCache(boolean allowNullValues) {
    this.allowNullValues = allowNullValues;
  }


  /**
   * Return whether {@code null} values are allowed in this cache.
   */
  public final boolean isAllowNullValues() {
    return this.allowNullValues;
  }

  @Override
  public V get(K key) {
    V value = lookup(key);
    return value;
  }

  /**
   * Perform an actual lookup in the underlying store.
   * @param key the key whose associated value is to be returned
   * @return the raw store value for the key
   */
  protected abstract V lookup(K key);

}
