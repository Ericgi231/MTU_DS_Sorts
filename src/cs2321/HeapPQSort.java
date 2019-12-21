package cs2321;

public class HeapPQSort<K extends Comparable<K>> extends PQSort<K> implements Sorter<K> {
	
	@TimeComplexity("O(n lg n)")
	public void sort(K[] array) {
		//the sort.sort function contains a loop of insert and a loop of remove
		//HeapPQ has a time complexity of O(log n) for both insert and remove
		//This function is O(n log n) because it has a loop of n containing a loop of log n
		
		//create an empty pq and a pqsorter
		HeapPQ<K, K> pq = new HeapPQ<K, K>();
		PQSort<K> sort = new PQSort<K>();
		
		//pass the empty pq and unsorted array into the sorter
		sort.sort(array, pq);
	}

}
