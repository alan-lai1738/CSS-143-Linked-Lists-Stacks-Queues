// Alan Lai
// 11/7/2019
// CSS 143
// Linked Lists/Stacks/Queues 
/* This is the List.java class. It uses nodes and has only one instance variable, which is the 
 * head of the linked list. Every node only has one pointer, which goes to the next node.
 * The list has a function to insert and remove node pointers, which all store objects and a single pointer.
 * There are many different situations for lists when adding/removing to the data.
 * I throw a ListException if there is an invalid index, or if the index is bigger than the size of list.
 */
package linkedlists;

public class List {
	private Node head = null;	// Head is null by default. This also means the list is empty.
	
	public void insert(Object next, int index) throws ListException {
		// Empty List Situations
		if(isEmpty() && index !=0) throw new ListException("The list is empty right now. Invalid index");
		// If the List is empty and the index is 1 or more, throw a List Exception.
		else if(head==null) {
		// If list is empty, we'll add the data as the head data and make null the pointer. (which was head)
		head = new Node (next, head);
		return;}
		
		// List with one Node 
		if(size()==1 && index > 1) throw new ListException("The list only has one index: Please add to the beginning or end of list.");
		// If the list is size of 1 and the index is greater than 1, that is an invalid index.
		else if(size()==1 && index == 0) {
		// If the list is size of 1 and the index input is 0, the head is now the input, and the pointer is the previous head.
			Node prevHead = head;
			head = new Node (next, prevHead);
			return;
		}
		// If size is 1 and index input is 1, put new node with new data as head.next.
		else if(size()==1 && index == 1) {
			head.next = new Node (next, null);
			return;
		}
		
		// List situations with 2 or more nodes.
		if(index > size()) {
			// If the index is greater than the list size, it is invalid.
			throw new ListException(index + " is an Invalid index, the max index is " + size());
		}
		if(index == 0) {
			// If the index is 0, replace the head with new data and pointer to previous head.
			Node prevHead = head;
			head = new Node (next, prevHead);
		}
		else {
			// nodeToUpdate = moveToNode moves to the index.
			Node nodeToUpdate = moveToNode(index);
			Node nodeToMove = nodeToUpdate.next;
			nodeToUpdate.next = new Node(next, nodeToMove);
			// Update the pointer of nodeToUpdate to a Node with the data, and the pointer to the
			// previous pointer. Basically "shifting it right" 
		}
	}

	public Object remove(int index) throws ListException {
		if(isEmpty()) throw new ListException("You can't remove from an empty list.");
		// If the list is empty, you can't remove. So throw an exception.
		else if(size()==1 && index > 0) throw new ListException("Invalid Index.");
		// If the list has one object and inputed index > 0, invalid.
		else if(size()==1 && index==0) { 
		// A list of size one can only have index 0 removed, so this is for that one case.
			Object retVal = head.data;
			head = null;
			return retVal;
		}
		else if(index==0) {
		// If the user wants to remove index 0, update the head and return data.
			Object retVal = head.data;
			head = head.next;
			return retVal;
		}
		else {
			if(index > size()) {
				// If the index is greater than the list size, it is invalid.
				throw new ListException(index + " is an Invalid index. The max removable index is " + (size()-1));
			}
			// For all other cases, move to node of before given index
			Node nodeToUpdate = moveToNode(index);
			// Move to the index pointing to the index were removing
			Node retVal = nodeToUpdate.next;
			// Since were pointing to the removed index, we make that our retVal
			nodeToUpdate.next = retVal.next;
			// Our node's next will no longer be that removed value, but whatever that removed value was pointed to
			// is now the pointer of this node left to the removed value
			retVal.next = null;
			// Break all node links
			return retVal.data;
			// Return the data of retVal
		}
	}
	
	// moveToNode has a loop that goes to the node before the index, which can be accessed with
	// .next
	private Node moveToNode(int index) {
		// Move to node return the node of a certain index. 
			Node retVal = head;
		for (int i = 1; i < index; i++) {
			// This for loop updates the retVal until it is the node of the index.
			retVal = retVal.next;
		}
		return retVal;
	}
	
	public boolean isEmpty() {
		// is Empty returns true if our list size is 0.
		if(size()==0) {
			return true;
		}
		// Otherwise, it's not empty.
		else return false;
	}
	
