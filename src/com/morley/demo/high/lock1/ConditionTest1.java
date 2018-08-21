package com.morley.demo.high.lock1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest1 {

	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	public void run1(){
		try {
			lock.lock();
			System.out.println("当前线程：" + Thread.currentThread().getName() + "进入等待状态..");
			Thread.sleep(3000);
			System.out.println("当前线程：" + Thread.currentThread().getName() + "释放锁..");
			condition.await();	// Object wait,释放锁
			System.out.println("当前线程：" + Thread.currentThread().getName() +"继续执行...");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void run2(){
		try {
			lock.lock();
			System.out.println("当前线程：" + Thread.currentThread().getName() + "进入..");
			Thread.sleep(3000);
			System.out.println("当前线程：" + Thread.currentThread().getName() + "发出唤醒..");
			condition.signal();		//Object notify
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		
		final ConditionTest1 uc = new ConditionTest1();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				uc.run1();
			}
		}, "t1");
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				uc.run2();
			}
		}, "t2");
		
		t1.start();
		t2.start();
	}
	
	
	
}
