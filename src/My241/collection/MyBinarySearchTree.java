package My241.collection;
/**
 * 
 * @author Colin Crowe
 * @version 12.6.17.8.47
 */
public class MyBinarySearchTree extends MyBinaryTree {
	/**
	 * Empty Constructor for for My Binary Search Tree
	 */
	public MyBinarySearchTree() {
		root = null;
	}
	/**
	 * Adds a new node to the tree, sorting it into the proper branch. 
	 * @param newItem Item to be added.
	 */
	public void insert(Object newItem){
		MyBinaryTreeNode newNode = new MyBinaryTreeNode(newItem);
		root = insertHelper(root, newNode);
	}
	private MyBinaryTreeNode insertHelper(MyBinaryTreeNode rt, MyBinaryTreeNode newNode){
		if (rt == null){
			rt = newNode;
			return rt;
		}
		if (newNode.compareTo(rt)<0){
			rt.left = insertHelper(rt.left,newNode);
		}
		else {
			rt.right = insertHelper(rt.right,newNode);
		}
		return rt;
	}
	/**
	 * Returns the max value in the tree.
	 * @return Max Value.
	 */
	public Object max(){
		if (root == null) return null;
		return maxHelper(root).data;
	}
	private MyBinaryTreeNode maxHelper(MyBinaryTreeNode rt){
		if (rt.right==null) return rt;
		else return maxHelper(rt.right);
	}
	/**
	 * Returns the lowest value in the tree.
	 * @return Lowest Value
	 */
	public Object min(){
		if (root == null) return null;
		else return minHelper(root);
	}
	private MyBinaryTreeNode minHelper(MyBinaryTreeNode rt){
		return (rt.left==null)?rt:minHelper(rt.left);
	}
	/**
	 * Searches the tree for the target node.
	 * @param target Node to be found.
	 * @return Null if not found, the node if found.
	 */
	public MyBinaryTreeNode find(Object target){
		return findHelper(root, new MyBinaryTreeNode(target));
	}
	private MyBinaryTreeNode findHelper(MyBinaryTreeNode rt, MyBinaryTreeNode targetNode){
		if (rt==null) return null;
		if (rt.compareTo(targetNode)>0){
			return findHelper(rt.left,targetNode);
		}
		if (rt.compareTo(targetNode)<0){
			return findHelper(rt.right,targetNode);
		}
		if (rt.compareTo(targetNode)==0){
			return rt;
		}
		return rt;
	}
	
	private MyBinaryTreeNode findParent(MyBinaryTreeNode rt, MyBinaryTreeNode target){
		if (target.compareTo(rt)<0){
			if (rt.left==null){
				return null;
			}
			else if (target.compareTo(rt.left)==0){
				return rt;
			}
			else {
				return findParent(rt.left,target);
			}
		}
		else {
			if (rt.right == null){
				return null;
			}
			else if (target.compareTo(rt.right)==0){
				return rt;
			}
			else {
				return findParent(rt.right,target);
			}
		}
	}
	
	private MyBinaryTreeNode successorParent(MyBinaryTreeNode rt){
		if (rt==null)
			return null;
		if (rt.right==null)
			return null;
		if (rt.right.left==null)
			return rt;
		MyBinaryTreeNode temp = rt.right;
		while (temp.left.left!=null){
			temp = temp.left;
		}
		return temp;
	}
	/**
	 * Removes a node from the tree.
	 * @param target Node to be removed.
	 */
	public void remove(Object target){
		if (root == null)
			return;
		MyBinaryTreeNode targetNode = new MyBinaryTreeNode(target);
		if (root.compareTo(targetNode)==0){
			MyBinaryTreeNode sp1 = successorParent(root);
			if (sp1==null&&root!=null){
				root = root.left;
			}
			else if (sp1 == root){
				root.right.left=root.left;
				root = root.right;
			}
			else {
				MyBinaryTreeNode rightChildOfSucc = sp1.left.right;
				sp1.left.left = root.left;
				sp1.left.right = root.right;
				root = sp1.left;
				sp1.left = rightChildOfSucc;
			}
			return;
		}
		MyBinaryTreeNode targetParent = findParent(root,targetNode);
		if (targetParent==null)
			return;
		if (targetParent.left.compareTo(targetNode)==0){
			MyBinaryTreeNode rNode = targetParent.left;
			MyBinaryTreeNode sp2 = successorParent(rNode);
			if (rNode.left==null&&rNode.right==null){
				targetParent.left=null;
			}
			else if(sp2 == rNode){
				sp2.right.left=rNode.left;
				targetParent.left=sp2.right;
			}
			else {
				MyBinaryTreeNode rightChildOfSucc = sp2.left.right;
				sp2.left.left = rNode.left;
				sp2.left.right = rNode.right;
				targetParent.left = sp2.left;
				sp2.left=rightChildOfSucc;
			}
			return;
		}
	}
	
	

}
