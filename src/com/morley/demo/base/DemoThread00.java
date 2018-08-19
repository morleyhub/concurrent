package com.morley.demo.base;

class User {
    private String name;
    private String pass;

    public User(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    /**
     * 如果不同步多线程会导致数据脏读的现象
     * @param name
     * @param pass
     */
    public synchronized void set(String name, String pass) {
        this.name = name;
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.pass = pass;
        System.out.println(Thread.currentThread().getName() + "-name=" + this.name + "pass=" + this.pass);
    }
}

/**
 * serlet是单例多线程的、Servlet的本身实现并不是一个单例实现、只是容器加载的时候只实例化一次，所造成的单例现象
 */
class UserServlet {

    private User user;

    public UserServlet() {
        user = new User("张三", "123456");
    }

    public void setPass(String name, String pass) {
        user.set(name, pass);
    }
}


public class DemoThread00 {

    public static void main(String[] args) {

        final UserServlet us = new UserServlet();

        new Thread(() -> us.setPass("李四", "777777")).start();

        new Thread(() -> us.setPass("王五", "888888")).start();
    }
}