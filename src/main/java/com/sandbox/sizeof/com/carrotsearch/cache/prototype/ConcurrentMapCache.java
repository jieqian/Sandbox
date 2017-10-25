package com.sandbox.sizeof.com.carrotsearch.cache.prototype;

import com.sandbox.sizeof.RamUsageEstimator;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Simple {@link org.springframework.cache.Cache} implementation based on the
 * core JDK {@code java.util.concurrent} package.
 *
 * <p>Useful for testing or simple caching scenarios, typically in combination
 * with {@link org.springframework.cache.support.SimpleCacheManager} or
 * dynamically through {@link ConcurrentMapCacheManager}.
 *
 * <p><b>Note:</b> As {@link ConcurrentHashMap} (the default implementation used)
 * does not allow for {@code null} values to be stored, this class will replace
 * them with a predefined internal object. This behavior can be changed through the
 * {@link #ConcurrentMapCache(String, ConcurrentMap, boolean)} constructor.
 */
public class ConcurrentMapCache<K,V> extends AbstractValueAdaptingCache<K,V> {

  private final String name;
  
  private final ConcurrentMap<K, V> store;

  public long capacity;
  
  private final AtomicLong currentAmountLoad = new AtomicLong(0);
  
  /**
   * 0 stand for false
   * 1 stand for true
   * 2 stand for cleared
   * */
  private AtomicInteger isCapable = new AtomicInteger(1);

  
  /**
   * Create a new ConcurrentMapCache with the specified name.
   * @param name the name of the cache
   */
  public ConcurrentMapCache(String name) {
    this(name, new ConcurrentHashMap<K, V>(256), true, MemoryUnit.MB.getValue());
  }

  
  /**
   * Create a new ConcurrentMapCache with the specified name.
   * @param name the name of the cache
   * @param allowNullValues whether to accept and convert {@code null}
   * values for this cache
   */
  public ConcurrentMapCache(String name, boolean allowNullValues) {
    this(name, new ConcurrentHashMap<K, V>(256), allowNullValues, MemoryUnit.MB.getValue());
  }

  
  /**
   * Create a new ConcurrentMapCache with the specified name and the
   * given internal {@link ConcurrentMap} to use.
   * @param name the name of the cache
   * @param store the ConcurrentMap to use as an internal store
   * @param allowNullValues whether to allow {@code null} values
   * (adapting them to an internal null holder value)
   */
  public ConcurrentMapCache(String name, ConcurrentMap<K, V> store, boolean allowNullValues, long capacity) {
    super(allowNullValues);
    this.name = name;
    this.store = store;
    this.capacity = capacity;
  }


  @Override
  public final String getName() {
    return this.name;
  }

  
  @Override
  public final ConcurrentMap<K, V> getNativeCache() {
    return this.store;
  }

  
  @Override
  protected V lookup(K key) {
    return this.store.get(key);
  }
  

  /**
   * @return the capacity
   */
  public long getCapacity() {
    return capacity;
  }
  

  /**
   * @param capacity the capacity to set
   */
  @Override
  public void estimateCapacity(long capacity, MemoryUnit unit) {
    this.capacity = capacity * unit.getValue();
  }

  
  private boolean isOverflow(V value){
    boolean rv = false;
    long valueSize = RamUsageEstimator.sizeOf(value);
    if( currentAmountLoad.addAndGet(valueSize) > capacity){
      rv = true;
    }
    
    return rv;
  }
  
  
  @Override
  public void put(K key, V value) {
    if(isCapable.get() > 0){
      isCapable.compareAndSet(2,1);
      this.store.put(key, value);
    }
    
    for(;;){
      if(isOverflow(value)){
        if(isCapable.compareAndSet(1, 0) || isCapable.compareAndSet(0, 0)){
          throw new IllegalArgumentException("the cache " + name + " is overflow");
        }
      }else{
        break;
      }
    }
  }
  

  @Override
  public V putIfAbsent(K key, V value) {
    V existing = null;
    if(isCapable.get() > 0){
      isCapable.compareAndSet(2,1);
      existing = this.store.putIfAbsent(key, value);
    }
    
    for(;;){
      if(isOverflow(value)){
        if(isCapable.compareAndSet(1, 0) || isCapable.compareAndSet(0, 0)){
          throw new IllegalArgumentException("the cache " + name + " is overflow");
        }
      }else{
        break;
      }
    }
    
    return existing;
  }

  
  @Override
  public void evict(K key) {
    long valueSize = RamUsageEstimator.sizeOf(store.get(key));
    currentAmountLoad.addAndGet(-1 * valueSize);
    this.store.remove(key);
  }

  
  @Override
  public void clear() {
    this.store.clear();
    currentAmountLoad.set(0);
    isCapable.set(2);
  }

}
