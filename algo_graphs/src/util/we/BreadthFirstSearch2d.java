package util.we;

import interfaces.ISearch;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import main.GraphCanvas;
import util.ripphausen.Edge;
import util.ripphausen.Graph;

public class BreadthFirstSearch2d<V extends Vertex2d, E extends Edge<V>> implements ISearch<V, E> {

	private GraphCanvas canvas;

	@Override
	public void search(Graph<V, E> g, V firstV) {

		if (firstV instanceof Vertex2d) {

			Vertex2d first = (Vertex2d) firstV;
			Queue<Vertex2d> queue = new LinkedList<Vertex2d>();

			first.getContent().setColor(Color.green);
			first.getContent().setDistance(0);
			first.getContent().setPred(null);
			
			queue.add(first);

			while (queue.size() != 0) {
				Vertex2d u = queue.poll();
				for (Iterator<V> iterator = g.getNeighbours(u.getId()).iterator(); iterator.hasNext();) {
					Vertex2d vertex2d = (Vertex2d) iterator.next();
//					System.out.println(vertex2d.getContent().getColor());
					if (vertex2d.getContent().getColor().equals(Color.green)) {
						vertex2d.getContent().setColor(Color.gray);
						vertex2d.getContent().setDistance(u.getContent().getDistance() + 1);
						vertex2d.getContent().setPred(u);
						queue.add(vertex2d);
					}
				}
				u.getContent().setColor(Color.black);
			}
		} else {
			System.out.println("could only be used with Vertex2d and Subclasses who have an instance of VertexSearchContext");
		}
	}

	@Override
	public void setCanvas(GraphCanvas canvas) {
		this.canvas = canvas;
	}

	public void searchShortestWayOfTwo(Graph<V, E> g, V start, V second) {

		search(g, start);
		ArrayList<E> wayList = new ArrayList<E>();
		Vertex2d predCur = (Vertex2d) second;

		while (predCur.getContent().getPred() != null) {
			for (E edge : Collections.synchronizedCollection(g.getIncidentEdges(predCur.getId()))) {
				if (predCur.getContent().getPred().getId() == (edge.getVertexB().getId())) {
					edge.setColor(Color.red);
					wayList.add(edge);
					break;
				}
			}
			predCur = (Vertex2d) predCur.getContent().getPred();
		}

		StringBuilder sb = new StringBuilder("");

		if (wayList.size() == 0) {
			sb.append("There is no shortest Way between: " + start.getId() + " and " + second.getId());
		} else {

			for (int i = wayList.size() - 1; i >= 0; i--) {
				sb.append(wayList.get(i).toString() + " dis: " + wayList.get(i).getVertexA().getContent().getDistance());
				if (i != 0) {
					sb.append(" -> ");
				}
			}
		}

		System.out.println(sb);
		canvas.setTxtOutput(canvas.getTxtOutput() + sb + "\n");

	}

	@Override
	public void searchShortestWayToAll(Graph<V, E> g, V start) {
		for (V vertex : Collections.synchronizedCollection(g.getVertices())) {
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
