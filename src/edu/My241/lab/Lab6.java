package edu.My241.lab;
import edu.My241.collection.*;

public class Lab6 {
	public static void test(){
		SLList simpleList = new SLList();
		int count = 0;
		for (int i = 1;i<31;i++){
			if (count == 0){
				simpleList.insert(i*i);
				count++;
			}
			else{
				simpleList.append(i*i);
				count = 0;
			}
		}
		String test = simpleList.toString();
		System.out.println(test);
		simpleList.remove(25);//Not removing for some reason. Strange.
		simpleList.remove(36);
		simpleList.remove(64);
		simpleList.remove(100);
		simpleList.remove(400);
		test = simpleList.toString();
		System.out.println(test);
		
		SortedSLList sortedList = new SortedSLList();
		for (int i = 1;i<31;i++){
			sortedList.insert(i*i);
		}
		test = sortedList.toString();
		System.out.println(test);
		sortedList.remove(1);
		sortedList.remove(25);
		sortedList.remove(36);
		sortedList.remove(64);
		sortedList.remove(144);
		sortedList.remove(400);
		sortedList.remove(900);
		test = sortedList.toString();
		System.out.println(test);
		
                sortedList.insert(1);
                sortedList.insert(64);
                sortedList.insert(400);
                sortedList.insert(900);
                test = sortedList.toString();
                System.out.println(test);
	}
}
