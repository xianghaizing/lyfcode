package com.lyf.procon;

/**
 * @Author lyf
 * @Date 2018/11/18 0018 21:09
 */
public class Food01 {
  private static int num = 0; // 食物数量
  
  public synchronized void add() {
    if (num < 1) {// 判断食物是否充足
      num++;// 生产食物
      notifyAll();// 唤醒消费者
    } else {
      try {
        wait();// 生产者等待
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public synchronized void remove() {
    if (num > 0) {// 判断食物是否充足
      num--; // 消费食物
      notifyAll();// 唤醒生产者
    } else {
      try {
        wait();// 消费者等待
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

}

class Producer01 extends Thread{
  private Food01 food01;

  public Producer01(Food01 proCon1) {
    this.food01 = proCon1;
  }

  @Override
  public void run() {
    while (true){
      food01.add();
      System.out.println(Thread.currentThread().getName() + "生产食物...");
    }
  }
}

class Consumer01 extends Thread{
  private Food01 food01;

  public Consumer01(Food01 proCon1) {
    this.food01 = proCon1;
  }

  @Override
  public void run() {
    while (true){
      food01.remove();
      System.out.println(Thread.currentThread().getName() + "消费食物...");
    }
  }
}

class Test01{
  public static void main(String[] args) throws InterruptedException {
    Food01 food01 = new Food01();
    for (int i = 0; i < 3; i++) {
      new Producer01(food01).start();
      new Consumer01(food01).start();
    }
    
    Thread.sleep(10);
    System.exit(0);// 强制终止程序
  }
}
