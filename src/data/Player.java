package data;

/**
 *
 * @author Petr
 */
public class Player {
    
    private String name;
    private int points;
    private int fullPoints;
    private boolean bonus;
    private int turn;
    private int index;
    private boolean myTurn;

    public Player(String name) {
    	this.name =  name;
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

	public boolean isBonus() {
		return bonus;
	}

	public void setBonus(boolean bonus) {
		this.bonus = bonus;
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

	public boolean isMyTurn() {
		return myTurn;
	}

	public void setMyTurn(boolean myTurn) {
		this.myTurn = myTurn;
	}
    
    
    
    
}
