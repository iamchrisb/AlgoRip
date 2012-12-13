package util;

import java.awt.Graphics;

public interface ISearch {

	<V extends Vertex2d, E extends Edge<V>> void search(Graph<V, E> g, V first);
	void setCanvas(GraphCanvas canvas);
}
