package com.lyf.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author lyf
 * @Date 2018/11/18 0018 10:30
 */
public class MyReadWriteLock {

  private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
  private int num = 0;

  public void readFile() {
    rwl.readLock().lock();
    try {
      System.out.println(Thread.currentThread().getName() + "执行读操作...");
      Thread.sleep((long) (Math.random() * 1000));
      System.out.println(Thread.currentThread().getName() + "读操作完成...");
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      rwl.readLock().unlock();
    }
  }


  public void writeFile() {
    rwl.writeLock().lock();
    System.out.println(Thread.currentThread().getName() + "执行写操作...");
    try {
      num++;
      Thread.sleep((long) (Math.random() * 1000));
      System.out.println(Thread.currentThread().getName() + "写操作完成...num: " + num);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      rwl.writeLock().unlock();
    }

  }

  public static void main(String[] args) {

    MyReadWriteLock myReadWriteLock = new MyReadWriteLock();
    for (int i = 0; i < 5; i++) {
      new Thread(()->{myReadWriteLock.readFile();}).start();// 读语句1
      new Thread(()->{myReadWriteLock.readFile();}).start();// 读语句2
      new Thread(() -> { myReadWriteLock.writeFile(); }).start();// 写语句
    }
  }
}
