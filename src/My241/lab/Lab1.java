/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package My241.lab;
import java.util.*;
/**
 * @author crcrowe0
 * @version 2017.9.13.4
 */
public class Lab1 {
    /**
     * @author crcrowe0
     * @param args 
     */
    public static void test(String[] args){
      Vector<Object> vector = new Vector<Object>();
      int primitiveInt = 241;
      Integer wrapperInt = new Integer(2017);
      String str = "Colin Crowe";
      vector.add(primitiveInt);
      vector.add(wrapperInt);
      vector.add(str);
      vector.add(2, new Integer(2178));
      System.out.println("The elements of vector: " + vector);
      System.out.println("The size of vector is: " + vector.size());  
      System.out.println("The elements at position 2 is: " + vector.elementAt(2));
      System.out.println("The first element of vector is: " + vector.firstElement());
      System.out.println("The last element of vector is: " + vector.lastElement());
      vector.removeElementAt(1);
      System.out.println("The elements of vector: " + vector); 
      System.out.println("The size of vector is: " + vector.size());
      System.out.println("The elements at position 2 is: " + vector.elementAt(2));
      System.out.println("The first element of vector is: " + vector.firstElement());
      System.out.println("The last element of vector is: " + vector.lastElement());    
      vector.clear();
      int length = args.length;
      System.out.println(length);
      for (int i = 0; i<length; ++i){
        vector.add(Integer.parseInt(args[i]));
      }
      System.out.println("The elements of vector: "+vector);
      for (int i = 0; i<vector.size();--i) {
          if (i%2 == 1)
          {
          vector.removeElementAt(i);
          }
          else {}
      }
      System.out.println("The even indices of vector: "+vector);
    }

}