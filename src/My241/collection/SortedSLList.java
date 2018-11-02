package My241.collection;

public class SortedSLList {
	private int size;
	private SLListNode head;
	private SLListNode tail;
	public void clear(){
		head = tail = null;
	}
	public void insert(Object element){
		if (head==null){
			head=tail=new SLListNode(element,null);
			size++;
			return;
		}
		SLListNode newNode = new SLListNode(element,null);
		SLListNode ref = head;
		if (((Comparable)(ref.data)).compareTo(element)>=0){
			newNode.next = head;
			head = newNode;
			size++;
		}
		while (ref.next!=null){
			if (((Comparable)(ref.next.data)).compareTo(element)>=0){
				newNode.next=ref;
				ref.next=newNode;
				size++;
				return;
			}
			ref = ref.next;
		}
		tail = tail.next=newNode;
		size++;
	}
	public boolean isEmpty(){
		if (head == null) return true;
		return false;
	}
	public String toString(){
		String out = "The SortedSLL contains:\n";
		if (isEmpty())
			return out+"0 nodes.\n";
		else
			out+="head-->\t";
		SLListNode ref=head;
		int count = 0;
		while (ref.next!=null){
			out+=ref.data+"\t-->\t";
			if (count==5){
				out+="\n        ";
				count = 0;
			}
			else {
			count++;
			}
			ref = ref.next;
		}
		out+=ref.data+"\t-->null";
		return out;
	}
	public int size(){
		return size;
	}
	public boolean remove(Object element){
			if (head == null)
				return false;
			if (((Comparable)(head.data)).compareTo(element)==0){
				if (head==tail){
					head=tail=null;
				}
				else {
					head = head.next;
				}
				size--;
				return true;
			}
			if (head==tail){
				return false;
			}
			SLListNode ref = head;
			while (ref.next!=tail){
				if (((Comparable)(ref.next.data)).compareTo(element)==0){
					ref.next = ref.next.next;
					size--;
					return true;
				}
				ref = ref.next;
			}
			if (((Comparable)(tail.data)).compareTo(element)==0){
				tail = ref;
				tail.next=null;
				size--;
				return true;
			}
		return false;
		} 
	
	

}
