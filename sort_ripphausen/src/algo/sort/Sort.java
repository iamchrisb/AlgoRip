package algo.sort;

/*
 * Sort - Interface for all 
 * sorts to use a strategy - pattern
 */
public interface Sort {
	
	/**
	 * 
	 * @param ar - array to be sorted, type: extends comparable
	 * to sort own types
	 * 
	 * @return returns the sorted array
	 */
	<T extends Comparable<T>> T[] sort(T[] ar);
}
