package com.morley.demo.base;


/**
 * 脏读
 * 为了避免脏读，多个线程操作同一个对象的设值方法和取值的方法都要是同步方法，否则容易出现脏读。
 */
public class DemoThread04 {

	private String name = "张三";
	private String address = "大兴";

	public synchronized void setVal(String name, String address) {
		this.name = name;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.address = address;
		System.out.println("setValue最终结果：username = " + name + " , address = " + address);
	}

	public synchronized void getVal() {
		System.out.println("getValue方法得到：username = " + this.name + " , address = " + this.address);
	}

	public static void main(String[] args) throws Exception {

		final DemoThread04 dr = new DemoThread04();
		new Thread(() -> dr.setVal("李四", "昌平")).start();
		Thread.sleep(1000);
		dr.getVal();
	}

}
