package My241.collection;
/**
 * Core class for the Binary Tree Node
 * @author Colin Crowe
 * @version 12.6.17.8.54
 */
public class MyBinaryTreeNode implements Comparable<MyBinaryTreeNode>{
  /**
   * Object which holds the data for the node.
   */
  public Object data;
  /**
   * Reference to left connected node, if any.
   */
  public MyBinaryTreeNode left;
  /**
   * Reference to right connected node, if any.
   */
  public MyBinaryTreeNode right;
  /**
   * Constructor for the MBTN without references. 
   * @param d data to be stored.
   */
  public MyBinaryTreeNode(Object d){
	  data = d;
	  left = null;
	  right = null;
  }
  /**
   * Constructor for the MBTN with left and right references.
   * @param d data to be stored
   * @param l left reference node
   * @param r right reference node
   */
  public MyBinaryTreeNode(Object d, MyBinaryTreeNode l, MyBinaryTreeNode r){
	  data = d;
	  left = l;
	  right = r;
  }
  /**
   * Prints the node data in a string.
   */
  public String toString(){
	  return data.toString();
  }
  /**
   * Overriding compareTo method which compares the data values of two nodes.
   * Returns 0 for a match, 1 for initial node being greater, -1 for initial node being less.
   */
  public int compareTo(MyBinaryTreeNode target){
	  return (((Comparable)this.data).compareTo(target.data));
  }
  
}
