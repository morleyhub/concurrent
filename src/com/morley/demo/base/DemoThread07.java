package com.morley.demo.base;

/**
 * 抛出异常释放锁
 * 当一个线程操作的同步方法内部抛出异常时，会自动释放当前持有的锁
 */
public class DemoThread07 {
	
	private int i = 0;
	
	public synchronized void run(){
		while(true){
			i++;
			System.out.println(Thread.currentThread().getName()+"-run>i="+i);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
//			int k = 10/0;

			if (i == 10) {
				throw new RuntimeException();
			}
		}
	}
	
	public synchronized void get(){
		System.out.println(Thread.currentThread().getName()+"-get>i="+i);
	}
	
	public static void main(String[] args) throws InterruptedException {
		final DemoThread07 demoThread07 = new DemoThread07();
		new Thread(() -> demoThread07.run()).start();
		//保证t1线程先执行
		Thread.sleep(1000);
		new Thread(() -> demoThread07.get()).start();
	}
}
