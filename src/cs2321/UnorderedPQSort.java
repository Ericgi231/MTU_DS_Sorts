package cs2321;

public class UnorderedPQSort<K extends Comparable<K>> extends PQSort<K> implements Sorter<K>  {

	@Override
	@TimeComplexity("O(n^2)")
	public void sort(K[] array) {
		//the sort.sort function contains a loop of insert and a loop of remove
		//UnorderedPQ has a time complexity of O(n) for remove
		//This function is O(n^2) because it has a loop of n containing a loop of n
		
		//create an empty pq and a pqsorter
		UnorderedPQ<K, K> pq = new UnorderedPQ<K, K>();
		PQSort<K> sort = new PQSort<K>();
		
		//pass the empty pq and unsorted array into the sorter
		sort.sort(array, pq);
	}

}
