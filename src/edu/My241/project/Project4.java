package edu.My241.project;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import edu.My241.collection.*;
/**
 * Project 4: Convert Infix to Printable Expression Tree
 * @author crcrowe0
 * @version 12.11.17
 */
public class Project4 {
	/**
	 * Test class used to read the Input file. Scans line by line to start conversion.
	 * Input File COSC241_P4_Input.txt This project fulfills extra credit requirements!
	 */
	public static void test() throws IOException{
		File input = new File("../COSC241_P4_Input.txt");
		Scanner read = new Scanner(input);
		File output = new File("../COSC241_P4_Output_crcrowe0.txt");
		BufferedWriter reader = new BufferedWriter(new FileWriter(output));
		MyStack stack = new MyStack();
		MyDeque deque = new MyDeque();
		boolean error = false;
		while (read.hasNextLine()){
			String expression = read.nextLine();
			if (expression.length()==0)
				continue;
			reader.write("Original Infix:		  "+expression);
			reader.newLine();
			if (debug(expression)){
				MyDeque cleanup = expressionCleanup(expression);
				DLListNode debugger = cleanup.head;
				Boolean nono = true;
				String numbers = "0123456789eE.";
				String operators = "+-/%*";
				String issue = "+-/%*)";
				String token = "";
				String para = "()";
				String lowOp = "+-";
				String left = "(";
				int length = expression.length();
				while (debugger.next!=null){
					if (operators.contains(debugger.data+"")){
						if (issue.contains(debugger.next.data+"")){
							nono = false;
							break;
						}
					}
					debugger = debugger.next;
				}
				if (nono == false){
					reader.write("***Invalid Expression***");
					reader.newLine();
					reader.newLine();
					continue;
				}
				char current;
				
				DLListNode ref = cleanup.head;
				while (ref!=null){
					String current2 = ref.data+"";
					if (numbers.contains(current2.charAt(0)+"")){
						Object temp = current2;
						deque.insertBack(temp);
					}
					else if (para.contains(current2.charAt(0)+"")){
						if (current2.equals(")")){
							if (stackCheck(stack)){
								SLListNode ref2 = stack.top;
								while (ref2!=null){
									String ref2data = ref2.data+"";
									if (ref2data.equals("(")){
										break;
									}
									else {
										//WORK ON THIS PLEASE
										Object temp = stack.pop();
										deque.insertBack(temp);
										ref2=ref2.next;
									}
								}
								//Pops the parenthesis. Not required?
								stack.pop();
							}
							else {
								error = true;
								break;
							}
						}
						if (current2.equals("(")){
							stack.push(current2);
						}
					}
					else if (operators.contains(current2+"")){
						if (stack.top==null){
							stack.push(current2);
						}
						else {
							String check = stack.top.data+"";
							if (lowOp.contains(check)||check==null){
								if (lowOp.contains(current2)){
									MyBinaryTreeNode rightValue = new MyBinaryTreeNode(deque.tail.data);
									deque.removeBack();
									MyBinaryTreeNode leftValue = new MyBinaryTreeNode(deque.tail.data);
									deque.removeBack();
									MyBinaryTreeNode temp = new MyBinaryTreeNode(stack.pop(),leftValue,rightValue);
									deque.insertBack(temp);
								}
								stack.push(current2);
							}
							else {
								check = stack.top.data+"";
								if (left.contains(check)){
									stack.push(current2);
								}
								else {
									Object temp = stack.pop();
									deque.insertBack(temp);
									if (stack.top==null){
										stack.push(current2);
									}
									else {
										check = stack.top.data+"";
										if (lowOp.contains(check)||check==null){
											if (lowOp.contains(current2)){
												temp = stack.pop();
												deque.insertBack(temp);
											}
											stack.push(current2);
										}
									}
								}
							}
						}
					}
					ref = ref.next;
				}
				if (error == true){
					continue;
				}
				Object ref3 = stack.top;
				while (ref3!=null){
					Object temp = stack.pop();
					deque.insertBack(temp);
					ref3 = stack.top;
				}
				MyDeque copy = new MyDeque();
				DLListNode ref4 = deque.head;
				while (ref4!=null){
					copy.insertBack(ref4.data);
					ref4 = ref4.next;
				}
				
				MyExpressionTree answer = new MyExpressionTree(copy);
				
				
				reader.write("PreOrder Traversal:   "+answer.preorderTraversal());
				reader.newLine();
				reader.write("InOrder Traversal:    "+answer.inOrderTraversal());
				reader.newLine();
				reader.write("PostOrder Traversal:  "+answer.postOrderTraversal());
				reader.newLine();
				reader.write("Evaluation Result  =  "+answer.solve());
				reader.newLine();
				reader.newLine();
			}
			else {
				reader.write("***Invalid Expression***");
				reader.newLine();
				reader.newLine();
			}
		}
		reader.close();
	}

	private static boolean stackCheck(MyStack stack){
		SLListNode ref = stack.top;
		boolean check;
		while (ref!=null){
			String check2 = ref.data+"";
			if (check2.equals("(")){
				return true;
			}
			ref = ref.next;
		}
		return false;
	}
	private static MyDeque expressionCleanup(String expression){
		MyDeque cleanup = new MyDeque();
		String numbers = "0123456789eE.";
		String token = "";
		int count = 0;
		expression = expression.trim();
		int length = expression.length();
		for (int i = 0;i<length;++i){
			char current = expression.charAt(i);
			if (numbers.contains(current+"")){
				token += current;
			}
			else {
				if (token.length()!=0){
					cleanup.insertBack(token);
					token = "";
				}
				cleanup.insertBack(current);
			}
		}
		if (token.length()!=0)
			cleanup.insertBack(token);
		String tokenList = "";
		DLListNode ref = cleanup.head;
		while (ref!=null){
			tokenList+=ref.data+"";
			ref = ref.next;
		}
		return cleanup;
	}
	
	private static boolean debug(String expression) throws FileNotFoundException{
		//Let's count parenthesis, check for ()
				int length = expression.length();
				int rightPara = 0;
				int leftPara = 0;
				String acceptable = "0123456789.Ee+-*/%() ";
				boolean flag = false;
				for (int i = 0;i<length;++i){
					char temp = expression.charAt(i);
					if (acceptable.contains(temp+"")==false){
						return false;
					}
					if (expression.charAt(i)=='('){
						if ((i==length-1)||expression.charAt(i+1)==')'){//Might throw an error.
							return false;
						}
						leftPara++;
					}
					if (expression.charAt(i)==')'){
						rightPara++;
					}
				}
				if (leftPara!=rightPara){
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
					return false;
				}
				//If you made it this far, I think your expression is solid.
				return true;
			}
}

