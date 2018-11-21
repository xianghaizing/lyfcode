package com.lyf.message;

import java.util.concurrent.Semaphore;

/**
 * @Author lyf
 * @Date 2018/11/19 0019 22:01
 */
public class SemaphoreTest {
  public static void main(String[] args) {
    final int people = 8;// 8个人
    final int machine = 5;// 5个机器
    Semaphore semaphore = new Semaphore(machine);

    for (int i = 0; i < people; i++) {
      new Thread(()->{
        try {
          semaphore.acquire();
          System.out.println(Thread.currentThread().getName()+"获得一台机器,开始工作...");
          Thread.sleep((long) (3000*Math.random()));
          System.out.println(Thread.currentThread().getName()+"工作完成,离开机器...");
          semaphore.release();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }).start();
    }
  }
}
