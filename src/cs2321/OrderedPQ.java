package cs2321;

import java.util.Comparator;

import net.datastructures.*;
/**
 * A PriorityQueue based on an ordered Doubly Linked List. 
 * 
 * Course: CS2321 Section ALL
 * Assignment: #3
 * @author
 */

public class OrderedPQ<K,V> implements PriorityQueue<K,V> {
	//nested classes
	//
	//implementation of entry, contains a key and value
	public class PQEntry<K,V> implements Entry<K,V>{
		//vars
		//
		private K k;
		private V v;
		
		//constructors
		//
		public PQEntry(K key, V value) {
			k = key;
			v = value;
		}
		
		//functions
		@Override
		public K getKey() {
			return k;
		}

		@Override
		public V getValue() {
			return v;
		}
		
		protected void setKey(K key) {
			k=key;
		}
		protected void setValue(V value) {
			v=value;
		}
	}
	
	//vars
	//
	private Comparator<K> comp;
	public DoublyLinkedList<Entry<K,V>> list = new DoublyLinkedList<>();
	
	//constructors
	//
	//instantiate with default comparator
	public OrderedPQ() {
		comp = new DefaultComparator<K>();
	}
	
	//instantiate with given comparator
	//c: comparator to use
	public OrderedPQ(Comparator<K> c) {
		comp = c;
	}
	
	//functions
	//
	//return size of OrderedPQ
	@Override
	@TimeComplexity("O(1)")
	public int size() {
		return list.size();
	}

	//returns true if OrderedPQ is empty
	@Override
	@TimeComplexity("O(1)")
	public boolean isEmpty() {
		return size() == 0;
	}

	//insert new entry at correct position in OrderedPQ
	//k: entry key
	//v: entry value
	@Override
	@TimeComplexity("O(n)")
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		 /* loops through the list from highest to lowest
		  * when an entry with a key smaller than the new entries key is found
		  * insert new entry after larger entry.
		  * Worst case scenario the entire n length of the list must be looped through
		  * therefore function is O(n)
		  */
		checkKey(key);
		Entry<K,V> newest = new PQEntry<>(key, value);
		Position<Entry<K,V>> walk = list.last();
		
		while(walk != null && compare(newest, walk.getElement()) < 0) {
			walk = list.before(walk);
		}
		
		if(walk == null) {
			list.addFirst(newest);
		} else {
			list.addAfter(walk, newest);
		}
		
		return newest;
	}

	//return entry with smallest key, do not remove from UnorderedPQ
	@Override
	@TimeComplexity("O(1)")
	public Entry<K, V> min() {
		/* because queue is already sorted,
		 * just look at first element in list.
		 * therefore function is O(1)
		 */
		if (list.isEmpty()){
			return null;
		}
		return list.first().getElement();
	}

	//return entry with smallest key, remove from queue
	@Override
	@TimeComplexity("O(1)")
	public Entry<K, V> removeMin() {
		/* because queue is already sorted,
		 * just remove first element in list.
		 * therefore function is O(1)
		 */
		if (list.isEmpty()) {
			return null;
		}
		return list.remove(list.first());
	}
	
	//utility
	//
	//compare the keys of two entries using comparator
	//a: first entry in comparison
	//b: second entry in comparison
	@TimeComplexity("O(1)")
	protected int compare(Entry<K,V> a, Entry<K,V> b) {
		return comp.compare(a.getKey(), b.getKey());
	}
	
	//return true if key is valid
	//key: key to check validity of
	@TimeComplexity("O(1)")
	protected boolean checkKey(K key) throws IllegalArgumentException{
		try {
			return (comp.compare(key, key) == 0);
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("Incompatible key");
		}
	}
}
