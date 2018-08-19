package com.morley.demo.base;

/**
 * 锁重入01
 * 锁重入是针对同一个线程操作同一个对象的不同的同步方法时，该线程一旦获得锁后，操作该对象的其他同步方法不必再重新等待获取锁。
 * 如果没有这个特性那就死锁了。
 */
public class DemoThread05{
	
	public synchronized void run1(){
		System.out.println(Thread.currentThread().getName()+">run1...");
		//调用同类中的synchronized方法不会引起死锁
		run2();
	}
	
	public synchronized void run2(){
		System.out.println(Thread.currentThread().getName()+">run2...");
	}
	
	public static void main(String[] args) {
		final DemoThread05 demoThread05 = new DemoThread05();
		new Thread(() -> demoThread05.run1()).start();
	}
}
