package com.morley.demo.high.executors;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPoolTest {

	public static void main(String[] args) {
		//core 核心线程数
		//max  最大线程数
		//timeout 超时时间
		//queue	等待队列
		
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			final int index = i;
			/*try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
			Runnable task = new Runnable() {
				public void run() {
					try {
						Thread.sleep(index*1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+">>"+index);
				}
			};
			cachedThreadPool.execute(task);
		}
	}

}

class Task implements Runnable{
	@Override
	public void run() {
		// TODO Auto-generated method stub
	}
}

class Caller implements Callable<String>{
	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
