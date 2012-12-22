package main;

import javax.swing.JFrame;

public class Main {

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
}