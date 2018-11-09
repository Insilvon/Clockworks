package edu.My241.project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import edu.My241.collection.MyQueue;
import edu.My241.collection.MyStack;
import edu.My241.collection.MyVector;
import edu.My241.collection.SLListNode;
/**
 * Class which corresponds to Project 3.
 * @author Colin Crowe
 * @version 11.27.17
 */
public class Project3 {
	/**
	 * Test class which will read the assigned input file "COSC241_P3_Input.txt" and convert valid expressions to postfix expressions
	 *  while searching and reading Scientific Notation and floating point numbers.
	 *  
	 *  THIS PROJECT INCLUDES EXTRA CREDIT FUNCTIONALITY AND WILL SOLVE PROBLEMS THAT ARE UNSOLVABLE IN THE BASE PROJECT
	 */
	public static void test() throws IOException{
			//Part One, initialize the base garbage
			File input = new File("../COSC241_P3_Input.txt");
			Scanner read = new Scanner(input);
			File output = new File("../COSC241_P3_Output_crcrowe0.txt");
			BufferedWriter reader = new BufferedWriter(new FileWriter(output));
			MyStack stack = new MyStack();
			MyQueue queue = new MyQueue();
			boolean error = false;
			while (read.hasNextLine()) {
				String expression = read.nextLine();
				if (expression.length()==0)
					continue;
				reader.write("Original Infix:		"+expression);
				reader.newLine();
				if (debug(expression)){
					MyQueue expression2 = cleanup2(expression);
					SLListNode debugger = expression2.front;
					Boolean nono = true;
					String numbers = "0123456789eE.";
					String operators = "+-/%*";
					String issue = "+-/%*)";
					String goOn = "0123456789eE.";
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
					
					MyQueue tokenList = new MyQueue();
					SLListNode ref = expression2.front;
					while (ref!=null) {
						String current2 = ref.data+"";
						if (numbers.contains(current2.charAt(0)+"")) {
							queue.insertBack(current2);
						}
						else if (para.contains(current2.charAt(0)+"")) {
							if (current2.equals(")")){
								
								if (stackCheck(stack)){
									SLListNode ref2 = stack.top;
									while (ref2!=null){
										String ref2data = ref2.data+"";
										if (ref2data.equals("(")) {
											break;
										}
										else {
											Object temp = stack.pop();
											queue.insertBack(temp);
											ref2=ref2.next;
										}
									}
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
							if (stack.top==null) {
								stack.push(current2);
							}
							else {
								String check = stack.top.data+"";
								if (lowOp.contains(check)||check==null){
									if (lowOp.contains(current2)){
										Object temp = stack.pop();
										queue.insertBack(temp);
									}
									//low priority. Pile on. Push onto stack. //High priority incoming
									stack.push(current2);
								}
								else {
									check = stack.top.data+"";
									if (left.contains(check)){
										stack.push(current2);
									}
									else {
										Object temp = stack.pop();
										queue.insertBack(temp);
										if (stack.top==null) {
										stack.push(current2);
										}
										else{
											check = stack.top.data+"";
											if (lowOp.contains(check)||check==null){
												if (lowOp.contains(current2)){
													temp = stack.pop();
													queue.insertBack(temp);
												}
												//low priority. Pile on. Push onto stack. //High priority incoming
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
						queue.insertBack(temp);
						ref3 = stack.top;
					}
					
					MyQueue copy = new MyQueue();
					SLListNode ref4 = queue.front;
					
					while (ref4!=null){
						copy.insertBack(ref4.data);
						ref4 = ref4.next;
					}
					

					String solved = solve(copy)+"";
					String outputExpression = "";
					//ISSUE HERE
					while (queue.front!=null){
						outputExpression+=queue.front()+" ";
						queue.removeFront();
					}
					reader.write("Corresponding Postfix:	"+outputExpression);
					reader.newLine();
					reader.write("Evaluation Result:	= "+solved);
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
	private static float solve(MyQueue queue){
		String op;
		MyVector expression = new MyVector();
		SLListNode ref = queue.front;
		MyStack stack1 = new MyStack();
		MyStack stack2 = new MyStack();
		//convert to backwards stack
		while (ref!=null){
			Object temp = ref.data;
			String check = ref.data+"";
			//Converts any "xxxeyyyy" expressions to a value of x*(10^y)
			if (check.contains("e")||check.contains("E")){
				String a = "",b = "";
				int i,j;
				for (i = 0;check.charAt(i)!='e'&&check.charAt(i)!='E'; ++i){
					a += check.charAt(i); 
				}
				for (j = i+1;j<check.length();++j){
					b += check.charAt(j);
				}
				double left = Double.parseDouble(a);
				double right = Double.parseDouble(b);
				double temporary1 = Math.pow(10,right);
				float temporary2 = (float) (temporary1 * left);
				temp = temporary2;
			}
			expression.append(temp);
			queue.removeFront();
			ref = ref.next;
		}
		float answer = solveHelper(expression);
		return answer;
	}
	private static float solveHelper(MyVector a){
		MyStack placeholder = new MyStack();
		String numbers = "0123456789eE.";
		String catchMinus = "-";
		int size = a.size();
		for (int i = 0; i<size;++i){
			if (size<=1)
				return (float) a.elementAt(0);
			String temp = a.elementAt(i+1)+"";
			if (catchMinus.contains(temp.charAt(0)+"")){
				if (temp.length()!=1)
					continue;
			}
			if (numbers.contains(temp.charAt(0)+"")){
				continue;
			}
			else {
				
				float num1 = Float.parseFloat(a.elementAt(i-1)+"");
				float num2 = Float.parseFloat(a.elementAt(i)+"");
				float num3;
				String operator = a.elementAt(i+1)+"";
				switch (operator){
				case "+":
					num3 = num1+num2;
					a.replace(i+1, num3);
					a.removeAt(i-1);
					a.removeAt(i-1);
					i = -1;
					size=size-2;
					break;
				case "-":
					num3 = num1-num2;
					a.replace(i+1, num3);
					a.removeAt(i-1);
					a.removeAt(i-1);
					i = -1;
					size=size-2;
					break;
				case "*":
					num3 = num1*num2;
					a.replace(i+1, num3);
					a.removeAt(i-1);
					a.removeAt(i-1);
					i = -1;
					size=size-2;
					break;
				case "/":
					num3 = num1/num2;
					a.replace(i+1, num3);
					a.removeAt(i-1);
					a.removeAt(i-1);
					i = -1;
					size=size-2;
					break;
				case "%":
					num3 = num1%num2;
					a.replace(i+1, num3);
					a.removeAt(i-1);
					a.removeAt(i-1);
					i = -1;
					size=size-2;
					break;
				}
			}
		}
		return -1;
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
	private static MyQueue cleanup2(String expression) {
		MyQueue cleanup = new MyQueue();
		String numbers = "0123456789eE.";
		String goOn = "012345.6789eE";
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
				if (token.length()!=0) {
					cleanup.insertBack(token);
					token = "";
				}
				cleanup.insertBack(current);
			}
		}
		if (token.length()!=0)
			cleanup.insertBack(token);
		String tokenList = "";
		SLListNode ref = cleanup.front;
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
