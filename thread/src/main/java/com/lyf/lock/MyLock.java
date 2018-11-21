package com.lyf.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author lyf
 * @Date 2018/11/17 0017 14:13
 */
public class MyLock {
  private static int num = 10;
  private Lock lock = new ReentrantLock();

  public void buy01() {
    lock.lock();// 获取锁
    try {
      if (num > 0) {
        Thread.sleep((long) (Math.random() * 100));
        System.out.println(Thread.currentThread().getName() + ": 抢到第" + num-- + "张票...");
      } else {
        System.out.println(Thread.currentThread().getName() + ": 票已售罄...");
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();// 释放锁
    }
  }

  public void buy02() {
    while (num>0 && lock.tryLock()){
      try {
        Thread.sleep((long) (Math.random() * 100));
        System.out.println(Thread.currentThread().getName() + ": 抢到第" + num-- + "张票...");
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        lock.unlock();// 释放锁
      }
    }
  }

  public void buy03() throws InterruptedException {
    lock.lockInterruptibly();// 获取锁
    try {
        Thread.sleep(2000);
        if (num > 0) {
          System.out.println(Thread.currentThread().getName() + ": 抢到第" + num-- + "张票...");
        } else {
          System.out.println(Thread.currentThread().getName() + ": 票已售罄...");
        }
    } finally {
      lock.unlock();// 释放锁
    }
  }
  
  
  
  
  public static void main(String[] args) throws InterruptedException {
    final MyLock myLock = new MyLock();
//    for (int i = 0; i < 200; i++) {
//      new Thread(() -> { myLock.buy01(); }, "线程"+i).start();
//      new Thread(() -> { myLock.buy02(); }, "线程" + i).start();
//    }
    
    Thread t1 = new Thread(()->{
      try {
        myLock.buy03();
      } catch (InterruptedException e) {
        System.out.println(Thread.currentThread().getName()+"被打断...");
      }
    }, "线程1");
    t1.start();// 启动线程
    Thread.sleep(1000);
    t1.interrupt();// 打断线程
  }
}
