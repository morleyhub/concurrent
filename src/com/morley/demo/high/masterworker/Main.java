package com.morley.demo.high.masterworker;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		
		//创建多个Worker
		//Master master = new Master(new Worker(), 16);
		//根据服务器的可用线程数进行创建worker
		System.out.println(Runtime.getRuntime().availableProcessors());
		Master master = new Master(new Worker(), Runtime.getRuntime().availableProcessors()*2);
		
		//创建100个任务,并且提交给Master
		Random r = new Random();
		for(int i = 1; i <= 20; i++){
			Task t = new Task();
			t.setId(i);
			t.setPrice(r.nextInt(1000));
			master.submit(t);
		}
		
		//master启动所有的worker
		master.execute();
		long start = System.currentTimeMillis();
		
		//检测所有的worker是否都执行完毕
		while(true){
			if(master.isComplete()){
				long end = System.currentTimeMillis() - start;
				//获取master的汇总结果
				int priceResult = master.getResult();
				System.out.println("最终结果：" + priceResult + ", 执行时间：" + end);
				break;
			}
		}
		
	}
}
