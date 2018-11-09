package edu.My241.lab;
import edu.My241.collection.*;

import java.io.*;
import java.util.Scanner;
/**
 * Class which corresponds to Lab 8.
 * @author Colin Crowe
 * @version 11.14.17
 */
public class Lab8 {
	/**
	 * Test class which reads the input file COSC241_L8_Input.txt line by lime to determine if
	 * the string is a palindrome.
	 * @throws IOException
	 */
	public static void test() throws IOException{
		MyDeque simpleDeque = new MyDeque();
		File test = new File("../COSC241_L8_Input.txt");
		Scanner read = new Scanner(test);
		File output = new File("../COSC241_L8_crcrowe0_Output.txt");
        BufferedWriter reader = new BufferedWriter(new FileWriter(output));
		while (read.hasNextLine()){
			String ref = read.nextLine();
			//Removes any spaces at the beginning
			while (ref.charAt(0)==' '){
				String ref2="";
				for (int j = 1;j<ref.length();++j){
					ref2 += ref.charAt(j);
				}
				ref = ref2;
			}
			//adds to deque
			for (int i = 0;i<ref.length();++i){
				if (ref.charAt(i)!=' ') {
					simpleDeque.append(ref.charAt(i));//Letter? Add it
				}
				else {
				//We find a space, then we start checking the rest. If there are spaces after...
				for (int k = i+1;k<ref.length();++k){
					if (ref.charAt(k)!=' ') {
						simpleDeque.append(ref.charAt(i));
						break;
					}
				}
				}
			}
			DLListNode ref2 = simpleDeque.head;
			String response = "";
			while (ref2!=null){
				response+=ref2.data;
				ref2=ref2.next;
			}
			System.out.println(simpleDeque.toString());
			if (palindromeCheck(response)==true){
				reader.write("\""+response+"\" is a palindrome.");
			}
			else {
				reader.write("\""+response+"\" is not a palindrome.");
			}
			reader.newLine();
			simpleDeque.clear();
		}
		reader.close();
		read.close();
		}
		/**
		 * Method which takes a string to determine if it is a palindrome.
		 * @param response String to examine.
		 * @return True - valid palindrome | False - invalid palindrome
		 * @throws IOException
		 */
		public static Boolean palindromeCheck(String response) throws IOException{
			if (response.length()==0){}
			else {
				int a = palindrome(response, 0, response.length()-1);
				if (a==1){
					return true;
				}
				else {
					return false;
				}
			}
			return false;
		}

		private static int palindrome(String string, int left, int right){
			int middle = (left+right)/2;
			while (left<=middle){
				if (string.charAt(left)==string.charAt(right)){
					left++;
					right--;
					palindrome(string, left, right);
				}
				else {
					return -1;
				}
			}
			return 1;
		}
}
