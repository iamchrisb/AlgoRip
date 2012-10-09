package algo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import algo.sort.HeapSort;
import algo.sort.InsertionSort;
import algo.sort.MergeSort;
import algo.sort.QuickSort;
import algo.sort.SelectionSort;
import algo.sort.Sort;

public class SortView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer[] array = new Integer[20];
	private Integer[] res_array = new Integer[20];

	private SelectionSort selectionsort = new SelectionSort();
	private InsertionSort insertsort = new InsertionSort();
	private MergeSort mergesort = new MergeSort();
	private HeapSort heapsort = new HeapSort();
	private QuickSort quicksort = new QuickSort();
	private Sort[] so = { selectionsort, insertsort, mergesort, heapsort, quicksort };

	private JTextArea tres;
	private JTextArea ta;
	private JComboBox box;

	public SortView() {

		this.setLayout(new GridLayout(0, 2));

		JPanel left = new JPanel();
		left.setBounds(200, 200, this.getWidth() / 2, this.getHeight());

		JPanel right = new JPanel();
		right.setBounds(200 + this.getWidth() / 2, 200, this.getWidth() / 2,
				this.getHeight());
		right.setBackground(Color.lightGray);

		this.add(left);
		this.add(right);

		left.setLayout(new BorderLayout());
		right.setLayout(new BorderLayout());

		//fill with Sort-Array
		box = new JComboBox(so);
		
		JLabel l = new JLabel("choose to sort:");
		JButton b = new JButton("sort!");
		JButton val = new JButton("generate");
		JPanel northpa = new JPanel();

		northpa.add(l);
		northpa.add(box);
		northpa.add(b);

		left.add(northpa, BorderLayout.NORTH);

		ta = new JTextArea(20, 20);
		left.add(new JScrollPane(ta), BorderLayout.CENTER);
		left.add(val, BorderLayout.SOUTH);

		JLabel res = new JLabel("Result: ");
		tres = new JTextArea(20, 20);

		right.add(res, BorderLayout.NORTH);
		right.add(new JScrollPane(tres), BorderLayout.CENTER);

		this.setVisible(true);
		this.setBounds(0, 0, 800, 600);

		val.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ta.setText("");
				tres.setText("");
				Random rand = new Random();
				for (int i = 0; i < array.length; i++) {
					int j = (rand.nextInt(100));
					array[i] = j;
					ta.setText(ta.getText() + j + "\n");
				}
			}
		});

		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				tres.setText("");
				res_array = ((Sort) box.getSelectedItem()).sort(array);
				for (int i = 0; i < res_array.length; i++) {
					tres.setText(tres.getText() + res_array[i] + "\n");
				}
			}
		});
	}
}
