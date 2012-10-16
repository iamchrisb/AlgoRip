package algo.sort;

import java.lang.reflect.Array;

//TODO CHRIS
public class MergeSortWiki implements Sort {

	@Override
	public <T extends Comparable<T>> T[] sort(T[] ar) {

		if (ar.length <= 1)
			return ar;

		T[] l = splitToLeft(ar);
		T[] r = splitToRight(ar);
		sort(l);
		sort(r);

		return merge(r, l);
	}

	@SuppressWarnings("unchecked")
	public <T extends Comparable<T>> T[] merge(T[] r, T[] l) {
		T[] n = (T[]) Array.newInstance(r[0].getClass(), r.length + l.length);
		while (r.length != 0 && l.length != 0) {
			if (l[0].compareTo(r[0]) == 0 || l[0].compareTo(r[0]) < 0) {
				fooAdd(n, l);
			}else {
				fooAdd(n, r);
			}
		}

		while (l.length != 0) {
			fooAdd(n, l);
		}

		while (r.length != 0) {
			fooAdd(n, r);
		}

		return n;
	}
	
	@SuppressWarnings("unchecked")
	private <T extends Comparable<T>> void fooAdd(T[] n,T[] l){ 
		moveAndFillLast(n, l[0]);
		T[] neoL = (T[]) Array.newInstance(l[0].getClass(), l.length - 1);
		for (int i = 0; i < l.length; i++) {
			neoL[i] = l[i+1];
		}
		l = neoL;
	}

	@SuppressWarnings("unchecked")
	private <T extends Comparable<T>> T[] splitToLeft(T[] ar) {
		T[] l = (T[]) Array.newInstance(ar[0].getClass(), ar.length / 2);
		for (int i = 0; i < l.length; i++) {
			l[i] = ar[i];
		}
		return l;
	}

	@SuppressWarnings("unchecked")
	private <T extends Comparable<T>> T[] splitToRight(T[] ar) {
		T[] r = (T[]) Array.newInstance(ar[0].getClass(), ar.length / 2);
		for (int i = 0; i < r.length; i++) {
			r[i] = ar[r.length + i];
		}
		return r;
	}

	private <T extends Comparable<T>> T[] moveAndFillLast(T[] ar, T a) {
		for (int i = 0; i < ar.length-1; i++) {
//			if(ar.length > 1) 
				ar[i] = ar[i + 1];
		}
		ar[ar.length] = a;
		return ar;
	}

	@Override
	public String toString() {
		return "MergeSort";
	}

}
