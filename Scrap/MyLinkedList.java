import java.util.*;

/**
 * Our implementation of LinkedList using MyNode.
 * @author swapneel
 *
 */
public class MyLinkedList<T> implements Collection<T> {

	private Node<T> head;
	int size;

	/**
	 * The constructor - initializes an empty linked list.
	 */
	public MyLinkedList() {
		size = 0;
	}

	public MyLinkedList(Collection<? extends T> elements) {
		head = null;
		ArrayList<T> list = new ArrayList<>(elements);
		Collections.reverse(list);
		list.stream().forEach(this::add);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public boolean contains(Object o) {
		return this.stream().anyMatch(o::equals);
//		while (iterator().hasNext()) {
//			if (iterator().next().equals(o)) {
//				return true;
//			}
//		}
//		return false;
	}

	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator<>(head);
	}

	@Override
	public Object[] toArray() {
		Object[] array = new Object[size()];
		return toArray(array);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T1> T1[] toArray(T1[] array) {
		int i = 0;
		for (T t : this) {
			array[i] = (T1) t; //TODO does this cause problems?
			i++;
		}
		if (array.length > i)
			array[i] = null;
		return array;
	}

	/**
	 * This method will create a new node.
	 * It will add it as the first node of the linked list.
	 * @param value the value to be added
	 */
	public boolean add(T value) {
		Node<T> node = new Node<>(value);
		node.next = head;
		head = node;
		size++;
		return true;
	}

	@Override
	public boolean remove(Object o) {
		for (Node<T> node = head; node != null; node = node.next) {
			if (node.next.equals(o)) {
				node.next = node.next.next;
				size--;
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean containsAll(Collection<?> c) {
		return c.stream().anyMatch(this::contains);
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		MyLinkedList<T> newList = new MyLinkedList<>(c);
		newList.addLast(head);
		this.head = newList.head;
		size += c.size();
		return !c.isEmpty();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		int toRemove = (int) c.stream().filter(this::contains).count();
		c.stream().forEach(this::remove);
		size -= toRemove;
		return toRemove > 0;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		int toRemove = (int) c.stream().filter(x -> !c.contains(x)).count();
		c.stream().filter(x -> !c.contains(x)).forEach(this::remove);
		size -=toRemove;
		return toRemove > 0;
	}

	@Override
	public void clear() {
		head = null;
	}

//	public T pop() {
//		if (head == null) {
//			throw new EmptyStackException();
//		}
//        T value = head.value;
//        head = head.next;
//		size--;
//        return value;
//    }

	/**
	 * This method will create a new node.
	 * It will add it as the last node of the linked list.
	 * @param node the node to be added
	 */
	public void addLast(Node<T> node) {
		size++;
		if (isEmpty()) {
			head = node;
		} else {
			Node<T> i = head;

			//			don't want to modify head directly
			//			shouldn't do head.next = node;

			while (i.next != null) {
				i = i.next;
			}
			i.next = node;
		}
	}

	public String toString() {
		if (!isEmpty()) {

			Node<T> i = head;

			String string = "Printing out the contents of the linked list\n";

			while (i.next != null) {
				string += i.value + " ---> ";
				i = i.next;
			}

			string += i.value;
			return string;
		}
		return "";
	}

	public List<T> toList() {
		@SuppressWarnings("unchecked")
		T[] a = toArray((T[]) new Object[size()]);
		return Arrays.asList(a);
	}

	public void display() {
		System.out.println(toString());
	}

	private class LinkedListIterator<T> implements Iterator<T> {
		Node<T> pointer;

		LinkedListIterator(Node<T> head) {
			pointer = head;
		}

		@Override
        public boolean hasNext() {
            return pointer != null;
        }

		@Override
        public T next() {
            if (pointer == null) {
                throw new NoSuchElementException();
            }
            T value = pointer.value;
            pointer = pointer.next;
            return value;
        }
	}
}
