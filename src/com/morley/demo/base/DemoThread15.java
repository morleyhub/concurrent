package com.morley.demo.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * AtomicXXX类不能保证成员方法的原子
 */
public class DemoThread15 implements Runnable{
	
	//原子类
	private static AtomicInteger sum = new AtomicInteger(0);
	
	public static void add(){
		for(int i=0;i<10000;i++){
			sum.addAndGet(1);
		}
		if(sum.intValue()==100000){
			System.out.println(sum+"=ok");
		}else{
			System.out.println(sum+"=no");
		}
	}
	
	@Override
	public void run() {
		add();
	}
	
	public static void main(String[] args) {
		//10个线程调用，最终结果应该为100000
		ExecutorService es = Executors.newFixedThreadPool(10);
		for(int i=0;i<10;i++){
			es.submit(new DemoThread15());
		}
		es.shutdown();
	}

	
}
