package edu.My241.lab;
import edu.My241.collection.*;
import static java.lang.System.nanoTime;
import java.util.*;
/**
 * Lab 3 Assignment: Searches and Sorts
 * @author crcrowe0
 *
 */
public class Lab3 {
		/**
		 * The Max value for the random number generator
		 */
        public static final int MAX = 899;
        /**
         * Min value for the random number generator
         */
        public static final int MIN = 100;
        
    /**
     * Assignment method which tests various sorts, searches, and vector usages.    
     */
	public static void test(){
		MyVector numVec = new MyVector();
                Random rand = new Random(nanoTime());
		for (int i = 0;i<700;++i){
                    numVec.append(rand.nextInt(MAX)+100);
			//numVec.append(MIN+(int)(Math.random()*MAX));
		}
                System.out.println();
                MySort.bubbleSort(numVec);
                System.out.println("Bubble Sorted Vector:");
                printOut(numVec);
                Scanner inp = new Scanner(System.in);
                int response = 0;
                System.out.println();
                System.out.println("Enter Search Query:");
                response = inp.nextInt();
                while (response>999||response<100){
                    System.out.println("Error, not within boundry. Please re-enter.");
                    Scanner inp2 = new Scanner(System.in);
                    response = inp2.nextInt();
                }
                
                System.out.println("Search was found at index: "+MySearch.linearSearchSorted(numVec,response));
                System.out.println();
                numVec.removeRange(50,450);
                numVec.reverse();
                MySort.selectionSort(numVec);
                System.out.println("Reversed, range removed, and sorted:");
                printOut(numVec);
                
                System.out.println("Enter Search Query:");
                response = inp.nextInt();
                while (response>999||response<100){
                    System.out.println("Error, not within boundry. Please re-enter.");
                    Scanner inp2 = new Scanner(System.in);
                    response = inp2.nextInt();
                }
                System.out.println("Search was found at index: "+MySearch.binarySearch(numVec,response));
	}
    /**    
     * A basic printout method which will print the given method.
     * @param a Method to be printed.
     */
	public static void printOut(MyVector a){
		int counter = 0;
		for (int i=0;i<a.size();++i){
			if (counter == 100){
				System.out.println();
				counter = 0;
			}
			System.out.print(a.elementAt(i)+" ");
			counter++;
		}
                System.out.println();
	}
	

}
