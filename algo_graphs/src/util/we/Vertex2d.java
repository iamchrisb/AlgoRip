package util.we;

import java.awt.Graphics;

import util.ripphausen.Vertex;

import com.google.gson.annotations.Expose;

public class Vertex2d extends Vertex {

	@Expose
	private int x;
	@Expose
	private int y;
	@Expose
	private int radius;

	private Vertex2dSearchContent<Vertex2d> content;

	public Vertex2dSearchContent<Vertex2d> getContent() {
		return content;
	}

	public void setContent(Vertex2dSearchContent<Vertex2d> content) {
		this.content = content;
	}

	public Vertex2d(int id, int x, int y, int radius) {
		super(id);
		this.x = x;
		this.y = y;
		this.radius = radius;
		content = new Vertex2dSearchContent<Vertex2d>();
	}
	
	public Vertex2d(int id) {
		super(id);
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
				+ " y:" + getY() + " radius: " + radius + "] ";
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
