package edu.My241.collection;
/**
 * Class to create A Binary Tree. 
 * @author Colin Crowe
 * @version 12.6.17.8.50
 */
public class MyBinaryTree {
	/**
	 * The apex value of the Tree.
	 */
	public MyBinaryTreeNode root;
	/**
	 * Clears (resets) the tree.
	 */
	public void clear(){
		root = null;
	}
	/**
	 * Tells if the tree is currently empty.
	 * @return true (empty) false (not empty)
	 */
	public boolean isEmpty(){
		if (root==null)
			return true;
		return false;
	}
	/**
	 * Returns number of nodes in the tree.
	 * @return Number of nodes in the tree.
	 */
	public int size(){
		return sizeHelper(root);
	}
	private int sizeHelper(MyBinaryTreeNode rt){
		if (rt==null) 
			return 0;
		else 
			return sizeHelper(rt.left)+sizeHelper(rt.right)+1;
	}
	/**
	 * Returns the height of the current tree.
	 * @return Height
	 */
	public int height(){
		return heightHelper(root, -1);
	}
	private int heightHelper(MyBinaryTreeNode rt, int ht){
		if (rt==null) 
			return ht;
		else
			return Math.max(heightHelper(rt.left, ht+1), heightHelper(rt.right,ht+1));
	}
	/**
	 * Traversal method which prints the parent node, the left tree, then the right tree.
	 */
	public String preorderTraversal(){
		preOrderHelper(root);
		return "";
	}
	private String preOrderHelper(MyBinaryTreeNode rt){
		if (rt==null) return "";
		System.out.print("\t"+rt.data);
		preOrderHelper(rt.left);
		preOrderHelper(rt.right);
		return "";
	}
	/**
	 * Traversal method which prints the left tree, then the right tree, then the parent node.
	 */
	public String postOrderTraversal(){
		postOrderHelper(root);
		return "";
	}
	private String postOrderHelper(MyBinaryTreeNode rt){
		if (rt==null) return "";
		postOrderHelper(rt.left);
		postOrderHelper(rt.right);
		System.out.print("\t"+rt.data);
		return "";
	}
	/**
	 * Traversal method which prints the left tree, the parent node, then the right tree.
	 */
	public String inOrderTraversal(){
		inOrderHelper(root);
		return "";
	}
	private String inOrderHelper(MyBinaryTreeNode rt){
		if (rt==null) return "";
		inOrderHelper(rt.left);
		System.out.print("\t"+rt.data);
		inOrderHelper(rt.right);
		return "";
	}
}
