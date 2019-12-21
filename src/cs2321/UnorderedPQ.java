package cs2321;

import java.util.Comparator;

import net.datastructures.*;
/**
 * A PriorityQueue based on an Unordered Doubly Linked List. 
 * 
 * Course: CS2321 Section ALL
 * Assignment: #3
 * @author
 */

public class UnorderedPQ<K,V> implements PriorityQueue<K,V> {
	//nested classes
	//
	//implementation of entry, contains a key and value
	public class PQEntry<K,V> implements Entry<K,V>{
		//vars
		//
		private K k;
		private V v;
		
		//constructor
		//
		public PQEntry(K key, V value) {
			k = key;
			v = value;
		}
		
		//functions
		//
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
	@TimeComplexity("O(1)")
	public UnorderedPQ() {
		comp = new DefaultComparator<K>();
	}
	
	//instantiate with given comparator
	//c: comparator to use
	@TimeComplexity("O(1)")
	public UnorderedPQ(Comparator<K> c) {
		comp = c;
	}
	
	//functions
	//
	//return size of UnorderedPQ
	@Override
	@TimeComplexity("O(1)")
	public int size() {
		return list.size();
	}

	//returns true if UnorderedPQ is empty
	@Override
	@TimeComplexity("O(1)")
	public boolean isEmpty() {
		return size() == 0;
	}
	
	//insert new entry at end of UnorderedPQ
	//k: entry key
	//v: entry value
	@Override
	@TimeComplexity("O(1)")
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		/* Just adds an entry to the end of the list, which has O(1)
		 * Therefore this function is O(1)
		 */
		checkKey(key);
		Entry<K,V> newest = new PQEntry<>(key, value);
		list.addLast(newest);
		return newest;
	}

	//return entry with smallest key, do not remove from UnorderedPQ
	@Override
	@TimeComplexity("O(n)")
	public Entry<K, V> min() {
		/* List is not sorted so must search list for smallest key.
		 * calls findMin which has O(n)
		 * all other calls are O(1)
		 * results in O(n)
		 */
		if (list.isEmpty()) {
			return null;
		}
		return findMin().getElement();
	}

	//return entry with smallest key, remove from queue
	@Override
	@TimeComplexity("O(n)")
	public Entry<K, V> removeMin() {
		/* List is not sorted so must search list for smallest key.
		 * calls findMin which has O(n)
		 * all other calls are O(1)
		 * results in O(n)
		 */
		if (list.isEmpty()) {
			return null;
		}
		return list.remove(findMin());
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
		} 
		catch (ClassCastException e) {
			throw new IllegalArgumentException("Incompatible key");
		}
	}
	
	//find and return position of entry with smallest key in UnorderedPQ
	@TimeComplexity("O(n)")
	private Position<Entry<K,V>> findMin(){
		/* Assign small to the first element in list.
		 * Loop through all entries in the list, 
		 * when an entry has a key smaller than the previous smallest,
		 * assign small to new smallest entry.
		 * Must always loop through n entries in list, therefore O(n)
		 */
		Position<Entry<K,V>> small = list.first();
		for(Position<Entry<K,V>> walk : list.positions()) {
			if(compare(walk.getElement(), small.getElement()) < 0) {
				small = walk;
			}
		}
		return small;
	}
}
