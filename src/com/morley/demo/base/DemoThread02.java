package com.morley.demo.base;

/** 
 * 对象锁和类锁
 * (多个对象多把锁、相互不影响)
 * (访问静态变量则需要多个对象使用一把锁、那么需要将锁升级为类锁)
 *
 *  一个对象一把锁，synchronized是针对当前变量的，如果两个线程持有的是两个不同的对象，则不存在锁竞争。
 *  synchronized如果锁的是类方法或者类变量，则称为类锁。
 *
 */
public class DemoThread02{
	
	private static int count = 0;
	
	//如果是static变量会怎样?
	public synchronized static void add() {
		count++;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+">count="+count);
	}
	
	public static void main(String[] args) {
		
		/**
		    Synchronized是获得对象锁,如果作用在static类型上,则升级为类锁
		 */
		
		//内部类无法访问非final对象
		/**
		 * 为什么要用final修饰:
		 * 
		 * 内部类对象的生命周期会超过局部变量的生命周期。
		 * 局部变量的生命周期：当该方法被调用时，该方法中的局部变量在栈中被创建，当方法调用结束时，退栈，这些局部变量全部死亡。
		 * 而内部类对象生命周期与其它类一样：自创建一个匿名内部类对象，系统为该对象分配内存，直到没有引用变量指向分配给该对象的内存，它才会死亡(被JVM垃圾回收)。
		 * 所以完全可能出现的一种情况是：成员方法已调用结束，局部变量已死亡，但匿名内部类的对象仍然活着。
		 * 
		 * 匿名内部类对象可以访问同一个方法中被定义为final类型的局部变量。
		 * 定义为final后，编译程序的实现方法：对于匿名内部类对象要访问的所有final类型局部变量，都拷贝成为该对象中的一个数据成员。
		 * 这样，即使栈中局部变量已死亡，但被定义为final类型的局部变量的值永远不变，因而匿名内部类对象在局部变量死亡后，照样可以访问final类型的局部变量，因为它自己拷贝了一份，且与原局部变量的值始终一致。
		 */
		final DemoThread02 thread1 = new DemoThread02();
		final DemoThread02 thread2 = new DemoThread02();

		new Thread(() -> thread1.add()).start();
		new Thread(() -> thread2.add()).start();
	}
}
