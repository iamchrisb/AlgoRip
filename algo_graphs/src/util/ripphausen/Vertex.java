package util.ripphausen;

import util.we.Vertex2dSearchContent;

/**
 * Eine Klasse, die Knoten eines Graphen repräsentiert
 * 
 * @author ripphausen
 * @version 1.0
 */
public class Vertex implements Comparable<Vertex> {
	private int id;
	private Vertex2dSearchContent<Vertex> content;

	public Vertex(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String toString() {
		return new Integer(id).toString();
	}

	@Override
	public int hashCode() {
		return new Integer(id).hashCode();
	}

	public Vertex2dSearchContent<Vertex> getContent() {
		return content;
	}

	public void setContent(Vertex2dSearchContent<Vertex> content) {
		this.content = content;
	}

	@Override
	public int compareTo(Vertex v2) {
		// implement how to compare vertices for the priority-queue
//		System.out.println(this.content.getDistance() + " - " + v2.getContent().getDistance() + " = " + (this.content.getDistance() - v2.getContent().getDistance()));
		if (this.content.getDistance() - v2.getContent().getDistance() == 0) {
			return this.id - v2.id;
		} return this.content.getDistance() - v2.getContent().getDistance();
	}
}