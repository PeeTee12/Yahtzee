package data;

/**
 *
 * @author Petr
 */
public class Player {
    
    private String name;
    private int points;
    private int fullPoints;
    private int index;

    public Player() {
  
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getFullPoints() {
		return fullPoints;
	}

	public void setFullPoints(int fullPoints) {
		this.fullPoints = fullPoints;
	}

	public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    
    
    
}
