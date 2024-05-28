package com.example.easyjava.denmo;


//写一个线程安全 的 单例模式
//双锁
//
public class Demosingleton {
    //懒汉
    private static volatile Demosingleton demosingleton;

    public Demosingleton() {
    }
    //双锁
    public static Demosingleton getDemosingleton() {
        if(demosingleton != null){
            synchronized (Demosingleton.class){
                if (demosingleton != null){
                    demosingleton=new Demosingleton();
                }
            }
        }
        return demosingleton;
    }

    public static void setDemosingleton(Demosingleton demosingleton) {
        Demosingleton.demosingleton = demosingleton;
    }
}
