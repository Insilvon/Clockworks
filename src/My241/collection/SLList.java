package My241.collection;

public class SLList {
	private SLListNode head;
	private SLListNode tail;
	public SLList(){
		head = tail = null;
	}
	public void append(Object element){
		if (head == null){
			head = tail = new SLListNode(element,null);
			return;
		}
		tail = tail.next=new SLListNode(element,null);
	}
	public void insert(Object element){
		if (head==null){
			head = tail = new SLListNode(element,null);
		}
		else {
			head = new SLListNode(element,head);
		}
	}
	public boolean isEmpty(){
		if (head==null) return true;
		else return false;
	}
	public void clear(){
		head = tail = null;
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
			return true;
		}
		if (head==tail){
			return false;
		}
		SLListNode ref = head;
		while (ref.next!=tail){
			if (((Comparable)(ref.next.data)).compareTo(element)==0){
				ref.next = ref.next.next;
				return true;
			}
                        else{
			ref = ref.next;
                        }
		}
		if (((Comparable)(tail.data)).compareTo(element)==0){
			tail = ref;
			tail.next=null;
			return true;
		}
	return false;
	}
	public String toString(){
		String out = "The SLL contains:\n";
		if (isEmpty()){
			return out+"0 nodes.\n";
		}
		else{
			out+="head-->\t";
		}
		SLListNode ref=head;
		int count = 0;
		while (ref.next!=null){
			out+=ref.data+"\t-->\t";
			if (count==5){
				out+="\n        ";
				count = 0;
			}
			else{
			count++;
			}
			ref = ref.next;
		}
		out+=ref.data+"\t--> null";
		return out;
	}

}
