package main;

import javax.swing.JFrame;

import util.ripphausen.Edge;
import util.ripphausen.Graph;
import util.ripphausen.GraphLesen;
import util.ripphausen.Vertex;
import util.we.BreadthFirstSearch;

public class Main {

	public static void main(String[] args) {
		
		/**
		 * the 2d part with gui 
		 */
//		 new GraphCanvas(new JFrame("GRAPHEN"));
		
		/**
		 * the normal part without gui
		 */

		String str_test8 = System.getProperty("user.dir") + "/test/graph8.txt";
		String str_test9 = System.getProperty("user.dir") + "/test/graph9.txt";
		String str_test20 = System.getProperty("user.dir") + "/test/graph20.txt";
		
		Graph<Vertex, Edge<Vertex>> g20 = null;
		Graph<Vertex, Edge<Vertex>> g20_d = null;
		
		Graph<Vertex, Edge<Vertex>> g8 = null;
		Graph<Vertex, Edge<Vertex>> g8_d = null;
		
		Graph<Vertex, Edge<Vertex>> g9 = null;
		Graph<Vertex, Edge<Vertex>> g9_d = null;
		
		try {
			
			g20 = GraphLesen.FileToGraph(str_test20, false);
			g20_d = GraphLesen.FileToGraph(str_test20, true);
			
			g9 = GraphLesen.FileToGraph(str_test9, false);
			g9_d = GraphLesen.FileToGraph(str_test9, true);
			
			g8 = GraphLesen.FileToGraph(str_test8, false);
			g8_d = GraphLesen.FileToGraph(str_test8, true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		BreadthFirstSearch searchB = new BreadthFirstSearch();
		
		searchB.searchShortestWayOfTwo(g20, g20.getVertex(2), g20.getVertex(4), true);
		System.out.println();
		
		searchB.searchShortestWayToAll(g20, g20.getVertex(0), true);
		System.out.println();
		
		searchB.searchShortestWayToAll(g20_d, g20.getVertex(0), true);
		System.out.println();
		
		searchB.searchShortestWayToAll(g8, g8.getVertex(0), true);
		System.out.println();
		
		searchB.searchShortestWayToAll(g8_d, g8.getVertex(0), true);
		System.out.println();
		
		searchB.searchShortestWayToAll(g9, g9.getVertex(0), true);
		System.out.println();
		
		searchB.searchShortestWayToAll(g9_d, g9.getVertex(0), true);
		System.out.println();
	}
}