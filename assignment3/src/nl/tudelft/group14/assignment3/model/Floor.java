package nl.tudelft.group14.assignment3.model;

public class Floor extends Block {

	private float pheromone;
	
	public Floor(int x, int y, float pheromone) {
		super(x, y);
		this.pheromone = pheromone;
	}

	public float getPheromone() {
		return pheromone;
	}

	public void setPheromone(float pheromone) {
		this.pheromone = pheromone;
	}

}
