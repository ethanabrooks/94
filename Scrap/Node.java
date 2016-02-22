/**
 * Our implementation of node.
 * Uses a String for the value. Could use int, double, other objects or generics.
 * @author swapneel
 *
 */
//public class MyGenericNode<K, V> {
public class Node<T> {
	
	public T value;
	public Node<T> next;

	/**
	 * The constructor for node.
	 * @param val the value of the node.
	 */
	public Node(T val) {
		value = val;
		next = null;
	}

}
