package main;

import interfaces.ISearch;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import util.ripphausen.Edge;
import util.ripphausen.Graph;
import util.we.Vertex2d;

public class GraphCanvas extends Canvas {

	public static void main(String[] args) {
		new GraphCanvas(new JFrame("GRAPHEN"));
		// Graph<Vertex, Edge<Vertex>> g = new Graph<Vertex, Edge<Vertex>>();
		// Vertex v = new Vertex(10);
		// System.out.println(v);
		// g.addVertex(new Vertex(0));
		// System.out.println(g.toString());
		// Graph<Vertex, Edge<Vertex>> g2 = new Graph<Vertex, Edge<Vertex>>(10);
		// System.out.println(g2);

		// String dat = System.getProperty("user.dir") + "\\test\\graph20.txt";
		// System.out.println(dat);

		// try {
		// Graph<Vertex, Edge<Vertex>> g3 = GraphLesen.FileToGraph(dat, true);
		// System.out.println(g3);
		// } catch (Exception e) {
		// }

	}

	Graph<Vertex2d, Edge<Vertex2d>> graph;
	ISearch search = new BreadthFirstSearch();
	private int rad;
	private static final long serialVersionUID = 1L;
	private Vertex2d[] vertices;
	private JPanel mainPanel;
	private JPanel choicePanel;
	private JComboBox chooseSearch;
	private JComboBox chooseAmount;
	private String[] amounts = { "2 Points", "All Points" };

	private Vertex2d first;
	private Vertex2d second;

	public GraphCanvas(JFrame frame) {
		ISearch[] searchArray = { search };
		choicePanel = new JPanel();
		chooseSearch = new JComboBox(searchArray);
		choicePanel.add(chooseSearch);
		chooseAmount = new JComboBox(amounts);
		choicePanel.add(chooseAmount);

		mainPanel = new JPanel();

		mainPanel.setLayout(new GridLayout(0, 2));
		mainPanel.add(this);
		mainPanel.add(choicePanel);

		frame.setVisible(true);
		frame.setSize(800, 300);
		frame.add(mainPanel);

		setBackground(Color.white);

		rad = 20;

		//
		search.setCanvas(this);
		graph = new Graph<Vertex2d, Edge<Vertex2d>>();
		// Vertex2d[] vertices = new Vertex2d[10];
		// for (int i = 0; i < vertices.length; i++) {
		// Vertex2d current = new Vertex2d(i,
		// (int) (Math.random() * frame.getWidth()),
		// (int) (Math.random() * frame.getHeight()), 10);
		// graph.addVertex(current);
		// vertices[i] = current;
		// }

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

		// for (int i = 1; i < vertices.length-1; i++) {
		// graph.addEdge(new Edge<Vertex2d>(vertices[i-1], vertices[i]));
		// }

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
					search.searchShortestWayToAll(graph, first);
					// for (Edge<Vertex2d> edge : Collections
					// .synchronizedCollection(graph
					// .getIncidentEdges(first))) {
					// System.out.println(edge);
					// edge.getVertexA().getContent().setColor(Color.black);
					// edge.getVertexB().getContent().setColor(Color.black);
					// edge.setColor(Color.red);
					// }
				}

				if (first != null && second != null) {
					search.searchShortestWayOfTwo(graph, first, second);
				}

				repaint();
			}
		});
	}

	@Override
	public void paint(Graphics graphic) {
		super.paint(graphic);
		// graphic.clearRect(0, 0, this.getWidth(), this.getHeight());

		for (Edge<Vertex2d> edge : Collections.synchronizedCollection(graph
				.getEdges())) {
			graphic.setColor(edge.getColor());
			graphic.drawLine(edge.getVertexA().getX(),
					edge.getVertexA().getY(), edge.getVertexB().getX(), edge
							.getVertexB().getY());
			// System.out.println(edge.getColor());
		}

		for (Vertex2d vertex : Collections.synchronizedCollection(graph
				.getVertices())) {
			vertex.draw(graphic);
			graphic.setColor(Color.black);
			graphic.drawString(new Integer(vertex.getId()).toString(),
					vertex.getX() - vertex.getRadius() / 5, vertex.getY()
							- vertex.getRadius() / 2);
		}

		// for (Iterator<Edge<Vertex2d>> iterator = graph.getEdges().iterator();
		// iterator
		// .hasNext();) {
		// Edge<Vertex2d> edge = (Edge<Vertex2d>) iterator.next();
		// graphic.setColor(edge.getColor());
		// graphic.drawLine(edge.getVertexA().getX(),
		// edge.getVertexA().getY(), edge.getVertexB().getX(), edge
		// .getVertexB().getY());
		// }

		// for (Iterator<Vertex2d> iterator = graph.getVertices().iterator();
		// iterator
		// .hasNext();) {
		// Vertex2d vert = (Vertex2d) iterator.next();
		// vert.draw(graphic);
		// }
	}

}
