package util.we;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

import util.ripphausen.Vertex;

public class Vertex2d extends Vertex {

	private Vector<Integer> position;
	private int radius;
	private VertexSearchContent content;

	public VertexSearchContent getContent() {
		return content;
	}

	public void setContent(VertexSearchContent content) {
		this.content = content;
	}

	public Vertex2d(int id, int x, int y, int radius) {
		super(id);
		position = new Vector<Integer>();
		position.add(0, x);
		position.add(1, y);
		this.radius = radius;
		content = new VertexSearchContent();
		content.setColor(Color.green);
	}

	public int getX() {
		return position.get(0);
	}

	public void setX(int x) {
		position.set(0, x);
	}

	public int getY() {
		return position.get(1);
	}

	public void setY(int y) {
		position.set(1, y);
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public Vertex2d checkCollision(int clickX, int clickY) {
		if (clickX >= position.get(0) - radius / 2
				&& clickX <= position.get(0) + radius / 2) {
			if (clickY >= position.get(1) - radius / 2
					&& clickY <= position.get(1) + radius / 2) {
				return this;
			}
		}
		return null;
	}

	public void draw(Graphics graphic) {
		graphic.setColor(content.getColor());
		graphic.fillOval(this.getX() - this.getRadius() / 2,
				this.getY() - this.getRadius() / 2, this.getRadius(),
				this.getRadius());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
		return result;
	}

	@Override
	public String toString() {
		if (content.getPred() != null)
			return "Vertex2d: " + getId() + " - [position = " + position
					+ ", distance = " + content.getDistance() + " pred= "
					+ content.getPred().getId() + "]";
		return "Vertex2d: " + getId() + " - [position = " + position
				+ ", distance = " + content.getDistance() + "]";
	}

}
