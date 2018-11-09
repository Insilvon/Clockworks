/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.My241.collection;

/**
 *
 * @author Colin
 */
public class HelpBot {
    public static void test(){
    	MySet A = new MySet();
    	MySet B = new MySet();
    	for (int i = 1;i<6;++i){
    		A.append(i);
    	}
    	for (int i = 1;i<11;i+=2){
    		B.append(i);
    	}
    	System.out.println("A:");
    	for (int i = 0;i<A.size;++i){
    		System.out.print(A.elementAt(i));
    	}
    	System.out.println();
    	System.out.println("B:");
    	for (int i = 0;i<B.size;++i){
    		System.out.print(B.elementAt(i));
    	}
    }
   
}
