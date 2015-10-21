package nl.tudelft.group14.assignment3.model;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class Grid extends Canvas {

	int width, height;
	int rows;
	int cols;
	
	int rowHeight;
	int rowWidth;
	
	Maze m;
	
	Grid(int width, int height, int rows, int cols, Maze m) {
		this.width = width;
		this.height = height;
		setSize(width, height);
		this.rows = rows;
		this.cols = cols;
		this.rowHeight = height / (rows);
		this.rowWidth = width / (cols);
		this.m = m;
	}
	
	public void fillWalls(Graphics g) {
		for(Point point : m.wallPoints) {
			fillXY(point.x, point.y, g, Color.GRAY);
		}
	}
	
	public void fillFloors(Graphics g) {
		float highest = 0.f;
		
		for (Point point : m.floorPoints) {
			Floor f = (Floor)m.getMatrix()[point.x][point.y];
			if (f.getPheromone() > highest)
				highest = f.getPheromone();
		}
		for (Point point : m.floorPoints) {
			Floor f = (Floor)m.getMatrix()[point.x][point.y];
			float green = Math.min(f.getPheromone(), 255);//(f.getPheromone() / highest) * 255f;
//			System.out.println(green);
			fillXY(point.x, point.y, g, new Color(0, (int)green, 0));
		}
	}
	
	public void paint(Graphics g) {
		width = getSize().width;
		height = getSize().height;
		
		g.setColor(Color.BLACK);
		
		for (int i = 0; i < rows; i++)
			g.drawLine(0, i * rowHeight, width, i * rowHeight);
		
		for (int i = 0; i < cols; i++)
			g.drawLine(i * rowWidth, 0, i * rowWidth, height);
		fillWalls(g);
		fillFloors(g);
	}
	
	public void fillXY(int row, int column, Graphics g, Color color) {
		g.setColor(color);
		g.fillRect(row * rowWidth, column * rowHeight, rowWidth, rowHeight);
	}
}
