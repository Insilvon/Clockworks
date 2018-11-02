package My241.collection;

import java.util.Stack;
/**
 * 
 * @author crcrowe0
 * @version 12.11.17
 */
public class MyExpressionTree extends MyBinaryTree{
	/**
	 * Empty constructor for a MyExpressionTree, creating an empty root MyBinaryTreeNode null.
	 */
	public MyExpressionTree(){
		root = null;
	}
	/**
	 * Constructor for a MyExpressionTree to set a MyBinaryTreeNode as the root.
	 * @param rt Root Node.
	 */
	public MyExpressionTree(MyBinaryTreeNode rt){
		root = rt;
	}
	/**
	 * Constructor for a MyExpressionTree to create a tree based off of an infix deque.
	 * @param list Deque expression. Must be a valid expression.
	 */
	public MyExpressionTree(MyDeque list){
		root = constructHelper(list);
	}
	private MyBinaryTreeNode constructHelper(MyDeque list){
		Stack<MyBinaryTreeNode> treeMaker = new Stack<>();
		DLListNode ref = list.head;
		String numbers = "1234567890eE.";
		while (ref!=null){
			char temp = (ref.data+"").charAt(0);
			if (numbers.contains(temp+"")){
				treeMaker.push(new MyBinaryTreeNode(ref.data));
			}
			else {
				MyBinaryTreeNode operator = new MyBinaryTreeNode(ref.data);
				operator.right=treeMaker.pop();
				operator.left=treeMaker.pop();
				treeMaker.push(operator);
			}
			ref = ref.next;
		}
		MyBinaryTreeNode answer = treeMaker.pop();
		return answer;
	}
	/**
	 * Takes your MyExpressionTree and evaluates the expression.
	 * @return Double value of the Tree.
	 */
	public double solve(){
		return solveHelper(root);
	}
	private double solveHelper(MyBinaryTreeNode rt){
		if (rt.right==null&&rt.left==null)//leaf node
			return Double.parseDouble(rt.data+"");
		switch(rt.data+""){
		case "+":
			return solveHelper(rt.left)+solveHelper(rt.right);
		case "-":
			return solveHelper(rt.left)-solveHelper(rt.right);
		case "*":
			return solveHelper(rt.left)*solveHelper(rt.right);
		case "/":
			return solveHelper(rt.left)/solveHelper(rt.right);
		case "%":
			return solveHelper(rt.left)%solveHelper(rt.right);
		}
		return (Double) 0.0;
	}
	/**
	 * Override method to create a string of printable traversal data.
	 * Follows preOrder - Node, Left Tree, Right Tree
	 */
	public String preorderTraversal(){
		return preOrderHelper(root);
	}
	private String preOrderHelper(MyBinaryTreeNode rt){
		String answer = "";
		if (rt==null) return "";
		answer += rt.data+" ";
		answer += preOrderHelper(rt.left);
		answer += preOrderHelper(rt.right);
		return answer;
	}
	/**
	 * Override method to create a string of printable traversal data.
	 * Follows postOrder - Left Tree, Right Tree, Node
	 */
	public String postOrderTraversal(){
		return postOrderHelper(root);
	}
	private String postOrderHelper(MyBinaryTreeNode rt){
		String answer = "";
		if (rt==null) return "";
		answer += postOrderHelper(rt.left);
		answer += postOrderHelper(rt.right);
		answer += rt.data+" ";
		return answer;
	}
	/**
	 * Override method to create a string of printable traversal data.
	 * Follows preOrder - Left Tree, Node, Right Tree
	 */
	public String inOrderTraversal(){
		String answer = inOrderHelper(root);
		return answer;
	}
	private String inOrderHelper(MyBinaryTreeNode rt){
		String answer = "";
		if (rt==null) return "";
		answer += inOrderHelper(rt.left);
		answer += rt.data+" ";
		answer += inOrderHelper(rt.right);
		return answer;
	}
	

}
