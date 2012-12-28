package util.we;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import util.ripphausen.Edge;
import util.ripphausen.Graph;
import util.ripphausen.Vertex;

/**
 * 
 * @author Christopher Bleckmann, Christoph Nützel, Norman Reszka
 * 
 *         <h1>BreadthFirstSearch</h1>
 *         <p>
 *         this class defines the BreadthFirstSearch for Graphs without 2d
 *         displaying
 *         </p>
 * 
 */
public class BreadthFirstSearch {

	/**
	 * this is the core method for the other both search-methods
	 * 
	 * @param g
	 *            the graph where to visit all reachable vertices
	 * @param firstV
	 *            the start-vertex
	 * @return returns a hashmap of <Vertex, Vertex2dSearchContent<Vertex>> with
	 *         all distances, colors, preds to be reused
	 */
	public HashMap<Vertex, Vertex2dSearchContent<Vertex>> search(Graph<Vertex, Edge<Vertex>> g, Vertex firstV) {

		Vertex first = firstV;
		// init the queue
		Queue<Vertex> queue = new LinkedList<Vertex>();
		HashMap<Vertex, Vertex2dSearchContent<Vertex>> map = new HashMap<Vertex, Vertex2dSearchContent<Vertex>>();
		map.put(first, new Vertex2dSearchContent<Vertex>(Color.green, 0, null));
		// fill every content with init stuff
		for (Iterator<Vertex> iterator = g.getVertices().iterator(); iterator.hasNext();) {
			Vertex vertex = (Vertex) iterator.next();
			map.put(vertex, new Vertex2dSearchContent<Vertex>(Color.green, 0, null));
		}

		// add the start vertex to the queue
		queue.add(first);
		
		// iterate over the queue
		while (queue.size() != 0) {
			// remove the "first" vertex from the queue
			Vertex u = queue.poll();
			// iterate over all neighbours of the removed vertex
			for (Iterator<Vertex> iterator = g.getNeighbours(u.getId()).iterator(); iterator.hasNext();) {
				Vertex vertex = iterator.next();
				// check if the vertex was visited before
				if (map.get(vertex).getColor().equals(Color.green)) {
					map.get(vertex).setColor(Color.gray);
					map.get(vertex).setDistance(map.get(u).getDistance() + 1);
					map.get(vertex).setPred(u);
					queue.add(vertex);
				}
			}
			map.get(u).setColor(Color.black);
		}

		return map;
	}

	/**
	 * this method shall find a way between two vertices of a graph
	 * 
	 * @param g
	 *            the graph where to search the way
	 * @param start
	 *            the vertex where the search starts
	 * @param second
	 *            the vertex where the search ends
	 * @return null if one of the vertex doesn't fit else an arraylist of edges
	 *         that represents the way
	 */
	public ArrayList<Edge<Vertex>> searchShortestWayOfTwo(Graph<Vertex, Edge<Vertex>> g, Vertex start, Vertex second, boolean print) {

		if (start == null || second == null)
			return null;

		HashMap<Vertex, Vertex2dSearchContent<Vertex>> map = search(g, start);
		ArrayList<Edge<Vertex>> wayList = new ArrayList<Edge<Vertex>>();
		Vertex predCur = second;
		
		/**
		 * iterate over all incidentEdges of the "last" vertex 
		 * set the last to the forlast till null
		 */
		while (map.get(predCur).getPred() != null) {
			for (Edge<Vertex> edge : g.getIncidentEdges(predCur.getId())) {
				if (map.get(predCur).getPred().getId() == (edge.getVertexB().getId())) {
					edge.setColor(Color.red);
					wayList.add(edge);
					break;
				}
			}
			predCur = map.get(predCur).getPred();
		}

		if (print) {
			StringBuilder sb = new StringBuilder("");

			if (wayList.size() == 0) {
				sb.append("There is no shortest Way between: " + start.getId() + " and " + second.getId());
			} else {

				for (int i = wayList.size() - 1; i >= 0; i--) {
					sb.append(wayList.get(i).toString() + " dis: " + map.get(wayList.get(i).getVertexA()).getDistance());
					if (i != 0) {
						sb.append(" -> ");
					}
				}
			}
			System.out.println(sb);
		}

		if (wayList.size() != 0)
			return wayList;
		return null;
	}

	/**
	 * this method shall find a way to all vertices of a given start-vertex
	 * 
	 * @param g
	 *            the graph where to find the ways
	 * @param start
	 *            the vertex where the search starts ( uses the
	 *            shortestWayOfTwo-method with all other vertices of the graph )
	 * @return returns null if the start vertex doesn't exist
	 */
	public ArrayList<ArrayList<Edge<Vertex>>> searchShortestWayToAll(Graph<Vertex, Edge<Vertex>> g, Vertex start, boolean print) {
		ArrayList<ArrayList<Edge<Vertex>>> fullWayList = new ArrayList<ArrayList<Edge<Vertex>>>();
		
		// use the getShortestWayOfTwo with all vertices
		for (Vertex vertex : g.getVertices()) {
			if (vertex.getId() != start.getId()) {
				fullWayList.add(searchShortestWayOfTwo(g, start, vertex, print));
			}
		}

		if (fullWayList.size() != 0)
			return fullWayList;
		return null;
	}
}
