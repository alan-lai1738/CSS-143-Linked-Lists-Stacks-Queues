// Alan Lai
// 11/7/2019
// CSS 143
// Linked Lists/Stacks/Queues 
/* This is the Queue.java class that inherits methods from list.
 * It functions as a First In Last out. You can enqueue or dequeue
 * which adds to the tail of the list/removes the head and returns it.
 */
package linkedlists;

public class Queue extends List {
	
	public void enqueue(Object next) throws ListException {
		// Enqueue calls super insert method and adds to the "tail"
		// of the list
		super.insert(next, super.size());
	}
	
	public Object dequeue() throws ListException {
		// Dequeue removes the head of the list and returns that data
		return super.remove(0);
	}
	
	@Override
	public void insert(Object next, int index) throws ListException {
		// Insert will always enqueue
		enqueue(next);
	}
	
	@Override
	public Object remove(int index) throws ListException {
		// Remove will always dequeue
		return dequeue();
	}
	
	/** DRIVER TESTS
	 * @throws ListException **/
	public static void main(String[] args) throws ListException {
		Queue q = new Queue();
		q.enqueue("0");
		q.enqueue("1");
		System.out.println(q);
		System.out.println(q.dequeue());
		q.enqueue("100");
		System.out.println(q);
		System.out.println(q.dequeue());
		q.enqueue("1000");
		System.out.println(q);
		System.out.println(q.dequeue() + " has been dequeued");
		System.out.println(q.dequeue() + " has been dequeued");
		System.out.println(q.isEmpty());
		q.enqueue("coco");
		System.out.println(q);
		q.enqueue("puffs");
		System.out.println(q);
		System.out.println(q.dequeue());
		q.insert("cocococo", 3123921); // DOESNT INSERT: ONLY ENQUEUE
		System.out.println(q);
		q.remove(13123123);
		System.out.println(q);
		// Remove and inserts tests. Only dequeues and enqueues.
		
		Queue crash = new Queue();
		System.out.println(crash.remove(0));
	}
}
