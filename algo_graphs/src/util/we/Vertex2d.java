package util.we;

import java.awt.Color;
import java.awt.Graphics;

import util.ripphausen.Vertex;

public class Vertex2d extends Vertex {

	private int x;
	private int y;
	private int radius;

	private Vertex2dSearchContent content;

	public Vertex2dSearchContent getContent() {
		return content;
	}

	public void setContent(Vertex2dSearchContent content) {
		this.content = content;
	}

	public Vertex2d(int id, int x, int y, int radius) {
		super(id);
		this.x = x;
		this.y = y;
		this.radius = radius;
		content = new Vertex2dSearchContent();
		content.setColor(Color.green);
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public Vertex2d checkCollision(int clickX, int clickY) {
		if (clickX >= getX() - radius / 2 && clickX <= getX() + radius / 2) {
			if (clickY >= getY() - radius / 2 && clickY <= getY() + radius / 2) {
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
	public String toString() {
		if (content != null) {
			if (content.getPred() != null || content.getColor() != null) {
				return "Vertex2d: " + getId() + " - [position = " + " x: "
						+ getX() + " y:" + getY() + ", distance = "
						+ content.getDistance() + " pred= "
						+ content.getPred().getId() + "]";
			}
		}
		return "Vertex2d: " + getId() + " - [position = " + " x: " + getX()
				+ " y:" + getY() + " radius: " + radius + "]";
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
