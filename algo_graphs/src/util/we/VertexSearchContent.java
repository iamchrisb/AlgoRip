package util.we;

import java.awt.Color;


public class VertexSearchContent {

	private int distance;
	private Vertex2d pred;
	private Color color;
	
	public VertexSearchContent() {
		distance = 0;
		pred = null;
		color = Color.green;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public Vertex2d getPred() {
		return pred;
	}

	public void setPred(Vertex2d pred) {
		this.pred = pred;
	}

}
