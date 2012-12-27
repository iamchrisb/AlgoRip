package main;

import interfaces.ISearch;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import util.ripphausen.Edge;
import util.ripphausen.Graph;
import util.ripphausen.Vertex;
import util.we.Vertex2d;

public class BreadthFirstSearch<V extends Vertex, E extends Edge<V>> implements
		ISearch<V, E> {

	private GraphCanvas canvas;

	@Override
	public void search(Graph<V, E> g, V firstV) {

		if (firstV instanceof Vertex2d) {

			Vertex2d first = (Vertex2d) firstV;
			Queue<Vertex2d> queue = new LinkedList<Vertex2d>();

			first.getContent().setColor(Color.green);
			first.getContent().setDistance(0);
			first.getContent().setPred(null);

			for (Iterator<V> iterator = g.getVertices().iterator(); iterator
					.hasNext();) {
				Vertex2d verStar = (Vertex2d) iterator.next();
				verStar.getContent().setColor(Color.green);
			}

			queue.add(first);

			while (queue.size() != 0) {
				Vertex2d u = queue.poll();
				for (Iterator<V> iterator = g.getNeighbours(u.getId()).iterator(); iterator
						.hasNext();) {
					Vertex2d vertex2d = (Vertex2d) iterator.next();
					if (vertex2d.getContent().getColor().equals(Color.green)) {
						vertex2d.getContent().setColor(Color.gray);
						vertex2d.getContent().setDistance(
								u.getContent().getDistance() + 1);
						vertex2d.getContent().setPred(u);
						queue.add(vertex2d);
					}
				}
				u.getContent().setColor(Color.black);
			}
		} else {
			System.out.println("could only be used with Vertex2d <- ...");
		}
	}

	@Override
	public void setCanvas(GraphCanvas canvas) {
		this.canvas = canvas;
	}

	public void searchShortestWayOfTwo(
			Graph<V, E> g, V start, V second) {

		search(g, start);
		ArrayList<E> wayList = new ArrayList<E>();
		Vertex2d predCur = (Vertex2d) second;

		while (predCur.getContent().getPred() != null) {
			for (E edge : Collections.synchronizedCollection(g
					.getIncidentEdges(predCur.getId()))) {
				// System.out.println("p-id: " +
				// predCur.getContent().getPred().getId() + " vb-id: " +
				// edge.getVertexB().getId() + " cond: " +
				// (predCur.getContent().getPred().getId() ==
				// edge.getVertexB().getId()));
				if (predCur.getContent().getPred().getId() == (edge
						.getVertexB().getId())) {
					edge.setColor(Color.red);
					wayList.add(edge);
					break;
				}
			}
			predCur = predCur.getContent().getPred();
		}

		StringBuilder sb = new StringBuilder("");
		for (int i = wayList.size() - 1; i >= 0; i--) {
			sb.append(wayList.get(i).toString());
			if (i != 0) {
				sb.append("->");
			}
		}

		if (wayList.size() == 0) {
			System.out.println("There is no shortest Way between: " + start
					+ " and " + second);
		} else {
			System.out.println(sb);
		}

	}

	@Override
	public void searchShortestWayToAll(
			Graph<V, E> g, V start) {
		for (V vertex : Collections.synchronizedCollection(g
				.getVertices())) {
			if (vertex.getId() != start.getId()) {
				searchShortestWayOfTwo(g, start, vertex);
			}
		}
	}

	@Override
	public String toString() {
		return "BreadthFirstSearch";
	}

}
