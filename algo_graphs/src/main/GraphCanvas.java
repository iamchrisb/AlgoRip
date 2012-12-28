package main;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import util.ripphausen.Edge;
import util.ripphausen.Graph;
import util.we.BreadthFirstSearch2d;
import util.we.GraphJSON;
import util.we.GraphJsonPojo;
import util.we.Vertex2d;
import util.we.Vertex2dSearchContent;

public class GraphCanvas extends Canvas {

	Graph<Vertex2d, Edge<Vertex2d>> graph;
	BreadthFirstSearch2d<Vertex2d, Edge<Vertex2d>> search = new BreadthFirstSearch2d<Vertex2d, Edge<Vertex2d>>();
	private int rad;
	private static final long serialVersionUID = 1L;
	private ArrayList<Vertex2d> vertices;
	private JPanel mainPanel;
	private JPanel choicePanelContainer;
	private JComboBox chooseAmount;
	private JButton refreshB;
	private String[] amounts = { "2 Points", "All Points" };

	private Vertex2d first;
	private Vertex2d second;
	private JTextArea txtOutput;
	private JPanel choicePanel;

	public static String TEST_RESSOURCE_PATH = new String(System.getProperty("user.dir") + "/res/graph3.json");

	public GraphCanvas(JFrame frame) {
		choicePanelContainer = new JPanel();
		choicePanelContainer.setLayout(new BorderLayout());

		chooseAmount = new JComboBox(amounts);
		choicePanel = new JPanel();
		choicePanel.add(chooseAmount);

		mainPanel = new JPanel();

		mainPanel.setLayout(new GridLayout(0, 2));
		mainPanel.add(this);
		mainPanel.add(choicePanelContainer);

		frame.setVisible(true);
		frame.setSize(800, 300);
		frame.add(mainPanel);

		setBackground(Color.white);

		refreshB = new JButton("Refresh");
		refreshB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtOutput.setText("");
				for (Iterator<Vertex2d> iterator = graph.getVertices().iterator(); iterator.hasNext();) {
					Vertex2d vertex = (Vertex2d) iterator.next();
					vertex.getContent().setColor(Color.green);
					vertex.getContent().setDistance(0);
					vertex.getContent().setPred(null);
				}

				for (Iterator<Edge<Vertex2d>> iterator = graph.getEdges().iterator(); iterator.hasNext();) {
					Edge<Vertex2d> edge = (Edge<Vertex2d>) iterator.next();
					edge.setColor(Color.black);
				}
				repaint();
			}
		});
		choicePanel.add(refreshB);
		txtOutput = new JTextArea();
		txtOutput.setRows(1);
		txtOutput.setColumns(1);
		txtOutput.setBackground(Color.gray);
		txtOutput.setCursor(Cursor.getDefaultCursor());
		txtOutput.setForeground(Color.white);

		// setting editable to false, crashes the gui

		choicePanelContainer.add(choicePanel, BorderLayout.NORTH);
		choicePanelContainer.add(txtOutput, BorderLayout.CENTER);

		//
		rad = 20;
		search.setCanvas(this);

		vertices = new ArrayList<Vertex2d>();
		vertices.add(new Vertex2d((int) (53), 40, 40, rad));
		vertices.add(new Vertex2d((int) (12), 40, 140, rad));
		vertices.add(new Vertex2d((int) (93), 140, 40, rad));
		vertices.add(new Vertex2d((int) (80), 140, 140, rad));
		vertices.add(new Vertex2d((int) (5), 180, 90, rad));
		vertices.add(new Vertex2d((int) (66), 260, 40, rad));
		vertices.add(new Vertex2d((int) (7), 260, 140, rad));
		vertices.add(new Vertex2d((int) (21), 260, 180, rad));
		vertices.add(new Vertex2d((int) (75), 220, 220, rad));
		//
		// for (int i = 0; i < vertices.length; i++) {
		// graph.addVertex(vertices[i]);
		// }

		Collection<Edge<Vertex2d>> edges = new ArrayList<Edge<Vertex2d>>();
		edges.add(new Edge<Vertex2d>(vertices.get(0), vertices.get(1)));
		edges.add(new Edge<Vertex2d>(vertices.get(1), vertices.get(0)));
		edges.add(new Edge<Vertex2d>(vertices.get(1), vertices.get(3)));
		edges.add(new Edge<Vertex2d>(vertices.get(3), vertices.get(1)));
		edges.add(new Edge<Vertex2d>(vertices.get(0), vertices.get(3)));
		edges.add(new Edge<Vertex2d>(vertices.get(3), vertices.get(0)));
		edges.add(new Edge<Vertex2d>(vertices.get(1), vertices.get(2)));
		edges.add(new Edge<Vertex2d>(vertices.get(2), vertices.get(1)));
		edges.add(new Edge<Vertex2d>(vertices.get(3), vertices.get(4)));
		edges.add(new Edge<Vertex2d>(vertices.get(4), vertices.get(3)));
		edges.add(new Edge<Vertex2d>(vertices.get(2), vertices.get(4)));
		edges.add(new Edge<Vertex2d>(vertices.get(4), vertices.get(2)));
		edges.add(new Edge<Vertex2d>(vertices.get(4), vertices.get(5)));
		edges.add(new Edge<Vertex2d>(vertices.get(5), vertices.get(4)));
		edges.add(new Edge<Vertex2d>(vertices.get(4), vertices.get(6)));
		edges.add(new Edge<Vertex2d>(vertices.get(6), vertices.get(4)));
		edges.add(new Edge<Vertex2d>(vertices.get(5), vertices.get(6)));
		edges.add(new Edge<Vertex2d>(vertices.get(6), vertices.get(5)));
		edges.add(new Edge<Vertex2d>(vertices.get(7), vertices.get(8)));
		edges.add(new Edge<Vertex2d>(vertices.get(8), vertices.get(7)));

		GraphJsonPojo<Vertex2d, Edge<Vertex2d>> gPojo = GraphJSON.getFromJSON(TEST_RESSOURCE_PATH);
		// Graph<Vertex2d, Edge<Vertex2d>> g2 = new Graph<Vertex2d,
		// Edge<Vertex2d>>(gra.getVertices(), gra.getEdges());
		Graph<Vertex2d, Edge<Vertex2d>> graph2 = new Graph<Vertex2d, Edge<Vertex2d>>(gPojo.getVertices(), gPojo.getEdges());
		Graph<Vertex2d, Edge<Vertex2d>> graph3 = new Graph<Vertex2d, Edge<Vertex2d>>(vertices , edges);
		graph = new Graph<Vertex2d, Edge<Vertex2d>>(vertices , edges );
		System.out.println();
