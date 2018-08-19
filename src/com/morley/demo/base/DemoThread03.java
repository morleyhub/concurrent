package com.morley.demo.base;

/** 
 * 同步与异步
 * 两个线程分别调用同一个对象的不同方法，如果该方法都为同步方法，存在锁竞争，因为锁是针对当前对象的，两个线程操作的是同一个对象。
 * 如果操作的是非同步方法，不必等待锁。
 */
public class DemoThread03{
	
	//同步执行
	public synchronized void print1() {
		System.out.println(Thread.currentThread().getName()+">hello!");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//异步执行
	public void print2() {
		System.out.println(Thread.currentThread().getName()+">hello!");
	}
	
	public static void main(String[] args) {
		
		final DemoThread03 thread = new DemoThread03();

		new Thread(() -> thread.print1()).start();
		new Thread(() -> thread.print1()).start();
		new Thread(() -> thread.print2()).start();
	}
}
