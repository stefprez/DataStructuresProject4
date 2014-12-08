package project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HeapSort implements SortingAlgorithm {

	private final String NAME = "Heap Sort";

	private long timeToSort;

	@Override
	public void sort(int[] arrayToSort) {
		long startTime = System.nanoTime();

		int size=10000, j;
		
		Heap theHeap = new Heap(size);
		for (j = 0; j < size; j++) // fill array with
		{
			int valueToInsert = arrayToSort[j];
			Node newNode = new Node(valueToInsert);
			theHeap.insertAt(j, newNode);
			theHeap.incrementSize();
		}
		
		// make random array into heap
		for (j = size / 2 - 1; j >= 0; j--)
			theHeap.trickleDown(j);
		
		for (j = size - 1; j >= 0; j--) // remove from heap and
		{ // store at array end
			Node biggestNode = theHeap.remove();
			theHeap.insertAt(j, biggestNode);
		}

		for (int i = size - 1; i >= 0; i--)
		{
			arrayToSort[i] = theHeap.heapArray[i].getKey();
		}
		
		timeToSort = System.nanoTime() - startTime;

	}

	public static int getInt() throws IOException {
		String s = getString();
		return Integer.parseInt(s);
	}

	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}

	@Override
	public long getTimeToSort() {
		return timeToSort;
	}

	@Override
	public String getName() {
		return NAME;
	}

	private class Heap {
		private Node[] heapArray;
		private int maxSize; // size of array
		private int currentSize; // number of items in array

		public Heap(int mx) // constructor
		{
			maxSize = mx;
			currentSize = 0;
			heapArray = new Node[maxSize];
		}

		public Node remove() // delete item with max key
		{ // (assumes non-empty list)
			Node root = heapArray[0];
			heapArray[0] = heapArray[--currentSize];
			trickleDown(0);
			return root;
		} // end remove()

		public void trickleDown(int index) {
			int largerChild;
			Node top = heapArray[index]; // save root
			while (index < currentSize / 2) // not on bottom row
			{
				int leftChild = 2 * index + 1;
				int rightChild = leftChild + 1;
				// find larger child
				if (rightChild < currentSize && // right ch exists?
						heapArray[leftChild].getKey() < heapArray[rightChild]
								.getKey())
					largerChild = rightChild;
				else
					largerChild = leftChild;
				// top >= largerChild?
				if (top.getKey() >= heapArray[largerChild].getKey())
					break;
				// shift child up
				heapArray[index] = heapArray[largerChild];
				index = largerChild; // go down
			} // end while
			heapArray[index] = top; // root to index
		} // end trickleDown()

//		public void displayHeap() {
//			int nBlanks = 32;
//			int itemsPerRow = 1;
//			int column = 0;
//			int j = 0; // current item
//			String dots = "...............................";
//			System.out.println(dots + dots); // dotted top line
//			while (currentSize > 0) // for each heap item
//			{
//				if (column == 0) // first item in row?
//					for (int k = 0; k < nBlanks; k++)
//						// preceding blanks
//						System.out.print(' ');
//				// display item
//				System.out.print(heapArray[j].getKey());
//				if (++j == currentSize) // done?
//					break;
//				if (++column == itemsPerRow) // end of row?
//				{
//					nBlanks /= 2; // half the blanks
//					itemsPerRow *= 2; // twice the items
//					column = 0; // start over on
//					System.out.println(); // new row
//				} else
//					// next item on row
//					for (int k = 0; k < nBlanks * 2 - 2; k++)
//						System.out.print(' '); // interim blanks
//			} // end for
//			System.out.println("\n" + dots + dots); // dotted bottom line
//		} // end displayHeap()
//
//		public void displayArray() {
//			for (int j = 0; j < maxSize; j++)
//				System.out.println(heapArray[j].getKey());
//		}
//
		public void insertAt(int index, Node newNode) {
			heapArray[index] = newNode;
		}

		public void incrementSize() {
			currentSize++;
		}
	} // end class Heap

	class Node {
		private int iData; // data item (key)

		public Node(int key) // constructor
		{
			iData = key;
		}

		public int getKey() {
			return iData;
		}
	} // end class Nod

}