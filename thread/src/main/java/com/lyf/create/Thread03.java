package com.lyf.create;

import java.util.concurrent.Callable;

class Thread03 implements Callable<String> {

  @Override
  public String call() throws Exception {
    Thread.sleep(3000);
    System.out.println(Thread.currentThread().getName() + "执行...");
    return "hello";
  }
}