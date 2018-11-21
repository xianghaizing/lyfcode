package com.lyf.procon;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @Author lyf
 * @Date 2018/11/18 0018 21:09
 */
public class Food03 {
  // 创建阻塞队列
  private BlockingDeque<Integer> blockingDeque = new LinkedBlockingDeque<>(10);

  public void add() {
    try {
      blockingDeque.put(1);// 在队列尾部添加元素
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void remove() {
    try {
      blockingDeque.take();// 在队列头部删除元素
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}

class Producer03 extends Thread{
  private Food03 food03;

  public Producer03(Food03 proCon1) {
    this.food03 = proCon1;
  }

  @Override
  public void run() {
    while (true){
      food03.add();
      System.out.println(Thread.currentThread().getName() + "生产食物...");
    }
  }
}

class Consumer03 extends Thread{
  private Food03 food03;

  public Consumer03(Food03 proCon1) {
    this.food03 = proCon1;
  }

  @Override
  public void run() {
    while (true){
      food03.remove();
      System.out.println(Thread.currentThread().getName() + "消费食物...");
    }
  }
}

class Test03{
  public static void main(String[] args) throws InterruptedException {
    Food03 food03 = new Food03();
    for (int i = 0; i < 3; i++) {
      new Producer03(food03).start();
      new Consumer03(food03).start();
    }
    
    Thread.sleep(10);
    System.exit(0);// 强制终止程序
  }
}
