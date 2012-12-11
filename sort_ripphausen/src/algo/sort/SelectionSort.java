package algo.sort;


public class SelectionSort implements Sort {

	public <T extends Comparable<T>> T[] sort(T[] test) {

		T n;
		int index;
		
		for (int i = 0; i < test.length; i++) {

			n = test[i];
			index = i;

			/**
			 * compare all left components
			 * not the sorted ones
			 * find the minimum of the left
			 * save the minimum
			 */
			for (int j = i; j < test.length; j++) {
				if (test[j].compareTo(test[index]) < 0 ) {
					n = test[j];
					index = j;
				}
			}
			
			/**
			 * move all to right by one till the
			 * changed index
			 */
			while ( i + 1 <= index ) {
					test[index] = test[index - 1];
					index--;
			}
			test[i] = n;
		}
		return test;
	}
	
	@Override
	public String toString() {
		return "SelectionSort";
	}

}
