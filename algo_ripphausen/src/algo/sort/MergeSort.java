package algo.sort;

import java.lang.reflect.Array;

//TODO CHRIS
public class MergeSort implements Sort {

	@Override
	public <T extends Comparable<T>> T[] sort(T[] ar) {
		if (ar.length > 1) {
			mergesort(ar, 0, ar.length - 1);
		}
		return ar;
	}

	/*
	 * sortiert A in Indexgrenzen l bis r
	 * „Teilen“
	 * linke Hälfte sortieren
	 * rechte Hälfte sortieren
	 * zusammen mischen
	 */
	public <T extends Comparable<T>> void mergesort(T[] ar, int l, int r) {
		if (l < r) {
			int m = (l + r) / 2;
			mergesort(ar, l, m);
			mergesort(ar, m + 1, r);
			merge(ar, l, m, r);
		}
	}

	/* merges the two arrays */
	public <T extends Comparable<T>> void merge(T[] ar, int l, int m, int r) {
		
		/*
		 *  Precondition: A ist sortiert von
		 *  l bis m (Teilfeld A1) und von m+1 bis r (Teilfeld A2)
		 *  Merge sortiert A von l bis r
		 *  H sei Hilfsfeld
		 *  
		 */
		int i = l;
		int j = m + 1;
		@SuppressWarnings("unchecked")
		T[] help = (T[]) Array.newInstance(ar[0].getClass(), r - l + 1);
		int count = 0;
		
		/*
		 * while (kein Teilfeld vollständig von links nach rechts durchlaufen)
		 * kopiere kleineren aktuellen Wert von A1 und A2 nach H 
		 * erhöhe Index von Teilfeld mit kleinerem Wert 
		 * erhöhe Index von H
		*/
		while (i <= m && j <= r) {
			if (ar[i].compareTo(ar[j]) <= 0) {
				help[count] = ar[i];
				i++;
				count++;
			} else {
				help[count] = ar[j];
				j++;
				count++;
			}
		}

		/*
		 * übertrage Werte des noch nicht vollständig durchlaufenen
		 * Teilfelds in Hilfsfeld
		 */
		while (i <= m) {
			help[count] = ar[i];
			count++;
			i++;
		}

		/*
		 * übertrage Werte des noch nicht vollständig durchlaufenen
		 * Teilfelds in Hilfsfeld
		 */
		while (j <= r) {
			help[count] = ar[j];
			count++;
			j++;
		}

		// Kopiere H nach A (von Index l bis Index r)
		for (int u = 0; u < help.length; u++) {
			ar[l + u] = help[u];
		}
	}

	@Override
	public String toString() {
		return "MergeSort";
	}

}
