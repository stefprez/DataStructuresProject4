package project;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {

		while (true) {

			System.out
					.print("Please select the sorting algorithm you would like to use:\n"
							+ "1. Quick Sort\n"
							+ "2. Heap Sort\n"
							+ "3. Merge Sort\n");

			System.out.print("Selection: ");
			String selectionValue = getResponse();

			SortingAlgorithm selectedSortingAlgorithm = null;

			switch (selectionValue) {
			case "1":
				System.out.println("Quick Sort\n");
				selectedSortingAlgorithm = new QuickSort();
				break;
			case "2":
				System.out.println("Heap Sort\n");
				selectedSortingAlgorithm = new HeapSort();
				break;
			case "3":
				System.out.println("Merge Sort\n");
				selectedSortingAlgorithm = new MergeSort();
				break;
			default:
				System.out.println("Invalid Selection\n");
				break;
			}

			System.out.print("Please select the array you would like to use:\n"
					+ "1. Ascending\n" + "2. Random\n" + "3. Descending\n"
					+ "4. Custom File\n");

			System.out.print("Selection: ");
			selectionValue = getResponse();

			String arraySelection = "";
			String arrayFilePath = "";

			switch (selectionValue) {
			case "1":
				arraySelection = "Ascending";
				arrayFilePath = "dataAscend.txt";
				break;
			case "2":
				arraySelection = "Random";
				arrayFilePath = "dataRandom.txt";
				break;
			case "3":
				arraySelection = "Descending";
				arrayFilePath = "dataDescend.txt";
				break;
			case "4":
				arraySelection = "Custom";
				System.out
						.print("Please enter the file name or file path\nof the array file you would like to use: ");
				arrayFilePath = getResponse();
				break;
			default:
				System.out.println("Invalid Selection");
				break;
			}

			int[] arrayToSort = readArrayFromFile(arrayFilePath);

			selectedSortingAlgorithm.sort(arrayToSort);

			printSortingReport(selectedSortingAlgorithm, arraySelection);

			outputSortedArrayToFile(arrayToSort, selectedSortingAlgorithm);

		}

	}

	private static void outputSortedArrayToFile(int[] arrayToSort,
			SortingAlgorithm selectedSortingAlgorithm) {
		
		System.out.print("Please enter the output file name: ");
		String outputFileName = getResponse();

		if (outputFileName.equals(""))
			outputFileName = "untitled.txt";
		
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("OutputFiles/" + outputFileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		for (int i : arrayToSort) {
			writer.println(i);
		}

		writer.close();
	}

	private static String getResponse() {
		@SuppressWarnings("resource")
		Scanner keyboard = new Scanner(System.in);
		String returnString = keyboard.nextLine();

		return returnString;
	}

	private static int[] readArrayFromFile(String fileName) {
		int arraySize = -1;
		
		try {
			arraySize = countLines(fileName);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("Array Size: " + arraySize);
		
		int[] arrayToReturn = new int[arraySize];
		Scanner inputFile = null;
		try {
			inputFile = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		int counter = 0;

		while (inputFile.hasNext()) {
			arrayToReturn[counter] = inputFile.nextInt();
			counter++;
		}

		inputFile.close();

		return arrayToReturn;
	}

	private static void printSortingReport(
			SortingAlgorithm selectedSortingAlgorithm, String arraySelection) {

		long timeToSortInNanoseconds = selectedSortingAlgorithm.getTimeToSort();
		float timeToSortInMilliseconds = (float) timeToSortInNanoseconds / 1000000;
		System.out.println();
		System.out.println("***************");

		System.out.println("Algorithm: " + selectedSortingAlgorithm.getName());
		System.out.println("Array: " + arraySelection);
		System.out.println("Time to sort: " + timeToSortInNanoseconds
				+ " ns (or " + timeToSortInMilliseconds + " ms)");

		System.out.println("***************");
		System.out.println();

	}
	
	private static int countLines(String filename) throws IOException {
	    InputStream is = new BufferedInputStream(new FileInputStream(filename));
	    try {
	        byte[] c = new byte[1024];
	        int count = 0;
	        int readChars = 0;
	        boolean empty = true;
	        while ((readChars = is.read(c)) != -1) {
	            empty = false;
	            for (int i = 0; i < readChars; ++i) {
	                if (c[i] == '\n') {
	                    ++count;
	                }
	            }
	        }
	        return (count == 0 && !empty) ? 1 : count;
	    } finally {
	        is.close();
	    }
	}
}