	public int indexOf(Object target) {
		// indexOf checks every node data to see if it matches the target data.
		Node check = head;
		// We check data one node at a time, starting with head.
		for (int i = 0; i < size(); i++) {
			if(check.data == null) {
				// If the data is null, return -1.
				return -1;}
			else if(check.data.equals(target)) {
				// If the data is equal to the target, return i
				return i;}
			else if(check.next == null) {
				// If next node is null, that means we've reached the end of the list.
				return -1;}
			else {
				// Update the next check to the next node, then loop again.
				check = check.next;}
			}
		return -1;
		}
		
	public int size() {
		// size() returns the number of nodes in the array, which is the list length.
		if(head==null) {
			return 0;
			// If the head is null, the list is empty
		}
		int count = 1;
		Node dataCounter = head;
		// Count the list nodes, starting with head.
		while(dataCounter.next != null) {
			// If the next isn't null, keep counting and updating the count.
			dataCounter = dataCounter.next;
			count++;
		}
		return count;
	}
	
	private class Node {
		// Private Class Node: Holds data and a pointer to the next node.
		public Object data;	// Holds any object as data
		public Node next;	// Points to the next node.
	
	private Node(Object objData, Node nextPointer) {
		// A Node constructor that takes an object and pointer.
		data = objData;
		next = nextPointer;}
	}
	
	@Override
	public String toString() {
		// toString prints the list data.
		String s = "";
		Node dataToPrint = head;
		for(int i = 0; i<size(); i++) {
			// For loop updates dataToPrint and prints it
			// until the i reaches the last index, which is the value before size.
			s+= dataToPrint.data + ", ";
			dataToPrint=dataToPrint.next;
		}
		return s;
		
	}
	
	/** END OF CODE, NOW ENTERING DRIVER
	 * @throws ListException **/
	public static void main(String[] args) throws ListException {
		List shoppingList = new List();
		shoppingList.insert("Pizza", 0);
		shoppingList.insert("Pina Colada", 1);
		shoppingList.insert("Pasta", 0);
		shoppingList.insert("Peppercini", 3);
		shoppingList.insert("Pineapple", 4);
		shoppingList.insert("Pineapple", 5);
		shoppingList.insert("index 3", 3);
		// Testing insert at different indexs and situations
		
		shoppingList.remove(3);
		shoppingList.remove(0);
		shoppingList.remove(2);
		// Testing remove at indexes 3, 0, 2
		
		shoppingList.insert("1",1);
		System.out.println(shoppingList);
		System.out.println(shoppingList.indexOf("Pizza"));
		System.out.println(shoppingList);
		shoppingList.remove(3);
		System.out.println("After removal: " + shoppingList);
		shoppingList.remove(3);
		System.out.println(shoppingList);
		shoppingList.remove(2);
		shoppingList.remove(1);
		System.out.println(shoppingList);
		System.out.println(shoppingList.remove(0) + " has been removed.");
		System.out.println(shoppingList.isEmpty());
		// More tests
		
		shoppingList.insert("1", 0);
		System.out.println(shoppingList.remove(0));
		
		
		List l = new List();
		l.insert("0", 0);
		l.insert("1", 1);
		l.insert("2", 2);
		l.insert("3", 3);
		
		l.remove(1);
		System.out.println(l);
		System.out.println("returned: " + l.remove(1));
	
		System.out.println(l);
		List empty = new List();
		List one = new List();
		List multiple = new List();
		
		one.insert("foo",0);
		multiple.insert("boo",0);
		multiple.insert("woo", 0);
		multiple.insert("goo", 1);
		
		System.out.println("Empty:"+empty);
		System.out.println("One:"+one);
		System.out.println("Multiple:"+ multiple);	
		
		Object retVal = one.remove(0);
		Object retVal2 = multiple.remove(1);
		System.out.println("One (upon remove):"+one);
		System.out.println("Multiple (upon remove):"+ multiple);
		
		one.insert(600, 0);
		multiple.insert("roo", 2);
		System.out.println("One (on insert):"+one);
		System.out.println("Multiple(on insert):"+ multiple);
		System.out.println(multiple.size());
		System.out.println(one.size());
		
		System.out.println(multiple.indexOf("boo"));
		System.out.println(multiple.indexOf("roo"));
		System.out.println(multiple.indexOf("dasd"));
		System.out.println(one.isEmpty());	
		System.out.println(one);
		System.out.println(multiple);
		// Testing ones, multiples, and empty list
		
		List crash = new List();
		System.out.println(crash.remove(0));
	}
}
	