package util;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.JFrame;

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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vertex2d[] vertices;

	public GraphCanvas(JFrame frame) {
		frame.setVisible(true);
		frame.setSize(500, 500);
		frame.add(this);
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

		vertices = new Vertex2d[7];
		vertices[0] = new Vertex2d((int) (Math.random() * 100), 40, 40, rad);
		vertices[1] = new Vertex2d((int) (Math.random() * 100), 40, 140, rad);
		vertices[2] = new Vertex2d((int) (Math.random() * 100), 140, 40, rad);
		vertices[3] = new Vertex2d((int) (Math.random() * 100), 140, 140, rad);
		vertices[4] = new Vertex2d((int) (Math.random() * 100), 180, 90, rad);
		vertices[5] = new Vertex2d((int) (Math.random() * 100), 260, 40, rad);
		vertices[6] = new Vertex2d((int) (Math.random() * 100), 260, 140, rad);

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

		// for (int i = 1; i < vertices.length-1; i++) {
		// graph.addEdge(new Edge<Vertex2d>(vertices[i-1], vertices[i]));
		// }

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				for (Iterator<Vertex2d> iterator3 = graph.getVertices()
//						.iterator(); iterator3.hasNext();) {
//					Vertex2d ca = (Vertex2d) iterator3.next();
//					ca.setColor(Color.GREEN);
//				}
//
//				for (Iterator<Vertex2d> iterator = graph.getVertices()
//						.iterator(); iterator.hasNext();) {
//					Vertex2d vert = (Vertex2d) iterator.next();
//					if (vert.checkCollision(e.getX(), e.getY())) {
//						vert.setColor(Color.red);
//						for (Iterator<Vertex2d> iterator2 = graph
//								.getNeighbours(vert).iterator(); iterator2
//								.hasNext();) {
//							Vertex2d cc = (Vertex2d) iterator2.next();
//							cc.setColor(Color.RED);
//						}
//					}
//					
//				}
				Graph<Vertex2d,Edge<Vertex2d>> g = graph;
				for (Iterator<Vertex2d> iterator = graph.getVertices().iterator(); iterator
						.hasNext();) {
					Vertex2d vert = (Vertex2d) iterator.next();
					if(vert.checkCollision(e.getX(), e.getY()) != null ) {
						search.search(graph, vert);
					}
				}
				repaint();
			}
		});
	}

	@Override
	public void paint(Graphics graphic) {
		super.paint(graphic);

		for (Iterator<Edge<Vertex2d>> iterator = graph.getEdges().iterator(); iterator
				.hasNext();) {
			Edge<Vertex2d> edge = (Edge<Vertex2d>) iterator.next();
			graphic.setColor(Color.black);
			graphic.drawLine(edge.getVertexA().getX(),
					edge.getVertexA().getY(), edge.getVertexB().getX(), edge
							.getVertexB().getY());
		}
		
		for (Iterator<Vertex2d> iterator = graph.getVertices().iterator(); iterator
				.hasNext();) {
			Vertex2d vert = (Vertex2d) iterator.next();
			vert.draw(graphic);
		}
	}

}
