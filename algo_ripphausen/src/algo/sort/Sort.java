package algo.sort;

public interface Sort {
	<T extends Comparable<T>> T[] sort(T[] ar);
}
