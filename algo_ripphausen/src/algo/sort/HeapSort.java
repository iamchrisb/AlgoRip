package algo.sort;

public class HeapSort implements Sort {

	private int n;

	@Override
	public <T extends Comparable<T>> T[] sort(T[] ar) {
		buildHeap(ar);

		n = ar.length - 1;
		for (int i = n; i > 0; i--) {
			T swap = ar[0];
			ar[0] = ar[i];
			ar[i] = swap;
			n--;
			heapify(ar, 0, n);
		}

		return ar;
	}

	@Override
	public String toString() {
		return "HeapSort";
	}

	/*
	 * Heapify(A, i) // A hat Heap-Eigenschaft bis auf Wert // an Index i if
	 * (Nachfolger von i existiert) l: Index linker Nachfolger (wenn ex.) r:
	 * Index rechter Nachfolger (wenn ex.) largest: Index von Nachfolger mit
	 * gr��erem Wert if (A[largest] > A[i]) Austausch Werte an Position i und
	 * largest Heapify(A, largest)
	 * 
	 * � Linker Nachfolger von i: 2*(i+1) � 1 � Rechter Nachfolger von i:
	 * 2*(i+1) � Vorg�nger von i: (i-1)/2
	 */
	public <T extends Comparable<T>> void heapify(T[] ar, int i, int till) {

		T value = ar[i];

		int rF = 2 * (i + 1);
		int lF = 2 * (i + 1) - 1;
		
/*
		boolean left1 = lF < ar.length;
		boolean left2 = lF > 0;
		
		boolean leftGroup = left1 && left2;

		boolean right1 = rF < ar.length;
		boolean right2 = rF > 0;

		boolean rightGroup = right1 && right2;
*/
		int largest = i;
		
		if( lF < till) {
			if(lF == till && ar[lF].compareTo(ar[i]) < 0){
				largest = lF;
			}
			if(ar[lF].compareTo(ar[i]) < 0) {
				largest = lF;
			}
			if(rF <= till){
				if(ar[lF].compareTo(ar[rF]) < 0) largest = rF;
			}
			largest = lF;
			heapify(ar, i, till);
		}
		
//		boolean toN  = lF > n && rF > n;

		
		/*
		if ((rightGroup && leftGroup )) {
			if (ar[rF].compareTo(ar[lF]) < 0) {
				largest = lF;
				if (ar[largest].compareTo(ar[i]) > 0 ) {
					T swap = ar[i];
					ar[i] = ar[largest];
					ar[largest] = swap;
					heapify(ar, largest);
				}
			} else {
				largest = rF;
				if (ar[largest].compareTo(ar[i]) > 0 ) {
					T swap = ar[i];
					ar[i] = ar[largest];
					ar[largest] = swap;
					heapify(ar, largest);
				}
			}
		} else if (rightGroup) {
			largest = rF;
			if (ar[largest].compareTo(ar[i]) > 0 ) {
				T swap = ar[i];
				ar[i] = ar[largest];
				ar[largest] = swap;
				heapify(ar, largest);
			}
		} else if (leftGroup) {
			largest = lF;
			if (ar[largest].compareTo(ar[i]) > 0 ) {
				T swap = ar[i];
				ar[i] = ar[largest];
				ar[largest] = swap;
				heapify(ar, largest);
			}
		}
		*/
	}

	public <T extends Comparable<T>> void buildHeap(T[] ar) {
		int k = ar.length - 1;
		for (int i = k; i >= 0; i--) {
			heapify(ar, i, k);
		}
	}
}
