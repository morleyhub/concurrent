package com.morley.demo.high.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest1 {

	public static void main(String[] args) {
		// 线程池
		ExecutorService exec = Executors.newCachedThreadPool();
		// 只能5个线程同时访问（设置许可证的数量），默认是不公平的
		//final Semaphore semp = new Semaphore(1);
		// 强制公平
		final Semaphore semp = new Semaphore(2);
		// 模拟多个客户端并发访问
		for (int index = 0; index < 5; index++) {
			Runnable run = new Runnable() {
				public void run() {
					try {
						System.out.println(Thread.currentThread().getName() + "尝试获取许可证");
						// 获取许可
						semp.acquire();
						System.out.println(Thread.currentThread().getName() + "获取许可证");
						Thread.sleep(1000);
						// 访问完后，释放 ，如果屏蔽下面的语句，则在控制台只能打印5条记录，之后线程一直阻塞
						System.out.println(Thread.currentThread().getName() + "释放许可证");
						semp.release();
					} catch (InterruptedException e) {
					}
				}
			};
			exec.execute(run);
		}
		// 退出线程池
		exec.shutdown();
	}

}
