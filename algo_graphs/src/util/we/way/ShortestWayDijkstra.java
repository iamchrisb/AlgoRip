package util.we.way;

import java.awt.Color;
import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.PriorityQueue;

import util.ripphausen.Edge;
import util.ripphausen.Graph;
import util.ripphausen.Vertex;
import util.we.Vertex2dSearchContent;

public class ShortestWayDijkstra<V extends Vertex, E extends Edge<V>> {

	private PriorityQueue<V> q;

	/**
	 * Initialisierung eines Relaxations- Algorithmus für Wege mit Startknoten s
	 * Initialize-Single-Source(G, s) for (v є V) dist[v] = MAXDOUBLE; pred[v] =
	 * null; dist[s] = 0;
	 */

	private void initSingleSource(Graph<V, E> g, V s) {
		for (V vertex : g.getVertices()) {
			vertex.setContent(new Vertex2dSearchContent<Vertex>(Color.green,
					Integer.MAX_VALUE, null));
		}
	}

	/**
	 * Relax(u,v,w) (u,v) Kante, w Gewichtsfunktion if (dist[v] > dist[u] +
	 * w(u,v)) dist[v] = dist[u] + w(u,v); pred[v] = u;
	 */

	/**
	 * 
	 * @param u
	 *            "old" vertex
	 * @param v
	 *            "new" vertex
	 * @param weight
	 * @throws InvalidAlgorithmParameterException 
	 */
	private void relax(V u, V v, int value) throws InvalidAlgorithmParameterException {
		int weight = value;
		if (u.getContent().getDistance() + weight > 0) {
			if (v.getContent().getDistance() > weight) {
				q.remove(v);
				v.getContent()
						.setDistance(u.getContent().getDistance() + value);
				q.add(v);
				v.getContent().setPred(u);
			}
		}else{
			throw new InvalidAlgorithmParameterException("weight must not be negative!");
		}
	}

	/**
	 * Dijkstra(G, w, s) Initialize-Single-Source(G, s); S = Ø; // alle von s
	 * erreichten Knoten Q = V(G); // am Anfang alle Knoten in Q while (Q != Ø)
	 * u = Extract-Min(Q); // Min bzgl. dist S = S.add(u); for (v є Adj(u))
	 * Relax(u,v,w);
	 * @throws InvalidAlgorithmParameterException 
	 */
	public void search(Graph<V, E> graph, V s) throws InvalidAlgorithmParameterException {

		initSingleSource(graph, s);
		graph.getVertex(s.getId()).setContent(
				new Vertex2dSearchContent<Vertex>(Color.green, 0, null));
		// S
		ArrayList<V> reached = new ArrayList<V>();
		// Q
		q = new PriorityQueue<V>(graph.getVertices());

		while (q.size() != 0) {
			V u = q.poll();
			reached.add(u);
			for (V v : graph.getNeighbours(u.getId())) {
				E e = null;
				for (E edge : graph.getIncidentEdges(u.getId())) {
					if (edge.getVertexA().getId() == u.getId()
							&& edge.getVertexB().getId() == v.getId()) {
						e = edge;
						break;
					}
				}
				relax(u, v, e.getWeight());
			}
		}
	}
}
