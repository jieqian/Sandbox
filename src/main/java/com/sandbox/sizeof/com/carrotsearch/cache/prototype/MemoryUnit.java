package com.sandbox.sizeof.com.carrotsearch.cache.prototype;

public enum MemoryUnit {
  KB(1024),MB(1024*1024),GB(1024*1024*1024);
  long value;
  MemoryUnit(long value){
    this.value = value;
  }
  
  long getValue(){
    return this.value;
  }
}
