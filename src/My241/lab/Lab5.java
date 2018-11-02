package My241.lab;
import java.util.Random;

import My241.collection.*;
public class Lab5 {
	public static void test(){
		MyVector a = randomVec();
		long first = System.currentTimeMillis();
		MySort.bubbleSort(a);
		long second = System.currentTimeMillis();
		
		System.out.println("Bubble Sort:");
		System.out.println("Time: "+(second-first));
		printOut(a);
		MyVector b = randomVec();
		first = System.currentTimeMillis();
		MySort.selectionSort(b);
		second = System.currentTimeMillis();
		
		System.out.println("Selection Sort:");
		System.out.println("Time: "+(second-first));
		printOut(b);
		MyVector c = randomVec();
		first = System.currentTimeMillis();
		MySort.quickSort(c,0,29999);
		second = System.currentTimeMillis();
		
		System.out.println("Quick Sort:");
		System.out.println("Time: "+(second-first));
		printOut(c);
		MyVector d = randomVec();
		first = System.currentTimeMillis();
		MySort.insertionSort(d,0,29999);
		second = System.currentTimeMillis();
		
		System.out.println("Insertion Sort:");
		System.out.println("Time: "+(second-first));
		printOut(d);
		MyVector e = randomVec();
		first = System.currentTimeMillis();
		MySort.shellSort(e);
		second = System.currentTimeMillis();
		
		System.out.println("Shell Sort:");
		System.out.println("Time: "+(second-first));
		printOut(e);
		
		MyVector f = randomVec();
		first = System.currentTimeMillis();
		MySort.selectionSort(f);
		second = System.currentTimeMillis();		
		System.out.println("Bubble Sort:");
		System.out.println("Time: "+(second-first));
		printOut(f);
		a.clear();

		
		
		
	}
	public static MyVector randomVec(){
		MyVector v = new MyVector();
		Random rand = new Random(20171019);
		for (int i = 0;i<30001;++i){
			v.append(rand.nextInt(99999));
		}
		return v;
	}
	public static void printOut(MyVector v){
		System.out.print(v.elementAt(0)+" ");
		System.out.print(v.elementAt(1)+" ");
		System.out.print(v.elementAt(2)+" ");
		System.out.print(v.elementAt(9999)+" ");
		System.out.print(v.elementAt(19999)+" ");
		System.out.print(v.elementAt(29999)+" ");
		System.out.println();
	}
}
