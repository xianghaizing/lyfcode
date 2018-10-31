package com.lyf.dataStructure;

/**
 * @Author lyf
 * @Date 2018/10/17
 */
public class ArrayNoSort {
  /**
   * 数组检索、插入、删除、打印操作
   * */
  
  private String[] strArray;
  private int length = 0;

  public ArrayNoSort(int max) {
    this.strArray = new String[max];
  }

  /**
   * 包含元素
   * @param target
   * @return
   */
  public int contain(String target){
    int index = -1;
    for (int i = 0; i < length; i++) {
      if (target==strArray[i]){
        index = i;
        break;
      }
    }
    return index;
  }

  /**
   * 插入
   * @param ele
   */
  public void insert(String ele){
    strArray[length] = ele;
    length ++;
  }

  /**
   * 删除
   * @param target
   * @return
   */
  public boolean delete(String target){
    int index = contain(target);
    if (index!=-1){
      for (int i = index; i < length-1; i++) {
        strArray[i] = strArray[i+1];
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
      System.out.print(strArray[i]+"\t");
    }
  }
  
  public static void main(String[] args){
    ArrayNoSort arrayNoSort = new ArrayNoSort(5);
    arrayNoSort.insert("a");
    arrayNoSort.insert("b");
    arrayNoSort.insert("c");
    arrayNoSort.insert("d");
    arrayNoSort.insert("e");
    arrayNoSort.display();

    System.out.println("\n================");
    arrayNoSort.delete("c");
    arrayNoSort.display();
    
  }
}
