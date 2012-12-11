package util;

import java.awt.Color;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;


public class BreadthFirstSearch implements ISearch {

	@Override
	public <V extends Vertex2d, E extends Edge<V>> void search(Graph<V, E> g,
			V first) {

			Queue<Vertex2d> queue = new LinkedList<Vertex2d>();
		
			first.getContent().setColor(Color.green);
			first.getContent().setDistance(Integer.MAX_VALUE);
			first.getContent().setPred(null);
			
			for (Iterator iterator = g.getVertices().iterator(); iterator.hasNext();) {
				Vertex2d verStar = (Vertex2d) iterator.next();
				verStar.getContent().setColor(Color.green);
			}
			
			queue.add(first);
			
			while(queue.size() != 0) {
				Vertex2d u = queue.poll();
				queue.addAll(g.getNeighbours((V) u));
				for (Iterator<V> iterator = g.getNeighbours(u.getId()).iterator(); iterator.hasNext();) {
					Vertex2d vertex2d = (Vertex2d) iterator.next();
					if(vertex2d.getContent().getColor().equals(Color.green)) {
						vertex2d.getContent().setColor(Color.gray);
						vertex2d.getContent().setDistance(u.getContent().getDistance() + 1);
						vertex2d.getContent().setPred(u);
					}
				}
				u.getContent().setColor(Color.black);
			}

			
	}

	/*
	 * Algorithmus Breitensuche (s) Sei s Startknoten für Breitensuche; 
	 * Q sei leere Queue 
	 * col[s] = grau; dist[s] = 0; pred[s] = null;
	 * Enqueue(Q, s); 
	 * for (v el V \ {s}) col[v] = weiß; dist[v] = MAXINT;
	 * pred[v] = null; while (Q != leer) u = Dequeue(Q); for (v el Adj(u)) //
	 * für alle Nachbarn von u if (col[v] == weiß) // d.h. nicht besucht
	 * col[v] = grau; dist[v] = dist[u] + 1; pred[v] = u; Enqueue(Q, v);
	 * col[u] = schwarz;
	 */
	

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
