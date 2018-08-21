package com.morley.demo.high.lock1;

import java.util.concurrent.locks.ReentrantLock;

/**
 * lock.getHoldCount()方法：只能在当前调用线程内部使用，不能再其他线程中使用
 * 那么我可以在run1方法里去调用run2方法，同时run1方法和run2方法都持有lock锁定即可 测试结果holdCount数递增
*/
public class HoldCountTest {
	// 重入锁
	private ReentrantLock lock = new ReentrantLock();
	public void run1() {
		try {
			lock.lock();
			System.out.println("进入run1方法，holdCount数为：" + lock.getHoldCount());
			// 调用run2方法
			run2();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	public void run2() {
		try {
			lock.lock();
			System.out.println("进入run2方法，holdCount数为：" + lock.getHoldCount());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		HoldCountTest thc = new HoldCountTest();
		thc.run1();
	}
}
