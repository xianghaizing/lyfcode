package com.lyf.procon;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author lyf
 * @Date 2018/11/18 0018 21:09
 */
public class Food02 {
  private static int num = 0; // 食物数量
  
  private ReentrantLock lock;
  private Condition producer;// 生产者状态
  private Condition consumer;// 消费者状态

  public Food02() {
    lock = new ReentrantLock();
    producer = lock.newCondition();
    consumer = lock.newCondition();
  }

  public void add() {
    lock.lock();// 获得锁
    try {
      if (num < 1) {
        num++;
        consumer.signalAll();// 通知消费者
      }else{
        producer.await();// 生产者等待
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();// 释放锁
    }
  }

  public void remove() {
    lock.lock();// 获得锁
    try {
      if (num > 0) {
        num--;
        producer.signalAll();// 通知生产者
      }else{
        consumer.await();// 消费者等待
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();// 释放锁
    }
  }
}

class Producer02 extends Thread{
  private Food02 food02;

  public Producer02(Food02 proCon1) {
    this.food02 = proCon1;
  }

  @Override
  public void run() {
    while (true){
      food02.add();
      System.out.println(Thread.currentThread().getName() + "生产食物...");
    }
  }
}

class Consumer02 extends Thread{
  private Food02 food02;

  public Consumer02(Food02 proCon1) {
    this.food02 = proCon1;
  }

  @Override
  public void run() {
    while (true){
      food02.remove();
      System.out.println(Thread.currentThread().getName() + "消费食物...");
    }
  }
}

class Test02{
  public static void main(String[] args) throws InterruptedException {
    Food02 food02 = new Food02();
    for (int i = 0; i < 3; i++) {
      new Producer02(food02).start();
      new Consumer02(food02).start();
    }
    
    Thread.sleep(10);
    System.exit(0);// 强制终止程序
  }
}
