package edu.My241.lab;
import java.util.*;
/**
 * 
 * @author crcrowe0
 * @version 10.10.17.6
 *
 */
public class Lab4 {
	/**
	 * Test class for checking palindrome inputs.
	 */
	public static void test() {
		Scanner inp = new Scanner(System.in);
		String response;
		System.out.println("Please input your string.");
		response = inp.nextLine();
		if (response.length()==0){}
		else {
			int a = palindrome(response, 0, response.length()-1);
			if (a==1)
				System.out.println("You have entered a palindrome.");
			else {
				System.out.println("You have not entered a palindrome.");}
			}
		inp.close();
	}
	/**
	 * Takes string input and starting index locations to determine if values contained within
	 * create a palindrome. Uses recursion.
	 * @param string String input to be checked. 
	 * @param left Index of the leftmost value to start with in the string.
	 * @param right Index of the rightmost value to start with in the string.
	 * @return 1 for Palindrome found, -1 for Palindrome not found.
	 */
	public static int palindrome(String string, int left, int right){
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
