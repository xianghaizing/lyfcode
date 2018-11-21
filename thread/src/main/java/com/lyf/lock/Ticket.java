package com.lyf.lock;

/**
 * @Author lyf
 * @Date 2018/11/17 0017 14:13
 */
public class Ticket {
  private static int num = 10;
  private Object lock = new Object();

  // 类锁 static synchronized
  public static synchronized void buy01() {
    buy();
  }

  // 类锁 synchronized (xxx.class)
  public void buy02() {
    synchronized (MyLock.class){
      buy();
    }
  }

  // 对象锁 synchronized
  public synchronized void buy03() {
    buy();
  }

  // 对象锁 synchronized (this)
  public void buy04() {
    synchronized (this){
      buy();
    }
  }

  // 私有锁 synchronized (lock)
  public void buy05() {
    synchronized (lock){
      buy();
    }
  }

  public static void buy(){
    if (num > 0) {
      try {
        Thread.sleep((long) (Math.random() * 100));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(Thread.currentThread().getName()+": 抢到第" + num-- + "张票...");
    } else {
      System.out.println(Thread.currentThread().getName()+": 票已售罄...");
    }
  }

  public static void main(String[] args) {
    final Ticket ticket = new Ticket();
    for (int i = 0; i < 200; i++) { 
//      new Thread(() -> { ticket.buy01(); }).start();
//      new Thread(() -> { ticket.buy02(); }).start();
//      new Thread(() -> { ticket.buy03(); }).start();
//      new Thread(() -> { ticket.buy04(); }).start();
      new Thread(() -> { ticket.buy05(); }).start();
    }
  }
}
