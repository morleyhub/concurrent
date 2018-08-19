package com.morley.demo.middle;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * ConcurrentLinkedQueue
 * 无阻赛、无锁、高性能、无界队列(直至内存耗尽)、线程安全，性能优于BlockingQueue、不允许null值
 * 使用CAS算法进行入队和出队操作
 */
public class DemoThread29 {
	
	public static void main(String[] args) {

		ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<Integer>();
		
		queue.add(1);
		queue.add(2); //add方法实际调用了offer方法
		
		//英文：offer 提供，给予; 提出，提议; 出价，开价; 表示愿意;
		//offer方法与add没有区别
		queue.offer(3);
		queue.offer(4);
		
		//queue.add(null); //不允许添加null元素
		System.out.println(queue);
		//英文：peek 偷看，窥视; 一瞥，看一眼
		System.out.println("[1]peek="+queue.peek()); //读取头元素,但是不移除
		System.out.println("[2]size="+queue.size()); //peek方法不会导致size改变
		
		//英文: poll 得到（一定数目的）选票; 对…进行调查; 修剪; 修剪;
		System.out.println("[3]poll="+queue.poll()); //读取头元素，并且移除
		System.out.println("[4]size="+queue.size()); //poll方法导致size改变
		
		System.out.println("[5]poll="+queue.poll()); 
		System.out.println("[6]poll="+queue.poll()); 
		System.out.println("[7]poll="+queue.poll()); 
		System.out.println("[8]size="+queue.size()); 
		
		System.out.println("peek="+queue.peek()); //队列为空, 读取头元素，返回null
		System.out.println("pool="+queue.poll()); //队列为空, 读取头元素并移除, 返回null
	}
}
