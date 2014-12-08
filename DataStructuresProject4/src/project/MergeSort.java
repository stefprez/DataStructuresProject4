package project;

public class MergeSort implements SortingAlgorithm {

	private final String NAME = "Merge Sort";
	private long timeToSort;

	private int[] theArray;
	private int nElems;

	@Override
	public void sort(int[] arrayToSort) {
		theArray = arrayToSort;
		nElems = theArray.length;

		long startTime = System.nanoTime();
		
		long[] workSpace = new long[nElems];
		recMergeSort(workSpace, 0, nElems - 1);

		timeToSort = System.nanoTime() - startTime;
	}

	private void recMergeSort(long[] workSpace, int lowerBound, int upperBound) {
		if (lowerBound == upperBound) // if range is 1,
			return; // no use sorting
		else { // find midpoint
			int mid = (lowerBound + upperBound) / 2;
			// sort low half
			recMergeSort(workSpace, lowerBound, mid);
			// sort high half
			recMergeSort(workSpace, mid + 1, upperBound);
			// merge them
			merge(workSpace, lowerBound, mid + 1, upperBound);
		} // end else
	} // end recMergeSort(

	private void merge(long[] workSpace, int lowPtr, int highPtr, int upperBound) {
		int j = 0; // workspace index
		int lowerBound = lowPtr;
		int mid = highPtr - 1;
		int n = upperBound - lowerBound + 1; // # of items
		while (lowPtr <= mid && highPtr <= upperBound)
			if (theArray[lowPtr] < theArray[highPtr])
				workSpace[j++] = theArray[lowPtr++];
			else
				workSpace[j++] = theArray[highPtr++];
		while (lowPtr <= mid)
			workSpace[j++] = theArray[lowPtr++];
		while (highPtr <= upperBound)
			workSpace[j++] = theArray[highPtr++];
		for (j = 0; j < n; j++)
			theArray[lowerBound + j] = (int)workSpace[j];
	} // end merge(

	@Override
	public long getTimeToSort() {
		return timeToSort;
	}

	@Override
	public String getName() {
		return NAME;
	}

}