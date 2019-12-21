package cs2321;

import java.util.Arrays;
import java.util.Comparator;

public class MergeSort<E extends Comparable<E>> implements Sorter<E> {

	@TimeComplexity("O(n lg n)")
	public void sort(E[] array) {
		//calls the merge function log n times, merge is n
		//so this function is O(n log n)
		
		int n = array.length;
		if(n != 1) {
			//split the array in halves
			int mid = n/2;
			E[] left = Arrays.copyOfRange(array, 0, mid);
			E[] right = Arrays.copyOfRange(array, mid, n);
			
			//sort each half by calling this function
			sort(left);
			sort(right);
			
			//merge the new sorted halves back together
			merge(left, right, array);
		}
	}
	
	@TimeComplexity("O(n)")
	public void merge(E[] l, E[] r, E[] a) { 
		//contains a loop that runs n times
		
		Comparator<E> c = new DefaultComparator<E>();
		int li = 0;
		int ri = 0;
		
		//for combined length of both arrays
		for(int i = 0; i < a.length; i++) {
			//if left array is empty, add all remaining right array elements to merged array
			if(li == l.length) {
				a[i] = r[ri];
				ri++;
			}
			//if right array is empty, add all remaining left array elements to merged array
			else if(ri == r.length) {
				a[i] = l[li];
				li++;
			}
			//if front item of left array is smaller than right, add left item to merged array and move left index
			else if(c.compare(l[li], r[ri]) <= 0) {
				a[i] = l[li];
				li++;
			}
			//if front item of right array is smaller than left, add right item to merged array and move right index
			else if(c.compare(r[ri], l[li]) <= 0) {
				a[i] = r[ri];
				ri++;
			}
		}
	}
}

