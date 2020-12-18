// Alan Lai
// 11/7/2019
// CSS 143
// Linked Lists/Stacks/Queues 
/* The Stack.java class inherits methods from list. It functions as a
 * First In First and can only be pushed and popped.
 */
package linkedlists;

public class Stack extends List {
	public void push(Object next) throws ListException {
		super.insert(next, 0);
		// Push uses the insert function from List and only inserts to
		// index 0
	}
	
	public Object pop() throws ListException {
		return super.remove(0);
		// Pop removes the item from index 0 and returns it, using
		// the remove function from List.
	}
	
	@Override
	public void insert(Object next, int index) throws ListException {
		// Override insert so people can't misuses insert, makes it push
		push(next);
	}
	
	@Override
	public Object remove(int index) throws ListException {
		// Override remove to only use pop
		return pop();
	}
	
	/** DRIVER TESTS 
	 * @throws ListException **/
	public static void main(String[] args) throws ListException {
		Stack s = new Stack();
		s.push("0");
		s.push("1");
		s.push("2");
		System.out.println(s.pop() + " has been popped");
		System.out.println("Stack is of size " + s.size());
		System.out.println("Stack empty is " + s.isEmpty());
		s.insert("1", 1);
		System.out.println(s);
		System.out.println(s.remove(99) + " has been removed");
		// Testing remove and insert: pushes and pops only.
		System.out.println("Updated stack: " + s);
		
		Stack crash = new Stack();
		System.out.println(crash.remove(0));
	}
}
