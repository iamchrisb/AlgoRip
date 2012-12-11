package algo.sort;


public class QuickSort implements Sort {

	@Override
	public <T extends Comparable<T>> T[] sort(T[] ar) {

		if (ar.length > 1) {
			quicksort(ar, 0, ar.length - 1);
		}
		return ar;
	}

	public <T extends Comparable<T>> void quicksort(T[] ar, int l, int r) {
		if (l < r) {
			int q = partition(ar, l, r);
			quicksort(ar, l, q - 1);
			quicksort(ar, q + 1, r);
		}
	}

	private <T extends Comparable<T>> int partition(T[] ar, int l, int r) {
		int i, j;
		T pivot = ar[r];
		i = l;
		j = r - 1;

		while (i <= j) {
			// if ar[i] bigger then pivot
			if (ar[i].compareTo(pivot) > 0) {
				// tausche x[i] und x[j]
				T swap = ar[i];
				ar[i] = ar[j];
				ar[j] = swap;
				j--;
			} else
				i++;
		}

		// tausche x[i] und x[rechts]
		T swap = ar[i];
		ar[i] = ar[r];
		ar[r] = swap;

		return i;
	}

	@Override
	public String toString() {
		return "QuickSort";
	}
}
