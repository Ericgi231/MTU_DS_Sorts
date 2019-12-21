package cs2321;

import java.util.Comparator;

public class InPlaceSelectionSort<K extends Comparable<K>> implements Sorter<K> {

	/**
	 * sort - Perform an in-place selection sort
	 * @param array - Array to sort
	 */
	@TimeComplexity("O(n^2)")
	public void sort(K[] array) {
		//two loops of n inside each other
		
		Comparator<K> c = new DefaultComparator<K>();
		int n = array.length;
		
		//for all values in array
		for(int i = 0; i < n-1; i++) {
			int min = i;
			
			//for all values in unsorted portion of array, find smallest value
			for(int j = i + 1; j < n; j++) {
				if(c.compare(array[j], array[min]) < 0) {
					min = j;
				}
			}
			
			//swap smallest value with value at end of sorted portion of array
			if (min != i) {
				K temp = array[i];
				array[i] = array[min];
				array[min] = temp;
			}
		}
	}

}
