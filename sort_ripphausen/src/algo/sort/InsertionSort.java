package algo.sort;

public class InsertionSort implements Sort {

	public <T extends Comparable<T>> T[] sort(T[] ar) {
		for(int i = 1; i < ar.length; i++){
			
			T key = ar[i];
			
			int k = i - 1;
			while(k >= 0 && ar[k].compareTo(key) > 0 ) {
				ar[k+1] = ar[k];
				k--;
			}
			ar[k+1] = key;
		}
		
		return ar;
	}
	
	@Override
	public String toString() {
		return "InsertionSort";
	}

}
