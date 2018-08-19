package com.morley.demo.base;

/**
 * 锁重入02 
 * (父子类可以锁重入)
 */
public class DemoThread06 {
    public static void main(String[] args) {
        new Thread(() -> {
            Child sub = new Child();
            sub.runChild();
        }).start();
    }
}

class Parent {
    public int i = 10;

    public synchronized void runParent() {
        try {
            i--;
            System.out.println("Parent>>>>i=" + i);
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Child extends Parent {

    public synchronized void runChild() {
        try {
            while (i > 0) {
                i--;
                System.out.println("Child>>>>i=" + i);
                Thread.sleep(100);
                //调用父类中的synchronized方法不会引起死锁
                this.runParent();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
