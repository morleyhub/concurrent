package com.morley.demo.base;
/**
 * 同步代码块(当前对象、类、任意对象)
 * 锁可分为当前对象锁，类锁，任意对象锁。
 * 相同类型的锁存在互斥，不同类型的锁互补干扰。
 */
public class DemoThread08 {

	/**
	 * 当前对象锁使用场景是多个线程持有的是同一个对象，该同步代码块才存在互斥
	 */
	public void run1() {
		synchronized (this) {
			try {
				System.out.println(Thread.currentThread().getName()+">当前对象锁..");
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 类锁不区分对象，类锁的代码块不管在相同对象还是不同对象在多线程之间都会存在互斥
	 */
	public void run2() {
		synchronized (DemoThread08.class) {
			try {
				System.out.println(Thread.currentThread().getName()+">类锁..");
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 不同线程持有的是任意代码块类的同一个对象，任意对象锁才存在互斥
	 */
	private Object objectLock = new Object();
	public void run3() {
		synchronized (objectLock) {
			try {
				System.out.println(Thread.currentThread().getName()+">任意对象锁..");
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	//测试方法
	public static void test(final int type){
		if(type==1){
			System.out.println("当前对象锁测试...");
		}else if(type==2){
			System.out.println("类锁测试...");
		}else{
			System.out.println("任意对象锁测试...");
		}
		final DemoThread08 demo1 = new DemoThread08();
		final DemoThread08 demo2 = new DemoThread08();

		Thread t1 = new Thread(() -> {
			if(type==1){
				demo1.run1();
			}else if(type==2){
				demo1.run2();
			}else{
				demo1.run3();
			}
		});


		Thread t2 = new Thread(() -> {
			if(type==1){
				demo1.run1();
			}else if(type==2){
				demo2.run2();
			}else{
				demo1.run3();
			}
		});
		t1.start();
		t2.start();
	}

	public static void main(String[] args) {
//		test(1);
//		test(2);
//		test(3);
		
		final DemoThread08 demo1 = new DemoThread08();
		final DemoThread08 demo2 = new DemoThread08();

		new Thread(() -> demo1.run2()).start();
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		new Thread(() -> demo2.run1()).start();
	}
}
