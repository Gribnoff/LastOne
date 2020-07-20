package ru.gribnoff.l2.t2;

import ru.gribnoff.l2.MyList;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class MyArrayList<T> implements MyList<T> {
	protected T[] data;
	protected int size;

	public MyArrayList() {
		this(DEFAULT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public MyArrayList(int capacity) {
		this.data = (T[]) new Object[capacity];
	}

	public MyArrayList(T[] data) {
		this.data = data;
		size = data.length;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean contains(T elem) {
		return indexOf(elem) != -1;
	}

	public int indexOf(T elem) {
		for (int i = 0; i < size; i++) {
			if (elem.equals(data[i]))
				return i;
		}
		return -1;
	}

	@Override
	public void add(T obj) {
		checkGrow();
		data[size++] = obj;
	}

	private void checkGrow() {
		if (size == data.length)
			data = Arrays.copyOf(data, size * 2);
	}

	@Override
	public boolean remove(T obj) {
		int index = indexOf(obj);
		if (index != -1) {
			removeByIndex(index);
			return true;
		}
		return false;
	}

	public boolean removeByIndex(int index) {
		checkIndex(index);

		if (size - 1 - index >= 0)
			System.arraycopy(data, index + 1, data, index, size - 1 - index);
		data[--size] = null;

		return true;
	}

	private void checkIndex(int index) {
		if (index >= 0 && index < size)
			return;
		throw new ArrayIndexOutOfBoundsException(String.format("Invalid index value: %d; array size is %d", index, size));
	}

	@Override
	public void clear() {
		for (int i = 0; i < size; i++)
			data[i] = null;
		size = 0;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		MyArrayList<?> that = (MyArrayList<?>) o;
		return size == that.size &&
				Arrays.equals(data, that.data);
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(size);
		result = 31 * result + Arrays.hashCode(data);
		return result;
	}

	@Override
	public Iterator<T> iterator() {
		return new Itr();
	}
	class Itr implements Iterator<T> {
		private int nextIndex;

		Itr() {
			nextIndex = 0;
		}

		public boolean hasNext() {
			return nextIndex < size;
		}

		public T next() {
			if (!hasNext())
				throw new NoSuchElementException();

			return data[nextIndex++];
		}
	}
}
