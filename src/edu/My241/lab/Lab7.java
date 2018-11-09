package edu.My241.lab;
import edu.My241.collection.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
/**
 * @version 2.20.11.8
 * @author Colin Crowe
 * Tests Stack and Queue
 */
public class Lab7 {
	/**
	 * Tests the usage of Stack and Queue, as well as a new Buffered Reader
	 * @throws IOException
	 */
	public static void test() throws IOException{
       
		Random rand = new Random(System.currentTimeMillis());
        MyStack stack = new MyStack();
        MyQueue queue = new MyQueue();
        
        for (int i = 0;i<60;++i){
        	int next = rand.nextInt();
        	stack.push(next);
        	queue.insertBack(next);
        }
        for (int j = 0;j<30;++j){
        	stack.pop();
        	queue.removeFront();
        }

        File output = new File("../COSC241_L7_Output_crcrowe0.txt");
        BufferedWriter reader = new BufferedWriter(new FileWriter(output));
       
        reader.write(stack.toString());
        reader.newLine();
        reader.write(queue.toString());
        
        reader.close();


	}
}
