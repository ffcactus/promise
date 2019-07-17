package com.promise.study.set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class ArraySetTest {

	interface Printer {
		void print();
	}

	class People implements Printer {
		protected String name;

		People() {

		}

		People(String name) {
			this.name = name;
		}

		public boolean equals(Object o) {
			if (o == null) {
				return false;
			}
			if (o instanceof People) {
				var target = (People) o;
				return target.name == this.name;
			} else {
				return false;
			}
		}

		public void print() {
			System.out.println(name);
		}
	}

	class Me extends ArraySetTest.People {
		Me() {
			super();
		}

		Me(String name) {
			super(name);
		}

		public boolean equals(Object o) {
			if (o == null) {
				return false;
			}
			if (o instanceof Me) {
				var target = (Me) o;
				return target.name == this.name;
			} else {
				return false;
			}
		}
	}

	/**
	 * Test the default constructor with no capacity parameter.
	 */
	@Test
	void TestConstructorWithoutCapacity() {
		var set = new ArraySet<String>();
		assertEquals(0, set.capacity());
		assertEquals(0, set.size());
		assertTrue(set.isEmpty());
	}

	/**
	 * Test the constructor with capacity parameter.
	 */
	@Test
	void TestConstructorWithCapacity() {
		var set = new ArraySet<String>(1);
		assertEquals(0, set.size());
		assertTrue(set.isEmpty());
	}

	@Test
	void TestSize() {
		var set = new ArraySet<String>();
		set.add("1");
		assertEquals(1, set.size());
	}

	@Disabled("Disable untill ArraySet allow more than Integer.MAX_VALUE elements to be added.")
	@Test
	void TestSizeOverflow() {
		var set = new ArraySet<Long>();

		for (long i = 0; i < Integer.MAX_VALUE + 1; i++) {
			set.add(i);
		}
		assertEquals(Integer.MAX_VALUE, set.size());
	}

	@Test
	void TestIsEmpty() {
		var set = new ArraySet<String>();
		assertTrue(set.isEmpty());
		set.add("1");
		assertFalse(set.isEmpty());
	}

	/**
	 * Test the exception handling for the case that a null element is used as
	 * parameter.
	 */
	@Test
	void TestContainsNull() {
		var set = new ArraySet<String>();
		assertThrows(NullPointerException.class, () -> {
			set.contains(null);
		});
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	void TestContainsOtherType() {
		var set = new ArraySet<Integer>();
		assertFalse(set.contains("a"));
	}

	@Test
	void TestContains() {
		var set = new ArraySet<Integer>();
		set.add(1);
		assertTrue(set.contains(1));
		assertFalse(set.contains(0));
	}

	@Test
	void TestIterator() {
		var set = new ArraySet<Integer>();
		set.add(1);
		set.add(2);

		var itor = set.iterator();
		assertTrue(itor.hasNext());
		assertEquals(Integer.valueOf(1), itor.next());
		assertTrue(itor.hasNext());
		assertEquals(Integer.valueOf(2), itor.next());
		assertFalse(itor.hasNext());
		set.add(3);
		assertTrue(itor.hasNext());
		assertEquals(Integer.valueOf(3), itor.next());
		assertFalse(itor.hasNext());
		assertThrows(NoSuchElementException.class, () -> {
			itor.next();
		});
	}

	@Test
	void TestToArray() {
		var set = new ArraySet<String>();
		// Should create a new array instance.
		var array1 = set.toArray();
		var array2 = set.toArray();
		assertFalse(array1 == array2);
		assertEquals(0, array1.length);

		set.add("1");
		var array3 = set.toArray();
		set.add("2");
		set.remove("1");
		assertEquals("1", array3[0]);
	}

	@Test
	void TestToTypedArrayCreateNew() {
		var set = new ArraySet<String>();
		set.add("a");
		set.add("b");
		var inputArray = new String[0];
		var array = set.toArray(inputArray);
		assertFalse(inputArray == array);
		assertEquals(3, array.length);
		assertEquals(null, array[2]);		
	}
	
	@Test
	void TestToTypedArrayUsingOrignal() {
		var set = new ArraySet<String>();
		set.add("a");
		set.add("b");
		var dest = new String[4];
		dest[3] = "c";
		var array = set.toArray(dest);
		assertTrue(array == dest);
		assertEquals(null, array[2]);
		assertEquals("c", array[3]);
	}

	@Test
	void TestAddNull() {
		var set = new ArraySet<Integer>();
		assertThrows(NullPointerException.class, () -> {
			set.add(null);
		});
	}

	@Test
	void TestAddInterface() {
		var set = new ArraySet<Printer>();
		var e1 = new People("e1");
		var e2 = new Me("e2");
		Printer e3 = new Me("e3");
		assertTrue(set.add(e1));
		assertTrue(set.add(e2));
		assertTrue(set.add(e3));
	}

	@Test
	void TestAdd() {
		var set = new ArraySet<String>();
		assertTrue(set.add("1"));
		assertTrue(set.add("2"));
		assertFalse(set.add("2"));
		assertEquals(2, set.size());
	}
	
	void TestAddWithDifferentTypes() {
		var s1 = new ArraySet<People>();
		assertTrue(s1.add(new People("1")));
		assertTrue(s1.add(new Me("2")));
		assertEquals(2, s1.size());
		
		var s2 = new ArraySet<Printer>();
		assertTrue(s2.add(new People("1")));
		assertTrue(s2.add(new Me("2")));
		assertEquals(2, s2.size());
	}

	@Test
	void TestRemoveNull() {
		var set = new ArraySet<Integer>();
		assertThrows(NullPointerException.class, () -> {
			set.remove(null);
		});
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	void TestRemoveOtherType() {
		var set = new ArraySet<Integer>();
		assertFalse(set.remove("a"));
	}

	@Test
	void TestRemove() {
		var set = new ArraySet<String>();
		assertFalse(set.remove("1"));
		set.add("1");
		set.add("2");

		// A failed remove should not change the size.
		assertFalse(set.remove("3"));
		assertEquals(2, set.size());

		// A success remove should change the size.
		assertTrue(set.remove("1"));
		assertEquals(1, set.size());
		assertFalse(set.contains("1"));
	}

	/**
	 * If the target collection is null, return false.
	 */
	@Test
	void TestContainsAllWithNull() {
		var set = new ArraySet<String>();
		assertFalse(set.containsAll(null));
	}

	/**
	 * If the target collection has other types, throw ClassCastException.
	 */
	@Test
	void TestContainsAllCollectionIncludesOtherType() {
		var set = new ArraySet<String>();
		set.add("1");
		set.add("2");
		set.add("3");
		var collection = new ArrayList<Object>();
		collection.add("1");
		collection.add(2);
		assertFalse(set.containsAll(collection));
	}

	/**
	 * If the target collection has null element, throw NullPointerException.
	 */
	@Test
	void TestContainsAllCollectionIncludesNull() {
		var set = new ArraySet<String>();
		set.add("1");
		set.add("2");
		set.add("3");
		var collection = new ArrayList<Object>();
		collection.add("1");
		collection.add(null);
		assertThrows(NullPointerException.class, () -> {
			set.containsAll(collection);
		});
	}

	@Test
	void TestContainsAll() {
		var set = new ArraySet<String>();
		set.add("1");
		set.add("2");
		set.add("3");

		var c1 = new ArrayList<Object>();
		c1.add("1");

		var c2 = new ArrayList<Object>();
		c2.add("1");
		c2.add("3");

		var c3 = new ArrayList<Object>();
		c3.add("2");
		c3.add("4");

		var c4 = new ArrayList<Object>();
		c4.add("5");

		assertTrue(set.containsAll(c1));
		assertTrue(set.containsAll(c2));
		assertFalse(set.containsAll(c3));
		assertFalse(set.containsAll(c4));
	}

	@Test
	void TestAddAllWithNull() {
		var set = new ArraySet<String>();
		assertFalse(set.addAll(null));
	}

	@Test
	void TestAddAllCollectionIncludesNull() {
		var set = new ArraySet<String>();
		var collection = new ArrayList<String>();
		collection.add("1");
		collection.add(null);
		assertThrows(NullPointerException.class, () -> {
			set.addAll(collection);
		});
	}

	@Test
	void TestAddAllCollectionWithOtherType() {
		var set = new ArraySet<People>();
		var collection = new ArrayList<People>();
		collection.add(new People());
		collection.add(new Me());
		assertTrue(set.addAll(collection));
	}

//	@Test
//	void TestAddAllFailedWithoutSizeChange() {
//		var set = new ArraySet<People>(People.class);
//		Collection<Object> collection = new ArrayList<Object>();
//		collection.add(new People("1"));
//		collection.add("1");
//		assertFalse(set.addAll(collection));
//		assertTrue(set.isEmpty());
//	}

	@Test
	void TestAddAll() {
		var set = new ArraySet<String>();
		set.add("1");
		set.add("2");
		var c1 = new ArrayList<String>();
		c1.add("1");
		c1.add("2");
		assertFalse(set.addAll(c1));
		var c2 = new ArrayList<String>();
		c2.add("3");
		c2.add("4");
		assertTrue(set.addAll(c2));
	}

	@Test
	void TestRetainAllWithNull() {
		var set = new ArraySet<String>();
		set.add("1");
		set.add("2");
		set.add("3");
		assertThrows(NullPointerException.class, () -> {
			set.retainAll(null);
		});
		assertEquals(3, set.size());
	}

	@Test
	void TestRetainAll() {
		var set = new ArraySet<String>();
		set.add("1");
		set.add("2");
		set.add("3");
		var collection = new ArrayList<String>();
		collection.add(null);
		collection.add("1");
		collection.add("4");
		assertTrue(set.retainAll(collection));
		assertEquals(2, set.size());
		assertTrue(set.contains("2"));
		assertTrue(set.contains("3"));
	}

	@Test
	void TestRemoveAllWithNull() {
		var set = new ArraySet<String>();
		assertFalse(set.removeAll(null));
	}

	@Test
	void TestRemoveAllCollectionWithOtherType() {
		var set = new ArraySet<People>();
		set.add(new People("a"));
		set.add(new People("b"));
		var collection = new ArrayList<People>();
		collection.add(new Me());
		assertFalse(set.removeAll(collection));

	}

	@Test
	void TestRemoveAllCollectionContainsNull() {
		var set = new ArraySet<String>();
		set.add("1");
		set.add("2");
		set.add("3");
		var collection = new ArrayList<String>();
		collection.add("1");
		collection.add(null);
		assertThrows(NullPointerException.class, () -> {
			set.removeAll(collection);
		});
	}

	@Test
	void TestRemoveAll() {
		var set = new ArraySet<String>();
		set.add("1");
		set.add("2");
		set.add("3");

		var collection = new ArrayList<String>();
		collection.add("1");
		collection.add("3");
		assertTrue(set.removeAll(collection));
		assertEquals(1, set.size());
		assertTrue(set.contains("2"));
		assertFalse(set.contains("1"));
		assertFalse(set.contains("3"));
	}

	@Test
	void TestClear() {
		var set = new ArraySet<String>();
		set.add("1");
		set.add("2");
		set.clear();
		assertTrue(set.isEmpty());
	}

	@Test
	void TestCapacity() {
		var set1 = new ArraySet<String>();
		assertEquals(0, set1.capacity());

		var set2 = new ArraySet<String>(1000);
		assertEquals(1024, set2.capacity());
	}

	@Test
	void TestCapacityChange() {
		var set = new ArraySet<String>();
		set.add("1");
		assertEquals(1, set.capacity());
		set.add("2");
		assertEquals(2, set.capacity());
		set.add("3");
		assertEquals(4, set.capacity());
		set.add("4");
		assertEquals(4, set.capacity());
		set.add("5");
		assertEquals(8, set.capacity());

		set.remove("1"); // 2, 3, 4, 5
		assertEquals(4, set.capacity());
		set.remove("2"); // 3, 4, 5
		assertEquals(4, set.capacity());
		set.remove("3"); // 4, 5
		assertEquals(2, set.capacity());
		set.remove("4"); // 5
		assertEquals(1, set.capacity());
		set.remove("5");
		assertEquals(0, set.capacity());

	}

	@Test
	void TestEquals() {
		var set1 = new ArraySet<String>();
		set1.add("1");
		set1.add("2");

		var set2 = new ArraySet<String>();
		set2.add("1");
		set2.add("2");

		var set3 = new ArraySet<String>();
		set3.add("1");

		var set4 = new ArraySet<String>();
		set4.add("4");

		var set5 = new ArraySet<String>();
		set5.add("1");
		set5.add("4");

		var set6 = new ArraySet<Integer>();
		set6.add(Integer.valueOf(1));

		assertFalse(set1.equals(null));

		assertTrue(set1.equals(set2));
		assertFalse(set1.equals(set3));
		assertFalse(set1.equals(set4));
		assertFalse(set1.equals(set5));
		assertFalse(set1.equals(set6));
	}
}
