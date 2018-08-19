package com.morley.demo.base;

/**
 * synchronized代码块 做到更细粒度的控制
 *
 */
public class DemoThread11 {

	public void execute() {
		try {
			System.out.println("线程" + Thread.currentThread().getName() + "执行不需要同步的内容1");
			Thread.sleep(2000);
			synchronized (this) {
				System.out.println("线程" + Thread.currentThread().getName() + "执行需要同步的内容2");
				Thread.sleep(2000);
			}
			Thread.sleep(2000);
			System.out.println("线程" + Thread.currentThread().getName() + "执行不需要同步的内容3");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		final DemoThread11 demo = new DemoThread11();
		new Thread(() -> demo.execute()).start();

		new Thread(() -> demo.execute()).start();
	}
}
