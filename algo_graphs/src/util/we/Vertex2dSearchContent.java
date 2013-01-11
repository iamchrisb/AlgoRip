package util.we;

import java.awt.Color;

import util.ripphausen.Vertex;

public class Vertex2dSearchContent<V extends Vertex> {

	private int distance;
	private V pred;
	private Color color;
	private int x;
	private int y;
	private int radius;
	
	public Vertex2dSearchContent() {
		distance = 0;
		pred = null;
		color = Color.green;
	}
	
	public Vertex2dSearchContent(Color color, int distance, V pred){
		this.color = color;
		this.distance = distance;
		this.pred = pred;
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

	public V getPred() {
		return pred;
	}

	public void setPred(V pred) {
		this.pred = pred;
	}

}
