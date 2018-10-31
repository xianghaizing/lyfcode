package com.lyf.dataStructure;

/**
 * @Author lyf
 * @Date 2018/10/17
 */
public class ArrayHasSort {
  /**
   * 数组检索、插入、删除、打印操作
   */

  private int[] intArray;
  private int length = 0;

  public ArrayHasSort(int max) {
    this.intArray = new int[max];
  }

  /**
   * 查找元素
   *
   * @param target
   * @return
   */
  public int find(int target) {
    int lowerBound = 0;// 最小下标
    int upperBound = length - 1;// 最大下标
    int curIn;// 当前下标

    if (upperBound < 0) {// 空数组,直接返回-1
      return -1;
    }

    while (true) {
      curIn = (lowerBound + upperBound) / 2;
      // 1个值
      if (target == intArray[curIn]) {
        return curIn;
        // 2两个值
      } else if (curIn == lowerBound) {
        if (target == intArray[upperBound]) {
          return upperBound;
        } else {
          return -1;
        }
        // 3个值
      } else {
        if (intArray[curIn] < target) {
          lowerBound = curIn;
        } else {
          upperBound = curIn;
        }
      }
    }
  }

  /**
   * 添加
   * @param ele
   */
  public void insert(int ele){
    int location = 0;
    for (; location < length; location++) {
      if (intArray[location]>ele) break;
    }

    for (int i = length; i > location; i--) {
      intArray[i] = intArray[i-1];
    }
    
    intArray[location] = ele;
    
    length ++;
  }
  /**
   * 删除
   * @param target
   * @return
   */
  public boolean delete(int target){
    int index = find(target);
    if (index!=-1){
      for (int i = index; i < length-1; i++) {
        intArray[i] = intArray[i+1];
      }
      length --;
      return true;
    }{
      return false;
    }
  }

  /**
   * 显示
   */
  public void display(){
    for (int i = 0; i < length; i++) {
      System.out.print(intArray[i]+"\t");
    }
  }
  
  public static void main(String[] args){
    ArrayHasSort arrayHasSort = new ArrayHasSort(5);
    arrayHasSort.insert(1);
    arrayHasSort.insert(2);
    arrayHasSort.insert(3);
    arrayHasSort.insert(4);
    arrayHasSort.insert(5);
    arrayHasSort.display();
    System.out.println("\n==================");
    
    arrayHasSort.delete(4);
    arrayHasSort.display();
  }
}
