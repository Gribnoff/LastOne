package ru.gribnoff.l2.t1;

import ru.gribnoff.l2.MyList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements MyList<T> {
	private Node<T> first;
	private Node<T> last;
	private int size;

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return first == null;
	}

	public void addFirst(T elem) {
		first = new Node<>(elem, null, first);

		if (size == 0)
			last = first;
		else
			first.next.prev = first;
		size++;
	}

	@Override
	public void add(T elem) {
		addLast(elem);
	}

	public void addLast(T elem) {
		last = new Node<>(elem, last, null);

		if (size == 0)
			first = last;
		else
			last.prev.next = last;

		size++;
	}

	public T removeFirst() {
		if (isEmpty())
			return null;

		T elem = first.elem;
		first = first.next;
		if (first != null)
			first.prev = null;

		size--;
		return elem;
	}

	public T removeLast() {
		if (isEmpty())
			return null;

		T elem = last.elem;
		last = last.prev;
		last.next = null;

		size--;
		return elem;
	}

	@Override
	public boolean remove(T elem) {
		Node<T> current = first;
		while (current != null) {
			if (current.elem.equals(elem))
				break;

			current = current.next;
		}

		if (current == null)
			return false;
		else if (current == first) {
			first = current.next;
			if (first != null)
				first.prev = null;
		} else if (current == last) {
			last = current.prev;
			if (last != null)
				last.next = null;
		} else {
			current.next.prev = current.prev;
			current.prev.next = current.next;
		}

		size--;
		return true;
	}

	@Override
	public void clear() {
		for (Node<T> node = first; node != null; ) {
			Node<T> next = node.next;
			node.elem = null;
			node.next = null;
			node.prev = null;
			node = next;
		}
		first = last = null;
		size = 0;
	}

	@Override
	public boolean contains(T elem) {
		for (T t : this) {
			if (elem.equals(t))
				return true;
		}
		return false;
	}

	public T getFirst() {
		return first.elem;
	}

	public T getLast() {
		return last.elem;
	}

	class Node<T> {
		T elem;
		Node<T> prev;
		Node<T> next;

		Node(T elem, Node<T> prev, Node<T> next) {
			this.elem = elem;
			this.prev = prev;
			this.next = next;
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new Itr();
	}

	class Itr implements Iterator<T> {
		private Node<T> current;
		private Node<T> next;
		private int nextIndex;

		Itr() {
			next = (size > 0) ? first : null;
			nextIndex = 0;
		}

		public boolean hasNext() {
			return nextIndex < size;
		}

		public T next() {
			if (!hasNext())
				throw new NoSuchElementException();

			current = next;
			next = next.next;
			nextIndex++;
			return current.elem;
		}
	}
}
