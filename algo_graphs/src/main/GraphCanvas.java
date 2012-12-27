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
import java.util.Collections;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import util.ripphausen.Edge;
import util.ripphausen.Graph;
import util.we.Vertex2d;

public class GraphCanvas extends Canvas {

	Graph<Vertex2d, Edge<Vertex2d>> graph;
	BreadthFirstSearch<Vertex2d, Edge<Vertex2d>> search = new BreadthFirstSearch<Vertex2d, Edge<Vertex2d>>();
	private int rad;
	private static final long serialVersionUID = 1L;
	private Vertex2d[] vertices;
	private JPanel mainPanel;
	private JPanel choicePanelContainer;
	private JComboBox chooseAmount;
	private JButton refreshB;
	private String[] amounts = { "2 Points", "All Points" };

	private Vertex2d first;
	private Vertex2d second;
	private JTextArea txtOutput;
	private JPanel choicePanel;

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
				for (Iterator<Vertex2d> iterator = graph.getVertices()
						.iterator(); iterator.hasNext();) {
					Vertex2d vertex = (Vertex2d) iterator.next();
					vertex.getContent().setColor(Color.green);
					vertex.getContent().setDistance(0);
					vertex.getContent().setPred(null);
				}

				for (Iterator<Edge<Vertex2d>> iterator = graph.getEdges()
						.iterator(); iterator.hasNext();) {
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
//		txtOutput.setEditable(false);
		txtOutput.setForeground(Color.white);
		choicePanelContainer.add(choicePanel , BorderLayout.NORTH);
		choicePanelContainer.add(txtOutput , BorderLayout.CENTER);
		
		//
		rad = 20;
		search.setCanvas(this);
		graph = new Graph<Vertex2d, Edge<Vertex2d>>();

		vertices = new Vertex2d[9];
		vertices[0] = new Vertex2d((int) (Math.random() * 100), 40, 40, rad);
		vertices[1] = new Vertex2d((int) (Math.random() * 100), 40, 140, rad);
		vertices[2] = new Vertex2d((int) (Math.random() * 100), 140, 40, rad);
		vertices[3] = new Vertex2d((int) (Math.random() * 100), 140, 140, rad);
		vertices[4] = new Vertex2d((int) (Math.random() * 100), 180, 90, rad);
		vertices[5] = new Vertex2d((int) (Math.random() * 100), 260, 40, rad);
		vertices[6] = new Vertex2d((int) (Math.random() * 100), 260, 140, rad);
		vertices[7] = new Vertex2d((int) (Math.random() * 100), 260, 180, rad);
		vertices[8] = new Vertex2d((int) (Math.random() * 100), 220, 220, rad);

		for (int i = 0; i < vertices.length; i++) {
			graph.addVertex(vertices[i]);
		}

		graph.addEdge(new Edge<Vertex2d>(vertices[0], vertices[1]));
		graph.addEdge(new Edge<Vertex2d>(vertices[1], vertices[0]));
		graph.addEdge(new Edge<Vertex2d>(vertices[1], vertices[3]));
		graph.addEdge(new Edge<Vertex2d>(vertices[3], vertices[1]));
		graph.addEdge(new Edge<Vertex2d>(vertices[0], vertices[3]));
		graph.addEdge(new Edge<Vertex2d>(vertices[3], vertices[0]));
		graph.addEdge(new Edge<Vertex2d>(vertices[1], vertices[2]));
		graph.addEdge(new Edge<Vertex2d>(vertices[2], vertices[1]));
		graph.addEdge(new Edge<Vertex2d>(vertices[3], vertices[4]));
		graph.addEdge(new Edge<Vertex2d>(vertices[4], vertices[3]));
		graph.addEdge(new Edge<Vertex2d>(vertices[2], vertices[4]));
		graph.addEdge(new Edge<Vertex2d>(vertices[4], vertices[2]));
		graph.addEdge(new Edge<Vertex2d>(vertices[4], vertices[5]));
		graph.addEdge(new Edge<Vertex2d>(vertices[5], vertices[4]));
		graph.addEdge(new Edge<Vertex2d>(vertices[4], vertices[6]));
		graph.addEdge(new Edge<Vertex2d>(vertices[6], vertices[4]));
		graph.addEdge(new Edge<Vertex2d>(vertices[5], vertices[6]));
		graph.addEdge(new Edge<Vertex2d>(vertices[6], vertices[5]));
		graph.addEdge(new Edge<Vertex2d>(vertices[7], vertices[8]));
		graph.addEdge(new Edge<Vertex2d>(vertices[8], vertices[7]));

		for (Iterator<Edge<Vertex2d>> iterator = graph.getEdges().iterator(); iterator
				.hasNext();) {
			Edge<Vertex2d> edge = (Edge<Vertex2d>) iterator.next();
			edge.setColor(Color.blue);
		}

		addMouseListener(new MouseAdapter() {

			public Vertex2d checkClick(MouseEvent e) {
				for (Iterator<Vertex2d> iterator = graph.getVertices()
						.iterator(); iterator.hasNext();) {
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
	}

	@Override
	public void paint(Graphics graphic) {
		super.paint(graphic);

		for (Edge<Vertex2d> edge : Collections.synchronizedCollection(graph
				.getEdges())) {
			graphic.setColor(edge.getColor());
			graphic.drawLine(edge.getVertexA().getX(),
					edge.getVertexA().getY(), edge.getVertexB().getX(), edge
							.getVertexB().getY());
		}

		for (Vertex2d vertex : Collections.synchronizedCollection(graph
				.getVertices())) {
			vertex.draw(graphic);
			graphic.setColor(Color.black);
			graphic.drawString(new Integer(vertex.getId()).toString(),
					vertex.getX() - vertex.getRadius() / 5, vertex.getY()
							- vertex.getRadius() / 2);
		}
	}
	
	public String getTxtOutput() {
		return txtOutput.getText();
	}

	public void setTxtOutput(String txtOutput) {
		this.txtOutput.setText(txtOutput);
	}
}

