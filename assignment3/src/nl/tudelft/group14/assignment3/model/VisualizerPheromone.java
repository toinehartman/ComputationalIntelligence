package nl.tudelft.group14.assignment3.model;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VisualizerPheromone extends Frame {
	  /*
	   * Construct a GfxDemo2 given its title, width and height. Uses a
	   * GridBagLayout to make the Canvas resize properly.
	   */
        public Grid xyz;
    
	  public VisualizerPheromone(String title, int w, int h, int rows, int cols, Maze m) {
	    setTitle(title);

	    // Now create a Canvas and add it to the Frame.
	    xyz = new Grid(w, h, rows, cols, m);
	    add(xyz);

	    // Normal end ... pack it up!
	    pack();
	  }
	  
	  public Grid returnGrid() {
	      return xyz;
	  }
	}