package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author V. Ragulin, 17-Jun-2024
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList, head node and tail don't have values */
	public MyLinkedList() {
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		size = 0;
		head.next = tail;
		tail.prev = head;
	}

	/**
	 *  Get node at a given element
	 * @param index Index where node currently resides
	 */
	private LLNode getNthNode(int index) {
		int current = 0;
		LLNode currentNode = this.head.next;
		while (current <= index) {
			if (current == index)
				break;
			else {
				current ++;
				currentNode = currentNode.next;
			}
		}
		return currentNode;
	}
	
	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element) 
	{
		if (element == null) {
			throw new NullPointerException("invalid input");
		}  

		// change next and previous in neighbouring nodes
		LLNode<E> newNode = new LLNode<E>(element, this.tail.prev, this.tail);
		this.tail.prev.next = newNode;
		this.tail.prev = newNode;
		size++;
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		if (index > size-1 || index < 0) {
			throw new IndexOutOfBoundsException("Invalid index number");
		} else {
			LLNode<E> node = getNthNode(index);
			return (E) node.data;
		}
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		if (element == null) {
			throw new NullPointerException("Invalid Input");
		}
		
		LLNode<E> nodeToMove = getNthNode(index);
		LLNode<E> newNode = new LLNode<E>(element, nodeToMove.prev, nodeToMove);
	
		// update next and prev. in each node
		nodeToMove.prev.next = newNode;
		nodeToMove.prev = newNode;
		size++;
	}


	/** Return the size of the list */
	public int size() 
	{
		return this.size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		if (index > size-1 || index < 0) {
			throw new IndexOutOfBoundsException("invalid index number!");
		} else {
			LLNode<E> nodeToRemove = getNthNode(index);
			nodeToRemove.prev.next = nodeToRemove.next;
			nodeToRemove.next.prev = nodeToRemove.prev;
			this.size --;
			
			return (E) nodeToRemove.data;			
		}
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		if (index > size-1 || index < 0) {
			throw new IndexOutOfBoundsException("invalid index number!");
		} else if (element == null) {
			throw new NullPointerException("Invalid Input");
		} 
		
		LLNode<E> node = getNthNode(index);
		E oldData = node.data;
		node.data = element;
		return oldData;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// No argument constructor is used for the sentinel nodes	
	public LLNode(E e) 
	{
		this(e, null, null);
	}
	
	public LLNode(E e, LLNode prev, LLNode next)
	{
		this.data = e;
		this.prev = prev;
		this.next = next;
	}
	
	@Override
	public String toString() {
		return this.data.toString();
	}
}
