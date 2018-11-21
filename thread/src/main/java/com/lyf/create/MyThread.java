package com.lyf.create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author lyf
 * @Date 2018/11/17 0017 13:38
 */
public class MyThread {
  public static void main(String[] args) throws ExecutionException, InterruptedException {
    // 继承Thread类  
    new Thread01("线程1").start();
    // 实现Runnable接口
    new Thread(new Thread02(), "线程2").start();
    // 实现Callable接口
    FutureTask<String> futureTask = new FutureTask<>(new Thread03());
    futureTask.run();
    while (futureTask.isDone()) {
      System.out.println("结果: " + futureTask.get());
      return;
    }
  }
}






