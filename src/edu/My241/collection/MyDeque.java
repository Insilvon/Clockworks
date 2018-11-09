package edu.My241.collection;
/**
 * 
 * @author crcrowe0
 * @version 12.11.17
 */
public class MyDeque extends DLList{
	/**
	 * Creates a new Deque based of the DLList Structure
	 */
	public MyDeque(){
		super();
	}
	/**
	 * Object referring to the front node of the Deque.
	 * @return head.data
	 */
	public Object front(){
		if (head==null)
			return null;
		return head.data;
	}
	/**
	 * Object referring to the back node of the Deque
	 * @return tail.data
	 */
	public Object back() {
		if (head==null)
			return null;
		return tail.data;
	}
	/**
	 * Method to insert a new Object into the back of the Deque
	 * @param element object to be inserted
	 */
	public void insertBack(Object element){
		append(element);
	}
	/**
	 * Method to insert a new Object into the front of the Deque
	 * @param element object to be inserted
	 */
	public void insertFront(Object element){
		insert(element);
	}
	/**
	 * Method to remove the tail node of the Deque
	 * @return tail.data
	 */
	public Object removeBack(){
		if (head==null) return null;
		Object temp = tail.data;
		if (head==tail){
			head = tail = null;
			return temp;
		}
		tail = tail.prev;
		tail.next = null;
		return temp;
	}
	/**
	 * Method to remove the head node of the Deque
	 * @return head.data
	 */
	public Object removeFront(){
		if (head==null) return null;
		Object temp = head.data;
		if (head==tail){
			head = tail = null;
			return temp;
		}
		head = head.next;
		head.prev = null;
		return temp;
	}
	/**
	 * Method to check if the deque is empty.
	 * @return True = empty, False = not empty.
	 */
	public boolean isEmpty(){
		if (head==null) return true;
		return false;
	}
	/**
	 * Prints the current Deque out to a String.
	 */
	public String toString(){
		String out = "The Deque contains:\n";
		if (head==null){
			return out+"0 nodes.";
		}
		out += "head-->\t";
		DLListNode ref = head;
		int count = 0;
		while (ref!=tail){
			out+=ref.data+"\t<-->\t";
			if (count == 5){
				count = 0;
				out+="\n";
			}
			ref = ref.next;
			count++;
		}
		out+=tail.data+"\t<-- tail";
		return out;
	}
	/**
	 * Override method to block removal anywhere except front and back.
	 */
	public void remove(Object element){}
}
