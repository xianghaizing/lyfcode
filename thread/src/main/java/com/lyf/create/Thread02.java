package com.lyf.create;

class Thread02 implements Runnable {

  @Override
  public void run() {
    System.out.println(Thread.currentThread().getName() + "执行...");
  }
}