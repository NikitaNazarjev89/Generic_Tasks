package org.example;



import org.apache.commons.lang3.ArrayUtils;

import java.awt.*;
import java.lang.reflect.Array;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Function;

/**
 * Для создания static inner class нужен объект родительского класса
 * Для создания inner class достаточно указать родительский класс
 * @param <T>
 */

public class MyList<T extends Number> implements Iterable<T>{

  private final int START_LEN = 15;
  public Object[] objArray = new Object[START_LEN];

  private int lastIndex;

  public MyList(){
    lastIndex = 0;
  }

  public int hashCode(){
    int result = lastIndex;
    if(result>0){
      for(Object d : objArray){
        if(d == null) continue;
        if(d instanceof Double){
          int current = (int)((Double) d * 31);
          result+=current;
        }else if(d instanceof Float){
          int current = (int)((Float) d * 31);
          result+=current;
        }else{
          result+=(int)d;
        }
      }
    }
    return result;
  }

  @Override
  public Iterator<T> iterator() {


    return new Iterator<T>() {
      int index = 0;
      @Override
      public boolean hasNext() {
        return objArray[index] == null? false : true ;
      }

      @Override
      public T next() {
        return (T)objArray[index++];
      }

      @Override
      public void remove() {
            MyList.this.remove(index--);
      }

    };
  }

  public String toString(){
    String result = "";
    for(int i = 0;i<lastIndex;i++){
      result+=objArray[i] + " ";
    }
    return result.trim();
  }

  public static void main(String[] args) {
    MyList <Integer> mlst = new MyList<>();
    mlst.add(1);
    mlst.add(2);
    mlst.add(3);
    Function f = new Function() {

      public Object apply(Object o) {
        return (Integer)o * 3;
      }
    };
      MyList<Integer> copy = mlst.map(f);
    System.out.println(copy.toString());
    System.out.println("________________");

  }


  @Override
  public boolean equals(Object obj) {
    if(this == obj){return true;}
    if(obj == null || this.getClass() != obj.getClass()){return false;}

    MyList<T> lst = (MyList<T>) obj;
    if(this.size()!=lst.size()){return false;}
    if(this.size()!=0 && lst.size()!=0 && this.get(0).getClass()!= lst.get(0).getClass()) {return false;}
    if(this.isEmpty()&&lst.isEmpty()){return false;}
    for(int i = 0; i < this.size();i++){
      // compare double
      if(lst.get(i) instanceof Double||lst.get(i) instanceof Float){
        double one = (Double) lst.get(i);
        double two = (Double) this.get(i);
        if(Double.compare(one,two)!=0){
          return false;
        }
      }
      //compare other
      else{
        int one = (int) lst.get(i);
        int two = (int) this.get(i);
        if(one!=two){
          return false;}
      }
    }
    return true;
  }

  public void add(T o) {
       if(objArray.length == lastIndex){
         resize();
       }
       objArray[lastIndex] = o;
       lastIndex++;
  }

  public MyList map(Function f) {
    MyList<T> copy = new MyList<>();
    for(int i = 0; i< lastIndex;i++){
      copy.add((T) f.apply(objArray[i]));
    }
    return copy;
  }




  public T get(int index) {
    if(index >= lastIndex || index < 0){
      throw new IndexOutOfBoundsException();
    }
    return (T)objArray[index];
  }

  private void resize() {
    objArray = Arrays.copyOf(objArray, objArray.length + (objArray.length/2));
  }

  public void remove(int index) {
    if(index >= lastIndex || index < 0){
      throw new IndexOutOfBoundsException();
    }
    Object[] first = Arrays.copyOfRange(objArray,0,index);
    Object[] second = Arrays.copyOfRange(objArray,index+1,objArray.length);
    objArray = ArrayUtils.addAll(first, second);
    lastIndex--;
  }
  public int size() {
    return lastIndex;
  }

  public boolean isEmpty() {
    if(lastIndex==0){return true;}
         return false;
  }

  public boolean contains(Object o){
    for(int i = 0;i < this.size();i++){
      if(o.equals(this.get(i))){
        return true;
      }
    }
    return false;
  }

  public Object[] toArray() {
    return Arrays.copyOf(objArray, lastIndex);
  }

}
