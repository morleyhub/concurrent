package com.morley.demo.base;

import java.util.ArrayList;
import java.util.List;

/**
 * 不使用wait/notify的通信
 */
public class DemoThread13{
	
	private List<String> list = new ArrayList<String>();
	private volatile boolean canGet = false;
	
	public void put(){
		for(int i=0;i<10;i++){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list.add("A");
			System.out.println("线程"+Thread.currentThread().getName()+"添加第"+i+"个元素");
			if(i==5){
				//循环到第次则通知其他线程开始获取数据进行处理
				canGet = true;
				System.out.println("线程"+Thread.currentThread().getName()+"发出通知");
			}
		}
	}
	
	public void get(){
		while(true){
			if(canGet){
				for(String s:list){
					System.out.println("线程"+Thread.currentThread().getName()+"获取元素:"+s);
				}
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		final DemoThread13 demo = new DemoThread13();
		
		new Thread(() -> demo.put()).start();
		
		new Thread(() -> demo.get()).start();
		
	}


}
