package algo.sort;

public class QuickSort implements Sort {

	@Override
	public <T extends Comparable<T>> T[] sort(T[] ar) {
		if (ar.length > 1) {
			mergesort(ar, 0, ar.length - 1);
		}
		return ar;
	}
	
	public <T extends Comparable<T>> void mergesort(T[] ar, int l, int r) {
		if (l < r) {
			int i = merge(ar, l, r);
			mergesort(ar, l, i-1);
			mergesort(ar, i+1, r);
		}
	}
	
	public <T extends Comparable<T>> int merge(T[] ar, int l, int r) {
		int  i, j;
		T help, pivot;
		pivot = ar[r];
		i = l;
		j = r-1;
		
		while(i<=j) {
			// if ar[i] bigger then pivot
			if ( ar[i].compareTo(pivot) > 0) {
				// tausche x[i] und x[j]
				help = ar[i];
				ar[i] = ar[j];
				ar[j] = help;
				j--;
			} else i++;		
		}
		
		// tausche x[i] und x[rechts]
		help = ar[i];
		ar[i] = ar[r];
		ar[r] = help;
		
		return i;
	}

	@Override
	public String toString() {
		return "QuickSort";
	}
}
