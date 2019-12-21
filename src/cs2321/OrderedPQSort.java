package cs2321;

public class OrderedPQSort<K extends Comparable<K>> extends PQSort<K> implements Sorter<K>  {
	
	@Override
	@TimeComplexity("O(n^2)")
	public void sort(K[] array) {
		//the sort.sort function contains a loop of insert and a loop of remove
		//OrderedPQ has a time complexity of O(n) for insert
		//This function is O(n^2) because it has a loop of n containing a loop of n
		
		//create an empty pq and a pqsorter
		OrderedPQ<K, K> pq = new OrderedPQ<K, K>();
		PQSort<K> sort = new PQSort<K>();
		
		//pass the empty pq and unsorted array into the sorter
		sort.sort(array, pq);
	}
}
