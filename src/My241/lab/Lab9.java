package My241.lab;
import java.util.Random;

import My241.collection.*;
public class Lab9 {
	public static final int MAX = 200;
	public static final int MIN = 100;
	public static void test(){
		MyBinarySearchTree tree = new MyBinarySearchTree();
		tree.insert(100);
		tree.insert(50);
		tree.insert(150);
		Random rand = new Random(System.currentTimeMillis());
		for (int i = 0;i<50;++i){
			tree.insert(rand.nextInt(MAX)+MIN);
		}
		tree.inOrderTraversal();
		System.out.println();
		tree.preorderTraversal();
		System.out.println();
		tree.postOrderTraversal();
	}
}
