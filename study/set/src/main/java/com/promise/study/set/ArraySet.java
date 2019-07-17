package com.promise.study.set;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Set that is implemented by array. It is checked, concurrent unsafe, no null element.
 *
 * @param <E> The type of the element.
 */
public class ArraySet<E> implements Set<E> {

	private Object[] array;
	// The real size of this set.
	private long size;
	// set's class used by equals().
	private Class<?> setClass;

	@SuppressWarnings("unchecked")
	public ArraySet(int capacity) {
		// decrement n (to handle cases when n itself
		// is a power of 2)
		capacity = capacity - 1;
		// do till only one bit is left
		while ((capacity & capacity - 1) != 0) {
			capacity = capacity & capacity - 1;    // unset rightmost bit
		}
		// n is now a power of two (less than n)
		// return next power of 2
		capacity = capacity << 1;
		array = (E[]) new Object[capacity];
		// elemClass = e;
		setClass = this.getClass();
		size = 0;
	}

	public ArraySet() {
		this(0);
	}

	/**
	 * Return the capacity of this set. The capacity grows as square of 2 and shrink
	 * vise versa.
	 * 
	 * @return
	 */
	public int capacity() {
		return this.array.length;
	}

	@Override
	public int size() {
		return (int) this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	@Override
	public boolean contains(Object o) {
		if (o == null) {
			throw new NullPointerException();
		}
		for (Object each : array) {
			boolean equals = o.equals(each);
			if (equals) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			private int index = 0;

			@Override
			public boolean hasNext() {
				return index != size;
			}

			@SuppressWarnings("unchecked")
			@Override
			public E next() {
				if (index == size) {
					throw new NoSuchElementException();
				}
				return (E) array[index++];
			}
		};
	}

	@Override
	public Object[] toArray() {
		Object[] ret = new Object[(int) size];
		for (int i = 0; i < size; i++) {
			ret[i] = array[i];
		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a) {
		Array.newInstance(a.getClass().getComponentType(), this.size() + 1);
		
		if (a == null || a.length <= this.size) {
			a = (T[]) Array.newInstance(a.getClass().getComponentType(), this.size() + 1);
		}

		int i;
		for (i = 0; i < this.size; i++) {
			a[i] = (T) this.array[i];
		}
		a[i] = null;
		return a;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean add(E e) {
		if (this.contains(e)) {
			return false;
		}

		// For empty array.
		if (this.array.length == 0) {
			this.array = (E[]) new Object[1];
			this.array[0] = e;
			this.size = 1;
			return true;
		}

		// If array is full, double the size.
		if (this.size == array.length) {
			if (this.size * 2 > Integer.MAX_VALUE) {
				throw new IllegalStateException();
			}

			var newArray = new Object[(int) (this.size * 2)];
			for (int i = 0; i < size; i++) {
				newArray[i] = this.array[i];
			}
			this.array = newArray;
		}
		this.array[(int) this.size++] = e;
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean remove(Object o) {
		if (!this.contains(o)) {
			return false;
		}

		// For 1 size array.
		if (this.size == 1) {
			this.array = (E[]) new Object[0];
			this.size = 0;
			return true;
		}
		// Check if we need shrink the internal array.
		Object[] newArray;
		if (this.size - 1 <= this.array.length / 2) {
			newArray = new Object[this.array.length / 2];
		} else {
			newArray = new Object[this.array.length];
		}
		for (int i = 0, j = 0; i < this.size;) {
			if (this.array[i].equals(o)) {
				i++;
			} else {
				newArray[j++] = this.array[i++];
			}
		}
		this.array = newArray;

		this.size--;
		return true;
	}

	/**
	 * If the target collection is null, return false.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		if (c == null) {
			return false;
		}
		for (var each : c) {
			if (!contains(each)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		var changed = false;
		if (c == null) {
			return false;
		}
		for (var each : c) {
			if (add(each)) {
				changed = true;
			}
		}
		return changed;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		var changed = false;
		if (c == null) {
			throw new NullPointerException();
		}
		for (var each : this) {
			if (c.contains(each)) {
				if (remove(each)) {
					changed = true;
				}
			}
		}
		return changed;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		var changed = false;
		if (c == null) {
			return false;
		}
		for (var each : c) {
			if (remove(each)) {
				changed = true;
			}
		}
		return changed;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		array = (E[]) new Object[0];
		size = 0;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (!setClass.isAssignableFrom(o.getClass())) {
			return false;
		}
		ArraySet<?> target = (ArraySet<?>) o;
		try {
			if (this.containsAll(target) && this.size() == target.size()) {
				return true;
			}
		} catch (NullPointerException | ClassCastException e) {
			return false;
		}

		return false;
	}

}
