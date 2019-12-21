package cs2321;

import java.util.Comparator;

public class InPlaceHeapSort<K extends Comparable<K>> implements Sorter<K> {

	/**
	 * sort - Perform an in-place heap sort
	 * @param array - Array to sort
	 */
	@TimeComplexity("O(n lg n)")
	public void sort(K[] array) {
		//this function calls heapify n times
		//heapify has a time complexity of O(lg n)
		//so this function is O(n lg n)
		
		//loop through nodes of tree starting at last nodes parent and all the way to the top of the tree
		//runs heapify on each node, turning it and it's children into a heap
		//loops height of tree, aka log n
		int n = array.length;
		for(int i = (n/2)-1; i >= 0; i--) {
			heapify(array, n, i);
		}
		
		for(int i = n-1; i >= 0; i--) {
			K temp = array[i];
			array[i] = array[0];
			array[0] = temp;
			heapify(array, i, 0);
		}
	}

	@TimeComplexity("O(lg n)")
	private void heapify(K[] array, int n, int i) {
		//this function recursively calls itself no greater than log n times
		//so the time complexity is O(lg n)
		
		//assign node index to variables
		Comparator<K> c = new DefaultComparator<K>(); 
		int large = i;
		int l = i*2+1;
		int r = i*2+2;
		
		//if the left child is bigger than the largest node, set that node to largest
		if(l < n && c.compare(array[large], array[l]) < 0) {
			large = l;
		}
		
		//if the right child is bigger than the largest node, set that node to largest
		if(r < n && c.compare(array[large], array[r]) < 0) {
			large = r;
		}
		
		//if a child is larger than the base node, swap them and repeat for new base node
		if(i != large) {
			K temp = array[i];
			array[i] = array[large];
			array[large] = temp;
			heapify(array, n, large);
		}
	}
}
