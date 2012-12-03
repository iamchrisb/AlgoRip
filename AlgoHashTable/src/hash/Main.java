package hash;

public class Main {

	/**
	 * main
	 */
	static HashFunction<Integer, String> hashFunktion = new HashFunction<Integer, String>(
			10);

	public static void main(String[] args) {

		hashFunktion.put(15, "HeapSort");
		hashFunktion.put(18, "QuickSort");
		hashFunktion.put(55, "MergeSort");
		hashFunktion.put(67, "SelectionSort");
		hashFunktion.remove(15);
		hashFunktion.remove(18);
		System.out.println(hashFunktion);
	}
}
