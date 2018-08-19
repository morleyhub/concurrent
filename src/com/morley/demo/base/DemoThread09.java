package com.morley.demo.base;

/**
 * 不要在线程中修改锁对象的引用，引用被改变会导致锁失效。
 * 在线程中如果线程A修改了锁对象，导致锁对象发生改变，那么会导致锁失效。
 */
public class DemoThread09 {

	private String lock = "lock handler";
	
	private void method(){
		synchronized (lock) {
			try {
				System.out.println("当前线程 : "  + Thread.currentThread().getName() + "开始");
				//锁的引用被改变,则其他线程可获得锁，导致并发问题
				lock = "change lock handler";
				Thread.sleep(2000);
				System.out.println("当前线程 : "  + Thread.currentThread().getName() + "结束");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
	
		final DemoThread09 changeLock = new DemoThread09();
		new Thread(() -> changeLock.method()).start();

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//由于锁的引用被改变，所以这个线程也进入到method方法内执行。
		new Thread(() ->changeLock.method()).start();
	}
	
}
