package edu.My241.collection;
/**
 * Class to create a Queue.
 * A collection of SLListNodes in a specific order.
 * @author Colin Crowe
 *
 */
public class MyQueue {
	/**
	 * The front Node in the queue.
	 */
	public SLListNode front;
	/**
	 * The rear Node in the queue.
	 */
	public SLListNode rear;
	/**
	 * Constructor. Defaults with empty queue.
	 */
	public MyQueue(){
		front = rear = null;
	}
	/**
	 * Removes all elements from the queue.
	 */
	public void clear(){
		front = rear = null;
	}
	/**
	 * Adds a value to the end of the queue.
	 * @param element Data to be inserted.
	 */
	public void insertBack(Object element){
		if (front==null){
			front=rear=new SLListNode(element,null);
			return;
		}
		rear = rear.next=new SLListNode(element,null);
	}
	/**
	 * Removes the front Node from the queue. 
	 * @return the Object data that was removed.
	 */
	public Object removeFront(){
		if (front==null){
			return null;
		}
		Object temp = front.data;
		if (front==rear){
			front=rear=null;
		}
		else {
			front = front.next;
		}
		return temp;
	}
	/**
	 * Returns the stored data of the front node.
	 * @return front node data
	 */
	public Object front(){
		if (front==null){
			return null;
		}
		return front.data;
	}
	/**
	 * String which displays the contents of the queue.
	 */
	public String toString(){
		String out = "The Queue contains:\n ";
		if (front == null)
			return out+"0 nodes.\n";
		else
			out+="Front-->\t";
		SLListNode ref=front;
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
		out+=ref.data+"\t-->Rear";
		return out;
	}
	
	
}