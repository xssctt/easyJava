package com.example.easyjava.denmo;


import java.util.concurrent.atomic.AtomicInteger;


public class demo {
    public static void main(String[] args) throws InterruptedException {
        //两个线程交叉打印1-100
        //
        //1执行 唤醒2  1等待   2执行 唤醒1  2等待
        AtomicInteger a= new AtomicInteger(1);
        Object lock = new Object();

        Thread thread0 = new Thread(() -> {
            //到 100  zhongzhi
            //thread1
            synchronized (lock){
                while (a.get()<=100){
                    System.out.println(a.getAndIncrement()+Thread.currentThread().getName());
                    lock.notifyAll();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"Thread-01");


        Thread thread1 = new Thread(() -> {
            synchronized (lock){
                //thread2
                //到 100  zhongzhi
                while(a.get()<=100){
                    System.out.println(a.getAndIncrement()+Thread.currentThread().getName());
                    lock.notifyAll();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"Thread-02");


        thread0.start();
        thread1.start();


    }





}
