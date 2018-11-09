package edu.My241.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.My241.collection.MyQueue;
import edu.My241.collection.MyStack;
import edu.My241.collection.SLListNode;

public class Project3Alt {
	public static void test() throws FileNotFoundException{
			//Part One, initialize the base garbage
			File input = new File("../COSC241_P3_Input.txt");
			Scanner read = new Scanner(input);
			MyStack stack = new MyStack();
			MyQueue queue = new MyQueue();
			while (read.hasNextLine()) {
				String expression = read.nextLine();
				if (debug(expression)){
					expression = cleanup2(expression);
					System.out.println("Cleanup 2: "+expression);
					
					String numbers = "0123456789";
					String operators = "+-/%*";
					String goOn = "0123456789eE.";
					String token = "";
					String para = "()";
					String lowOp = "+-(";
					int length = expression.length();
					char current;
					
					MyQueue tokenList = new MyQueue();
					
					for (int i = 0;i<expression.length();++i){//loop through the entire expression
						char current2 = expression.charAt(i);
						if (numbers.contains(current2+"")){
							while (goOn.contains(expression.charAt(i+1)+"")){
								token+=current2;
								i++;
							}
							token+=expression.charAt(i);
							tokenList.insertBack(token);
							token = "";
						}
						else {
							tokenList.insertBack(current2);
						}
					}
					//The queue tokenList should now contain each token in the expression, with each token being one node.
					SLListNode ref = tokenList.front;
					while (ref!=null){
						String current3 = ref.data+"";
						if (numbers.contains(current3.charAt(0)+"")){
							queue.insertBack(current3);
						}
						if (operators.contains(current3.charAt(0)+"")){
							if (stack.top==null) {
								stack.push(current3);
							}
							else {
								String check = stack.top.data+"";
								if (lowOp.contains(check)||check==null){
									//low priority. Pile on. Push onto stack.
									stack.push(current3);
								}
								else {
									Object temp = stack.pop();
									queue.insertBack(temp);
									stack.push(current3);
								}
							}
						}
						if (para.contains(current3.charAt(0)+"")){
							switch(current3.charAt(0)){
								case '(':
									stack.push(current3);
									break;
								case ')':
									if (stackCheck(stack)){
										SLListNode ref2 = stack.top;
										while (ref2.data!=(Object)'('){
											Object temp = stack.pop();
											queue.insertBack(temp);
											ref2 = stack.top;
										}
										stack.pop();
										break;
									}
									else {
										System.out.println("Error: Improper parenthesis. Skipping.");
										break;
									}
								default:
									break;
							}
						}
						ref = ref.next;
					}
				}
				else {}
			}//end while
			//Clear the rest of the stack bub
			Object ref = stack.top;
			while (ref!=null){
				Object temp = stack.pop();
				queue.insertBack(temp);
				ref = stack.top;
			}
			String output = "";
			while (queue.front!=null){
				output+=queue.front()+"";
				queue.removeFront();
			}
			System.out.println(output);
	}
	private static boolean stackCheck(MyStack stack){
		SLListNode ref = stack.top;
		boolean check;
		while (ref!=null){
			if (ref.data==(Object)'('){
				return true;
			}
			ref = ref.next;
		}
		return false;
	}
	private static String cleanup2(String expression) {
		MyQueue cleanup = new MyQueue();
		String numbers = "0123456789";
		String goOn = "0123456789eE.";
		String token = "";
		int count = 0;
		expression = expression.trim();
		for (int i = 0;i<expression.length();++i){//loop through the entire expression
			char current = expression.charAt(i);
			if (numbers.contains(current+"")){
				while (goOn.contains(expression.charAt(i+1)+"")){
					token+=current;
					i++;
				}
				token+=expression.charAt(i);
				cleanup.insertBack(token);
				token = "";
			}
			else {
				cleanup.insertBack(current);
			}
		}
		String tokenList = "";
		SLListNode ref = cleanup.front;
		while (ref!=null){
			tokenList+=ref.data+"";
			ref = ref.next;
		}
		return tokenList;
	}
	private static boolean debug(String expression) throws FileNotFoundException{
		//Let's count parenthesis, check for ()
		int length = expression.length();
		int rightPara = 0;
		int leftPara = 0;
		boolean flag = false;
		for (int i = 0;i<length;++i){
			if (expression.charAt(i)=='('){
				if ((i==length-1)||expression.charAt(i+1)==')'){//Might throw an error.
					System.out.println("Left para fail!");
					return false;
				}
				leftPara++;
			}
			if (expression.charAt(i)==')'){
				rightPara++;
			}
		}
		if (leftPara!=rightPara){
			System.out.println("Equal paras fail");
			return false;
		}
		//Okay sure, so your parenthesis are good... but...
		//Let's check to make sure there's no weird operations at the beginning or end.
		char front = expression.charAt(0);
		char back = expression.charAt(length-1);
		//If it's anything that isn't a number, it's wrong.
		//But... the first could be a left para, last could be right para. 
		switch(front){
		case ' ':
		case '(':
		case '0':
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9':
			break;
		default:
			System.out.println("Starting value fail.");
			return false;
		}
		switch(back){
		case ' ':
		case '0':
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9':
		case ')':
			break;
		default:
			System.out.println("Ending value fail.");
			return false;
		}
		//If you made it this far, I think your expression is solid.
		return true;
	}
}
