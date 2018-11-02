package My241.collection;

public class DLList {
	public DLListNode head;
	public DLListNode tail;
	public DLList(){
		head=tail=null;
	}
	public void clear(){
		head = tail = null;
	}
	public void append(Object element){
		if (head==null){
			head=tail=new DLListNode(element,null,null);
			return;
		}
		tail.next = new DLListNode(element,tail,null);
		tail = tail.next;
	}
	public void insert(Object element){
		if (head==null){
			head=tail=new DLListNode(element,null,null);
		}
		else {
			head = new DLListNode(element,null,head);
			head.next.prev = head;
		}
	}
	public void remove(Object element){
		if (head==null)return;
		if (((Comparable)(head.data)).compareTo(element)==0){
			if (head==tail) {
				head = tail = null;
			}
			else {
				head = head.next;
				head.prev = null;
			}
			return;
		}
		if (head == tail)return;
		DLListNode ref = head.next;
		while (ref != tail){
			if (((Comparable)(ref.data)).compareTo(element)==0){
				ref.prev.next=ref.next;
				ref.next.prev=ref.prev;
				return;
			}
			ref = ref.next;
		}
		if (((Comparable)(tail.data)).compareTo(element)==0){
			tail = tail.prev;
			tail.next = null;
		}
	}
	public String toString(){
		String out = "The DLList contains:\n";
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
	
}
