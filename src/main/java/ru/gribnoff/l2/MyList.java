package ru.gribnoff.l2;

public interface MyList<T> extends Iterable<T> {
	int DEFAULT_CAPACITY = 10;

	int size();
	boolean isEmpty();
	boolean contains(T obj);
	void add(T obj);
	boolean remove(T obj);
	void clear();
	boolean equals(Object o);
	int hashCode();

	@SuppressWarnings("unchecked")
	default void addAll(T... elems) {
		for (T elem : elems) {
			add(elem);
		}
	}

	@SuppressWarnings("unchecked")
	default boolean containsAll(T... elems) {
		for (T elem : elems) {
			if (!contains(elem))
				return false;
		}
		return true;
	}
}
