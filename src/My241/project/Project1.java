/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package My241.project;
import My241.collection.*;
/**
 *
 * @author Colin
 */
public class Project1 {
    public static void test(){
        MySet primeNumSet = new MySet();
        MySet fibonacciNumSet = new MySet();
        //Generate and Add fibonacci numbers to fibonacciNumSet
        fibonacciNumSet.append(0);
        fibonacciNumSet.append(1);
        fibonacciNumSet.append(2);
        for (int i = 3;i<30;++i){
                int a = ((int)fibonacciNumSet.elementAt(i-1)+(int)fibonacciNumSet.elementAt(i-2));
                fibonacciNumSet.append(a);
        }
        System.out.println();        
        //Prime Number Setup
        int count = 0;
        for (int i = 0;count<30;++i){
            int check = 1;
            for (int k=2;k<i;k++){
                if (i%k==0){
                    check=0;
                    break;
                }
            }
            if(check==1){
                primeNumSet.add(i);
                count++;
            }
        }
        System.out.println("Fibonacci Number List:");
        printSet(fibonacciNumSet);
        
        System.out.println("Prime Number List:");
        printSet(primeNumSet);
        
        System.out.println("Intersection:");
        printSet(fibonacciNumSet.intersection(primeNumSet));
        
        System.out.println("Symmetric Difference");
        printSet((fibonacciNumSet.compliment(primeNumSet)).union(primeNumSet.compliment(fibonacciNumSet)));
        
        System.out.println("Union:");
        printSet(fibonacciNumSet.union(primeNumSet));
    }
    
    public static void printSet(MySet A){
    	System.out.print("     ");
    	for (int i = 0;i<A.size();++i){
    		System.out.print(A.elementAt(i)+" ");
    	}
    	System.out.println();
    }
}

