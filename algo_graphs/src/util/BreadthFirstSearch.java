package util;

import java.awt.Color;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;


public class BreadthFirstSearch implements ISearch {
	
	private GraphCanvas canvas;

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
				System.out.println("while");
				Vertex2d u = queue.poll();
//				queue.addAll(g.getNeighbours((V) u));
				
				for (Iterator iterator = g.getNeighbours(u.getId()).iterator(); iterator.hasNext();) {
					Vertex2d vertex2d = (Vertex2d) iterator.next();
					if(vertex2d.getContent().getColor().equals(Color.green)) {
						vertex2d.getContent().setColor(Color.gray);
						vertex2d.getContent().setDistance(u.getContent().getDistance() + 1);
						vertex2d.getContent().setPred(u);
						queue.add(vertex2d);
						if(canvas != null) canvas.repaint();
					}
				}
				u.getContent().setColor(Color.black);
			}
	}

	@Override
	public void setCanvas(GraphCanvas canvas) {
		// TODO Auto-generated method stub
		this.canvas = canvas;
	}

}
