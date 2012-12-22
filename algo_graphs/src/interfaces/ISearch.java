package interfaces;

import main.GraphCanvas;
import util.ripphausen.Edge;
import util.ripphausen.Graph;
import util.we.Vertex2d;

public interface ISearch {

	<V extends Vertex2d, E extends Edge<V>> void search(Graph<V, E> g, V first);

	<V extends Vertex2d, E extends Edge<V>> void searchShortestWayOfTwo(
			Graph<V, E> g, V start, V second);
	
	<V extends Vertex2d, E extends Edge<V>> void searchShortestWayToAll(
			Graph<V, E> g, V start);

	void setCanvas(GraphCanvas canvas);
}