//		graph = new Graph<Vertex2d, Edge<Vertex2d>>(gPojo.getVertices(), gPojo.getEdges());

		addMouseListener(new MouseAdapter() {

			public Vertex2d checkClick(MouseEvent e) {
				for (Iterator<Vertex2d> iterator = graph.getVertices().iterator(); iterator.hasNext();) {
					Vertex2d vert = (Vertex2d) iterator.next();
					if (vert.checkCollision(e.getX(), e.getY()) != null) {
						return vert;
					}
				}
				return null;
			}

			@Override
			public void mouseClicked(MouseEvent e) {

				if (first != null) {
					second = checkClick(e);
				}

				// this null-check MUST BE the second
				if (first == null) {
					first = checkClick(e);
					if (chooseAmount.getSelectedIndex() == 1) {
						search.searchShortestWayToAll(graph, first);
						first = null;
					}
				}

				if (first != null && second != null) {
					if (chooseAmount.getSelectedIndex() == 0) {
						search.searchShortestWayOfTwo(graph, first, second);
						first = null;
						second = null;
					}
				}

				repaint();
			}
		});
		repaint();
	}

	@Override
	public void paint(Graphics graphic) {
		super.paint(graphic);

		/**
		 * if no graph exists 
		 * there is nothing to paint
		 */
		if (graph == null) {
			return;
		}
		
		for (Edge<Vertex2d> edge : graph.getEdges()) {

			if (edge.getColor() == null)
				edge.setColor(Color.black);
			graphic.setColor(edge.getColor());
			graphic.drawLine(edge.getVertexA().getX(), edge.getVertexA().getY(), edge.getVertexB().getX(), edge.getVertexB().getY());
		}

		for (Vertex2d vertex : Collections.synchronizedCollection(graph.getVertices())) {
			vertex.draw(graphic);
			if (vertex.getContent() == null)
				vertex.setContent(new Vertex2dSearchContent());
			graphic.setColor(Color.black);
			graphic.drawString(new Integer(vertex.getId()).toString(), vertex.getX() - vertex.getRadius() / 5, vertex.getY() - vertex.getRadius() / 2);
		}
	}

	public String getTxtOutput() {
		return txtOutput.getText();
	}

	public void setTxtOutput(String txtOutput) {
		this.txtOutput.setText(txtOutput);
	}
}
