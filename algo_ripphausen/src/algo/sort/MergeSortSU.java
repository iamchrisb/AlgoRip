package algo.sort;

import java.lang.reflect.Array;
import java.util.ArrayList;

//TODO CHRIS
public class MergeSortSU implements Sort {

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Comparable<T>> T[] sort(T[] ar) {
		ArrayList<T> arl = new ArrayList<T>();
		for(int i = 0 ; i < ar.length ; i++ ) {
			arl.add(ar[i]);
		}
		return (mergesort(arl, 0, arl.size())).toArray((T[]) Array.newInstance(ar[0].getClass(), arl.size()));
	}
	
	public <T extends Comparable<T>> ArrayList<T> mergesort(ArrayList<T> arl, int l, int r){
		if(l < r) {
			int m = (l + r) / 2;
			mergesort(arl, l, m);
			mergesort(arl, m+1, r);
			return merge(arl, l, m, r);
		}
		
		return arl;
	}
	
	public <T extends Comparable<T>> ArrayList<T> merge(ArrayList<T> arl, int l, int m, int r){
		int i = 0;
		int j = m+1;
		ArrayList<T> help = new ArrayList<T>();
		while( i <= m && j <= r) {
			if(arl.get(i).compareTo(arl.get(j)) < 0) {
				help.add(arl.get(i));
				i++;
			} else {
				help.add(arl.get(j));
				j++;
			}
		}
		
		return null;
	}

}
