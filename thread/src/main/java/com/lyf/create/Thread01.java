package com.lyf.create;

class Thread01 extends Thread {
  public Thread01(String name) {
    super(name);
  }

  @Override
  public void run() {
    System.out.println(Thread.currentThread().getName() + "执行...");
  }
}