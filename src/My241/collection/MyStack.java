package My241.collection;
/**
 * Class to create a Stack Class.
 * @author Colin Crowe
 * @version 11.8.2.23
 */
public class MyStack {
	/**
	 * The top node of the stack.
	 */
	public SLListNode top;
	/**
	 * The constructor for the class. Defaults empty.
	 */
	public MyStack(){
		top = null;
	}
	/**
	 * Removes the top Node from the stack.
	 * @return Data of the top Node. 
	 */
	public Object pop(){
		if(top==null){
			return null;
		}
		Object temp = top.data;
		top = top.next;
		return temp;
	}
	/**
	 * Adds a Node to the top of the stack.
	 * @param element data to be stored in the stack.
	 */
	public void push(Object element){
		top = new SLListNode(element,top);
	}
	/**
	 * Fetches the data on the top of the stack.
	 * @return Top Node Data
	 */
	public Object top(){
		if (top==null){
			return null;
		}
		return top.data;
	}
	/**
	 * Creates a String representing the contents of the Stack.
	 */
	public String toString(){
		String out = "The Stack contains:\n ";
		if (top == null)
			return out+"0 nodes.\n";
		else
			out+="Top-->\t";
		SLListNode ref=top;
		int count = 0;
		while (ref.next!=null){
			out+=ref.data+"\t-->\t";
			if (count==4){
				out+="\n        ";
				count = 0;
			}
			else {
			count++;
			}
			ref = ref.next;
		}
		out+=ref.data+"\t-->Bottom";
		return out;
	}
}
