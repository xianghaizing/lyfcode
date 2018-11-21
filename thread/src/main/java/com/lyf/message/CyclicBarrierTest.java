package com.lyf.message;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author lyf
 * @Date 2018/11/19 0019 21:51
 */
public class CyclicBarrierTest {
  
  public static void main(String[] args){
    CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

    for (int i = 0; i < 3; i++) {
      new Thread(()->{
        System.out.println(Thread.currentThread().getName()+"开始执行...");
        try {
          Thread.sleep((long) (3000*Math.random()));
          System.out.println(Thread.currentThread().getName()+"执行完毕...");
          cyclicBarrier.await();
        } catch (InterruptedException e) {
          e.printStackTrace();
        } catch (BrokenBarrierException e) {
          e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"所有线程执行完毕,开始执行其他任务...");
      }).start();
    }
  }
}
