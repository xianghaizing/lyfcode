package com.lyf.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author lyf
 * @Date 2018/11/18 0018 20:23
 */
public class SpinLock {
  // CAS操作
  AtomicReference<Thread> runThread = new AtomicReference<>();

  public void lock() {
    Thread cur = Thread.currentThread();// 获取当前线程
    // 如果需要运行线程标志为null,也就是说现在没有线程在执行,就执行当前线程
    // 如果需要运行线程标志不为null,说明现在有线程在执行,就自己个儿执行自己的
    while (!runThread.compareAndSet(null, cur)) {
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(Thread.currentThread().getName() + "在自旋中...");
    }
    try {
      System.out.println(Thread.currentThread().getName() + "开始执行...");
      Thread.sleep(2000);
      System.out.println(Thread.currentThread().getName() + "执行完毕...");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void unlock() {
    Thread cur = Thread.currentThread();
    runThread.compareAndSet(cur, null);
  }
  
  public static void main(String[] args) {
      SpinLock spinLock = new SpinLock();
      Thread t1 = new Thread(()->{
        spinLock.lock();
        try {
          Thread.sleep(2048);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        spinLock.unlock();
      });
      Thread t2 = new Thread(()->spinLock.lock());
      t1.start();
      t2.start();
  }
}
