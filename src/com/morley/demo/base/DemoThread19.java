package com.morley.demo.base;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 使用CountDownLatch解决notify不释放锁、不实时的问题
 * 
 */
public class DemoThread19 {

	// 原子类
	private volatile List<String> list = new ArrayList<String>();
	private CountDownLatch countDownLatch = new CountDownLatch(5);

	public void put() {
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list.add("A");
			System.out.println("线程" + Thread.currentThread().getName() + "添加第" + i + "个元素");
			//计数减少,当5->0时,实时发送通知
			//说明：如果countDown没有被执行5次,则不会唤醒其他线程
			countDownLatch.countDown(); 
		}
	}

	public void get() {
		try {
			System.out.println("线程" + Thread.currentThread().getName() + "业务处理,发现有需要的数据没准备好,则发起等待");
			System.out.println("线程" + Thread.currentThread().getName() + "wait");
			countDownLatch.await(); //等待
			System.out.println("线程" + Thread.currentThread().getName() + "被唤醒");
			for (String s : list) {
				System.out.println("线程" + Thread.currentThread().getName() + "获取元素:" + s);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		final DemoThread19 demo = new DemoThread19();

		new Thread(new Runnable() {
			@Override
			public void run() {
				demo.get();
			}
		}, "t1").start();
		;

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		new Thread(new Runnable() {
			@Override
			public void run() {
				demo.put();
			}
		}, "t2").start();

	}

}
