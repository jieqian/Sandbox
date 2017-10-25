package com.sandbox.synchronization;

import java.util.concurrent.atomic.AtomicLong;

public abstract class AbstractCounter {
	
	protected AtomicLong count = new AtomicLong();
	
	protected long incr(){
//	protected static synchronized long incr(){ //同一时刻，对所有该类的实例来说，都只能有一个线程进来
		synchronized(MockCounter.class){ //无论放什么class对象，同一时刻，对所有该类的实例来说，都只有一个线程能进来
			long rv = count.incrementAndGet();
			display(rv);
			return rv;
		}
	}
	
	public abstract void display(long rv);
}
