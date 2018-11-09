package edu.My241.collection;

public class DLListNode {
	public Object data;
	public DLListNode prev;
	public DLListNode next;
	public DLListNode(Object d, DLListNode p, DLListNode n){
		data = d;
		prev = p;
		next = n;
	}
	public String toString(DLListNode ref){
		String out = "Node contains\t";
		out += ref.data;
		return out;
	}
}
