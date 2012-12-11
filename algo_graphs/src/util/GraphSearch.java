package util;

public interface GraphSearch {

	<V extends Vertex, E extends Edge<V>> void search(Graph<V, E> g, V first);
}
