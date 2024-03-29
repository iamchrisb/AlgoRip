package algo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Date;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import algo.sort.HeapSort;
import algo.sort.InsertionSort;
import algo.sort.MergeSort;
import algo.sort.QuickSort;
import algo.sort.SelectionSort;
import algo.sort.Sort;
import algo.util.FileIntArray;
import algo.util.FileTestSave;
import algo.util.TestSortTime;

public class SortView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// needed arrays
	private Integer[] array_to_sort;
	private Integer[] array_ressource;

	// sort classes
	private SelectionSort selectionsort = new SelectionSort();
	private InsertionSort insertsort = new InsertionSort();
	private MergeSort mergesort = new MergeSort();
	private HeapSort heapsort = new HeapSort();
	private QuickSort quicksort = new QuickSort();
	private Sort[] so = { selectionsort, insertsort, mergesort, heapsort,
			quicksort };

	// gui needed crap
	private JTextArea sortedArrayTextArea;
	private JTextArea chosenArrayTextArea;
	private JComboBox sortBox;
	private JLabel time;
	private JComboBox fileArrayComboBox;
	private JTextField randomArrayLengthTF;

	private JFrame frame;

	private File ARRAY_RESSOURCE_PATH = new File(System.getProperty("user.dir")
			+ "/res/arrays/");
	public static String TEST_RESSOURCE_PATH = new String(
			System.getProperty("user.dir") + "/res/test/tests.txt");
	private TestSortTime testSortTime = new TestSortTime();

	public SortView() {

		frame = this;
		this.setLayout(new GridLayout(0, 2));

		JPanel leftPA = new JPanel();
		leftPA.setBounds(200, 200, this.getWidth() / 2, this.getHeight());

		JPanel rightPA = new JPanel();
		rightPA.setBounds(200 + this.getWidth() / 2, 200, this.getWidth() / 2,
				this.getHeight());
		rightPA.setBackground(Color.lightGray);

		this.add(leftPA);
		this.add(rightPA);

		leftPA.setLayout(new BorderLayout());
		rightPA.setLayout(new BorderLayout());

		// fill with Sort-Array
		sortBox = new JComboBox(so);

		JLabel l = new JLabel("choose to sort:");
		JButton b = new JButton("sort!");
		JButton generate = new JButton("generate");
		randomArrayLengthTF = new JTextField();
		JPanel northpa = new JPanel();

		northpa.add(l);
		northpa.add(sortBox);
		northpa.add(b);

		leftPA.add(northpa, BorderLayout.NORTH);

		chosenArrayTextArea = new JTextArea(20, 20);
		leftPA.add(new JScrollPane(chosenArrayTextArea), BorderLayout.CENTER);
		GridLayout g = new GridLayout(0, 3);
		JPanel leftSouthPA = new JPanel(g);
		leftSouthPA.add(randomArrayLengthTF);
		leftSouthPA.add(generate);
		time = new JLabel("foobar");
		leftSouthPA.add(time);
		leftPA.add(leftSouthPA, BorderLayout.SOUTH);

		JLabel res = new JLabel("Result: ");
		sortedArrayTextArea = new JTextArea(20, 20);

		fileArrayComboBox = new JComboBox(ARRAY_RESSOURCE_PATH.listFiles());
		fileArrayComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread tr3 = new Thread(runnableChoseFromFile);
				tr3.start();
			}
		});

		rightPA.add(res, BorderLayout.SOUTH);
		rightPA.add(fileArrayComboBox, BorderLayout.NORTH);
		rightPA.add(new JScrollPane(sortedArrayTextArea), BorderLayout.CENTER);

		this.setVisible(true);
		this.setBounds(0, 0, 1000, 600);

		generate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (randomArrayLengthTF.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "MUST NOT BE EMPTY!");
				} else {
					Thread tr2 = new Thread(runnableGenerate);
					tr2.start();
				}
			}
		});

		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread tr1 = new Thread(runnableSort);
				tr1.start();
			}
		});

		randomArrayLengthTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				@SuppressWarnings("static-access")
				String str = arg0.getKeyText(arg0.getKeyCode());
				System.out.println(str);
				if (str.matches("[0-9]|R�cktaste")) {
				} else {
					JOptionPane.showMessageDialog(frame, "ONLY NUMBERS!!");
					randomArrayLengthTF.setText("");
				}
			}
		});
	}

	Runnable runnableChoseFromFile = new Runnable() {
		@Override
		public void run() {
			File current = ((File) fileArrayComboBox.getSelectedItem());
			testSortTime.setArrayname(current.getName());
			chosenArrayTextArea.setText("");
			sortedArrayTextArea.setText("");
			Integer[] p = FileIntArray.FileToIntegerArray(current
					.getAbsolutePath());
			array_to_sort = p;
			for (int i = 0; i < p.length; i++) {
				chosenArrayTextArea.setText(chosenArrayTextArea.getText()
						+ p[i] + "\n");
			}
		};
	};

	Runnable runnableGenerate = new Runnable() {
		@Override
		public void run() {
			Integer length = new Integer(randomArrayLengthTF.getText());
			array_to_sort = new Integer[length];
			testSortTime.setArrayname("Generated");
			chosenArrayTextArea.setText("");
			sortedArrayTextArea.setText("");
			Random rand = new Random();
			for (int i = 0; i < array_to_sort.length; i++) {
				int j = (rand.nextInt(length));
				array_to_sort[i] = j;
				chosenArrayTextArea.setText(chosenArrayTextArea.getText() + j
						+ "\n");
			}
		}
	};

	Runnable runnableSort = new Runnable() {
		@Override
		public void run() {

			/* clear the textarea */
			sortedArrayTextArea.setText("");

			/* count the time and sort */
			Long start = System.nanoTime();
			array_ressource = ((Sort) sortBox.getSelectedItem())
					.sort(array_to_sort);
			start = System.nanoTime() - start;
			time.setText(start.toString());

			/* fill the tests */
			testSortTime.setTime(start);
			testSortTime.setSort(sortBox.getSelectedItem().toString());
			testSortTime.setArray_length(array_ressource.length);
			testSortTime.setDate(new Date().toString());
			FileTestSave.addStringToFile(TEST_RESSOURCE_PATH,
					testSortTime.toString());

			/* fill the textarea */
			for (int i = 0; i < array_ressource.length; i++) {
				sortedArrayTextArea.setText(sortedArrayTextArea.getText()
						+ array_ressource[i] + "\n");
			}
		}
	};
}
