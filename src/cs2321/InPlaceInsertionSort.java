package cs2321;

import java.util.Comparator;

public class InPlaceInsertionSort<K extends Comparable<K>> implements Sorter<K> {

	/**
	 * sort - Perform an in-place insertion sort
	 * @param array - Array to sort
	 */
	@TimeComplexity("O(n^2)")
	public void sort(K[] array) {
		//two loops of n inside each other
		
		Comparator<K> c = new DefaultComparator<K>();
		
		//loop for all elements in array
		for(int n = 1; n < array.length; n++) {
			K t = array[n];
			int j = n-1;
			
			//swap element at end of sorted portion of array left until it is in correct position
			while(j!= -1 && c.compare(t, array[j]) < 0) {
				array[j+1] = array[j];
				j--;
			}
			
			array[j+1] = t;
		}
	}

}
