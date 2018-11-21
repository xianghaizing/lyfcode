package com.lyf.lock;

/**
 * @Author lyf
 * @Date 2018/11/18 0018 14:30
 */
public class RepeatLock {
  
  public synchronized void A(){
    System.out.println("进入A...");
    B();
  }
  
  public synchronized void B(){
    System.out.println("进入B...");
  }
  
  public static void main(String[] args){
    RepeatLock repeatLock = new RepeatLock();
    for (int i = 0; i < 5; i++) {
      new Thread(()->{repeatLock.A();}).start();
    }
  }
}
