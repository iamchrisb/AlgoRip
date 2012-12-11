package util;

import java.awt.Color;
import java.util.LinkedList;
import java.util.Queue;


public class BreadthFirstSearch implements GraphSearch {

	/*
	 * Algorithmus Breitensuche (s) Sei s Startknoten für Breitensuche; Q
	 * sei leere Queue col[s] = grau; dist[s] = 0; pred[s] = null;
	 * Enqueue(Q, s); 
	 * for (v el V \ {s}) col[v] = weiß; dist[v] = MAXINT;
	 * pred[v] = null; while (Q != leer) u = Dequeue(Q); for (v el Adj(u)) //
	 * für alle Nachbarn von u if (col[v] == weiß) // d.h. nicht besucht
	 * col[v] = grau; dist[v] = dist[u] + 1; pred[v] = u; Enqueue(Q, v);
	 * col[u] = schwarz;
	 */
	@Override
	public <V extends Vertex, E extends Edge<V>> void search(Graph<V, E> g,
			V first) {
		Color col = Color.green;
		int distance = 0;
		Vertex pred = null;

		Queue<Vertex> queue = new LinkedList<Vertex>();
		queue.addAll(g.getNeighbours(first));
		
	}

//	@Override
//	public <V extends Vertex, E extends Edge<V>> void search(Graph<V, E> g, V first) {
//		Color col = Color.green;
//		int distance = 0;
//		Vertex pred = null;
//
//		Queue<Vertex> queue = new LinkedList<Vertex>();
//		
//		g.getNeighbours(first);
//	}

}
