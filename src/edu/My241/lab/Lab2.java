/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.My241.lab;
import edu.My241.collection.MyVector;

/**
 * Lab2 Assignment: Implementing the Vector Collection
 * @author crcrowe0
 */
public class Lab2 {
    /**
     * Test method, main method for this lab. Creates new MyVector Object
     * To manipulate.
     */    
    public static void test(){
        MyVector v2 = new MyVector();
        //Add the fibonnaci numbers
        for (int i = 0; i<26; ++i){
            if (i<2){
                v2.append(i);
            }
            else {
                v2.append((int) v2.elementAt(i-1)+(int)v2.elementAt(i-2));
            }
        }
        
//print the original iii
        System.out.println("The elements of the vector are: ");
        int a = v2.size();
        for (int i = 0; i<a-1;++i){
            System.out.print(((int) v2.elementAt(i)) + " ");
        }
        MyVector vcopyoriginal = v2.clone();
        System.out.println();
//reverse the original + print it
        v2.reverse();
        System.out.println("The reverse is:");
        //print the reverse
        for (int i = 1; i<a;++i){
            System.out.print(((int)v2.elementAt(i)) + " ");
        }

//clone the reversed vector        
        MyVector vcopy = v2.clone();
        
        System.out.println();
        
//print the original
        System.out.println("The original elements are currently:");
        for (int i = 1;i<a;++i) {
            System.out.print(v2.elementAt(i)+" ");
        }
         System.out.println();
//remove odd indexes from original
        int b = v2.size();
        for (int i = b-1;i>0;--i) {
            if (i%2 == 1)
            {
                v2.removeAt(i);
            }
        }
        //print the original
        System.out.println("After removing odd indecies, the original elements are currently:");
        for (int i = 1;i<a/2;++i) {
            System.out.print(v2.elementAt(i)+" ");
        }
         System.out.println();
//reverse the clone
        vcopy.reverse();
//print the revesered clone (should be straight)
        System.out.println("The reversed-reversed clone is: ");
        for (int i =0;i<a-1;++i){
            System.out.print(vcopy.elementAt(i)+" ");
        }
        System.out.println();
        //merge the cloned to the original

        System.out.println("Merged is:");
        MyVector.merge(v2,vcopy);
        }
}
