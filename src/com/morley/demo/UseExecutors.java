package com.morley.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


public class UseExecutors {
	
	public static void main(String[] args) {
		
		/**
		 * 以下四种线程池，内部都是通过实例化ThreadPoolExecutor类来实现的
		 * 
		 * public ThreadPoolExecutor(
		 * 					  int corePoolSize,	//核心线程数,实例化时直接创建的线程数
                              int maximumPoolSize,	//最大线程数,
                              long keepAliveTime,	//空闲时间
                              TimeUnit unit,	//空闲时间单位
                              BlockingQueue<Runnable> workQueue,	//阻塞队列
                              ThreadFactory threadFactory,	//线程工厂
                              RejectedExecutionHandler handler)	//拒绝执行处理类
		 */
		
		/**
		 * 创建固定线程数量的线程池,当有任务提交到线程池中，当线程池存在空闲时则立即执行。
		 * 如果超过线程池的容量则进入阻塞队列等待
		 */
		ExecutorService executorService1 = Executors.newFixedThreadPool(10);
		
		/**
		 * 创建
		 */
		ExecutorService executorService2 = Executors.newCachedThreadPool();
		ExecutorService executorService3 = Executors.newSingleThreadExecutor();
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
	}
	
}
