package util;

public interface ISearch {

	<V extends Vertex2d, E extends Edge<V>> void search(Graph<V, E> g, V first);
}
