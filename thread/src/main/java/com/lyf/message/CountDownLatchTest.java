package com.lyf.message;

import java.util.concurrent.CountDownLatch;

/**
 * @Author lyf
 * @Date 2018/11/19 0019 21:16
 */
public class CountDownLatchTest {
  
  public static void main(String[] args) throws InterruptedException {

    final CountDownLatch countDownLatch = new CountDownLatch(2);
    
    for (int i = 0; i < 2; i++) {
      new Thread(()->{
        System.out.println(Thread.currentThread().getName()+"开始执行...");
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"执行完毕...");
        countDownLatch.countDown();// 计数器减1
      }).start();
    }

    System.out.println("主线程等待...");
    countDownLatch.await();
    System.out.println("两个子线程执行完毕...");
    System.out.println("主线程继续执行...");
    
  }
}
