package com.sandbox.synchronization;


public class Counter extends AbstractCounter {

	public void increment(){
		super.incr();
	}

	@Override
	public void display(long rv) {
		System.out.println(rv);
	}
}
