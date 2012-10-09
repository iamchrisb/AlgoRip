package algo.sort;

import java.lang.reflect.Array;

//TODO CHRIS
public class MergeSort implements Sort {

	@Override
	public <T extends Comparable<T>> T[] sort(T[] ar) {
		mergesort(ar, 0, ar.length-1);
		
		return ar;
	}
	
	public <T extends Comparable<T>> void mergesort(T[] ar, int l, int r){
		if(l < r) {
			int m = (l + r) / 2;
			mergesort(ar, l, m);
			mergesort(ar, m+1, r);
			merge(ar, l, m, r);
		}
	}
	
	public <T extends Comparable<T>> void merge(T[] ar, int l, int m, int r){
		int i = l;
		int j = m+1;
		@SuppressWarnings("unchecked")
		T[] help = (T[]) Array.newInstance(ar[0].getClass(), r - l + 1);
		int count = 0;
		while( i <= m && j <= r) {
			if(ar[i].compareTo(ar[j]) <= 0) {
				help[count] = ar[i];
				i++;
				count++;
			} else {
				help[count] = ar[j];
				j++;
				count++;
			}
		}
		
		while( i <= m ){
			help[count] = ar[i];
			count++;
			i++;
		}
		
		while( j <= r ){
			help[count] = ar[j];
			count++;
			j++;
		}
		
		for(int u = 0; u < help.length ; u++) {
			ar[l+u] = help[u];
		} 
	}
	
	@Override
	public String toString() {
		return "MergeSort";
	}

}
