package algo.sort;

import algo.view.SortView;

public class Main {

	public static void main(String[] args) {

		SortView s = new SortView();
		SelectionSort sels = new SelectionSort();
		Integer[] i = { 6, 8, 9, 3, 2, 0, 1, 5 } ;
		sels.sort(i);
		
		SelectionSort selst = new SelectionSort();
		String[] sa = { "Hans" , "Lydi" , "Penis" , "Lou" , "Erni" , "Jonson", "Sarah" , "Chris" };
		selst.sort(sa);
		
	}
}
