package com.morley.demo.base;

/**
 * 在线程中修改了锁对象的属性,只要不修改引用则不会产生线程安全问题
 * 
 */
class Person {
	private String name;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "DemoThread10 [name=" + name + ", age=" + age + "]";
	}
}

public class DemoThread10 {

	private Person person = new Person();

	public void changeUser(String name, int age) {
		synchronized (person) {
			System.out.println("线程" + Thread.currentThread().getName() + "开始" + person);
			// 打开注释：引起对象引用发生变化,两个线程同时进入方法、导致线程安全问题
			//person = new Person();
			person.setAge(age);
			person.setName(name);
			System.out.println("线程" + Thread.currentThread().getName() + "修改为" + person);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("线程" + Thread.currentThread().getName() + "结束" + person);
		}
	}

	public static void main(String[] args) {
		final DemoThread10 thread10 = new DemoThread10();

		new Thread(() -> thread10.changeUser("小白", 99)).start();

		new Thread(() -> thread10.changeUser("小黑", 100)).start();
	}
}
