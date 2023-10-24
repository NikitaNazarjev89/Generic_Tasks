package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.function.Function;

public class MyListTest {
  @Test
  public void test_for_out_index() {
    MyList<Integer> lst = new MyList<>();
    lst.add(1);
    lst.add(2);
    lst.add(3);
    Assertions.assertThrows(IndexOutOfBoundsException.class,()-> lst.get(3));
    Assertions.assertThrows(IndexOutOfBoundsException.class, ()->lst.get(-1));
  }

  @Test
  public void test_for_size_list(){
    MyList<Short> sLst = new MyList<>();
    Assertions.assertEquals(0,sLst.size());
    MyList<Double> lst = new MyList<>();
    lst.add(24.4);
    lst.add(24.4);
    Assertions.assertEquals(2,lst.size());
  }

  @Test
  public void test_remove_myList(){
    MyList<Long> lst = new MyList<>();
    lst.add(12L);
    lst.add(140L);
    Assertions.assertEquals(2,lst.size());
    lst.remove(0);
    Assertions.assertEquals(140L,lst.get(0));
    Assertions.assertEquals(1,lst.size());
  }

  @Test
  public void test_empty(){
    MyList<Float> lst = new MyList<>();
    Assertions.assertTrue(lst.isEmpty());
    lst.add(1.2F);
    Assertions.assertFalse(lst.isEmpty());
  }

  @Test
  public void test_hashCode(){
    MyList<Double> lst1 = new MyList<>();
    MyList<Double> lst2 = new MyList<>();
    Assertions.assertEquals(lst1.hashCode(),lst2.hashCode());

    lst1.add(22.4);
    lst1.add(33.4);
    lst1.add(44.72);

    lst2.add(22.4);
    lst2.add(33.4);
    lst2.add(44.72);
    Assertions.assertEquals(lst1.hashCode(),lst2.hashCode());
  }

  @Test
  public void test_equals(){
    MyList<Double> lst1 = new MyList<>();
    MyList<Double> lst2 = new MyList<>();
    MyList<Float>  lst3 = new MyList<>();
    Assertions.assertFalse(lst1.equals(lst2));
    lst1.add(12.3);
    lst1.add(124.67);
    lst1.add(12123.3234);
    lst2.add(12.3);
    lst2.add(124.67);
    lst2.add(12123.3234);
    lst3.add(12.3F);
    lst3.add(124.67F);
    lst3.add(12123.3234F);
    Assertions.assertTrue(lst1.equals(lst2));
    lst1.remove(0);
    Assertions.assertFalse(lst1.equals(lst2));
    Assertions.assertFalse(lst2.equals(lst3));
  }

  @Test
  public void test_contains(){
    MyList<Double> lst = new MyList<>();
    lst.add(33.4);
    lst.add(123.4);
    Assertions.assertTrue(lst.contains(33.4));
    Assertions.assertFalse(lst.contains(32.4));
    Assertions.assertFalse(lst.contains(324));
    Assertions.assertFalse(lst.contains(32.4F));
  }

  @Test
  public void test_toString(){
    MyList<Integer> lst = new MyList<>();
    lst.add(1);
    lst.add(2);
    lst.add(3);
    String exected = "1 2 3";
    Assertions.assertEquals(exected,lst.toString());
  }

  @Test
  public void test_map(){

    MyList<Integer> lst = new MyList<>();
    lst.add(1);
    lst.add(2);
    lst.add(3);
    Function f = new Function() {
      @Override
      public Object apply(Object o) {
        return (Integer) o * 2;
      }
    };
    MyList<Integer> copy = lst.map(f);
    String expected = "2 4 6";
    Assertions.assertEquals(expected,copy.toString());
  }


}
