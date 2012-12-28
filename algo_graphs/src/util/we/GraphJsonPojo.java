package util.we;

import java.util.ArrayList;

import util.ripphausen.Edge;

public class GraphJsonPojo<V extends Vertex2d, E extends Edge<V>> {

	private ArrayList<V> vertices;
	private ArrayList<E> edges;
	
	public ArrayList<V> getVertices() {
		return vertices;
	}
	public ArrayList<E> getEdges() {
		return edges;
	}
	
	

}
