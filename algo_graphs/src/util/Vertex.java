package util;

/** Eine Klasse, die Knoten eines Graphen repr�sentiert
 * 
 * @author ripphausen
 * @version 1.0
 */
public abstract class Vertex {
	private int id;

	public Vertex(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public String toString() {
		return new Integer(id).toString();
	}
}