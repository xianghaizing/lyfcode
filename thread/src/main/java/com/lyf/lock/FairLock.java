package com.lyf.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author lyf
 * @Date 2018/11/18 0018 18:46
 */
public class FairLock {

  private ReentrantLock lock = new ReentrantLock(true);

  public synchronized void A() {
    lock.lock();
    System.out.println(Thread.currentThread().getName() + "获得锁...");
    try {
      Thread.sleep(5000);
      System.out.println(Thread.currentThread().getName() + "执行...");
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  public static void main(String[] args) throws InterruptedException {
    FairLock fairLock = new FairLock();
    new Thread(() -> {
      fairLock.A();
    }).start();// 启动线程获得锁

    for (int i = 0; i < 5; i++) {
      new Thread(() -> {
        System.out.println(Thread.currentThread().getName() + "等待获得锁...");
        fairLock.A();
      }).start();
    }
  }
}
