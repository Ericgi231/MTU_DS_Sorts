package cs2321;

/**
 * A test driver for Sorts.
 * 
 * Course: CS2321 Section ALL
 * Assignment: #4
 * @author Instructor
 */

public class SortTiming {

	public static void main(String [] args){
		double avgtime;
		Integer[] testee;
		java.util.Random random = new java.util.Random();
		
		Integer[]  data = new Integer[100000];
		for(int i = 0; i < 100000; i++) {
			data[i] = random.nextInt(100000);
		}
		
		testee= data.clone();
		avgtime = testSort(testee, new InPlaceSelectionSort<Integer>());	
		System.out.println("InPlaceSelection: " + avgtime + "ms");
		
		testee= data.clone();
		avgtime = testSort(testee, new InPlaceInsertionSort<Integer>());	
		System.out.println("InPlaceInsertion: " + avgtime + "ms");
		
		testee= data.clone();
		avgtime = testSort(testee, new UnorderedPQSort<Integer>());	
		System.out.println("UnorderedPQ: " + avgtime + "ms");
		
		testee= data.clone();
		avgtime = testSort(testee, new OrderedPQSort<Integer>());	
		System.out.println("OrderedPQ: " + avgtime + "ms");
		
		testee= data.clone();
		avgtime = testSort(testee, new HeapPQSort<Integer>());	
		System.out.println("HeapPQSort: " + avgtime + "ms");
		
		testee= data.clone();
		avgtime = testSort(testee, new InPlaceHeapSort<Integer>());	
		System.out.println("InPlaceHeap: " + avgtime + "ms");
		
		testee= data.clone();
		avgtime = testSort(testee, new MergeSort<Integer>());	
		System.out.println("Merge: " + avgtime + "ms");
		
		testee= data.clone();
		avgtime = testSort(testee, new QuickSort<Integer>());	
		System.out.println("Quick: " + avgtime + "ms");
		
	}

	/**
	 * Algorithm: testSort
	 * @param arr - an array of Integers to use for empirical measurement of a sort
	 * @param sortClass - the Class representing the sorting algorithm to be run
	 * @param iterations - the number of times the sort is repeated
	 * @return average time taken for a single execution of a sort (in nanoseconds)
	 * 
	 * A copy (clone) of the array is made to test over, so that the original may be reused.
	 */
	public static double testSort(Integer[] arr, Sorter<Integer> sortClass){
		long startTime = 0, endTime = 0;

		System.gc();
		startTime = System.nanoTime();
		//#repeated measurements (no less than .5 seconds worth of repeats)
		do{
			//create a copy of the array for each test case
			Integer[] testCase = arr.clone();
			//the sorting algorithm, based on the Sorter Class
			sortClass.sort(testCase);
			
			endTime = System.nanoTime();
		}while(endTime - startTime < 500000000);
				
		return (double) (endTime - startTime) / 1000000;
	}
	
}
