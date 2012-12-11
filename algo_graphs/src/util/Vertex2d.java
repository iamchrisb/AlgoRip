package util;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

public class Vertex2d extends Vertex {

	private Vector<Integer> position;
	private boolean clicked;
	private int radius;
	private Color color;
	private VertexSearchContent content;

	public Vertex2d(int id, int x, int y, int radius) {
		super(id);
		position = new Vector<Integer>();
		position.add(0, x);
		position.add(1, y);
		this.radius = radius;
		color = Color.green;
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

	public boolean isClicked() {
		return clicked;
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public boolean checkCollision(int clickX, int clickY) {
//		System.out.println("x: " + clickX + " y: " + clickY);
//		System.out.println("x vert: " + position.get(0) + " x rad: " + (position.get(0) + radius) + " y vert: " + position.get(1) + " rad y: " + (position.get(1) + radius));
		if (clickX >= position.get(0) - radius / 2 && clickX <= position.get(0) + radius / 2) {
			if (clickY >= position.get(1) - radius / 2 && clickY <= position.get(1) + radius / 2) {
				System.out.println("x: " + clickX + " y: " + clickY);
				System.out.println("x vert: " + position.get(0) + " x rad: " + (position.get(0) + radius) + " y vert: " + position.get(1) + " rad y: " + (position.get(1) + radius));
//				System.out.println("hit");
				clicked = true;
				return true;
			}
		}
		return false;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color c) {
		this.color = c;
	}

	public void draw(Graphics graphic) {
		graphic.setColor(color);
		graphic.fillOval(this.getX() - this.getRadius() / 2,
				this.getY() - this.getRadius() / 2, this.getRadius(),
				this.getRadius());
	}

}
