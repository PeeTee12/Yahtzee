package data;

/**
 * Tato metoda vypoèítává body, pro jednotlivé kombinace.
 * @author Petr
 */
public class Game {
	
	Roll roll = new Roll();
    
	/**
	 * Konstruktor tøídy.
	 */
    public Game() {
    
    }
    
    public int ones(int[] value){
        int points;
        points = value[0];
        return points;
    }
    
    public int twos(int[] value){
        int points;
        points = value[1]*2;
        return points;
    }
    
    public int threes(int[] value){
        int points;
        points = value[2]*3;
        return points;
    }
    
    public int fours(int[] value){
        int points;
        points = value[3]*4;
        return points;
    }
    
    public int fives(int[] value){
        int points;
        points = value[4]*5;
        return points;
    }
    
    public int sixes(int[] value){
        int points;
        points = value[5]*6;
        return points;
    }
    
    public int threeOfAKind(int[] dice, int[] value){
        int points = 0;
        
        for (int j = 0;j < 6;j++){
            if (value[j] >= 3){
            for(int k = 0;k < 5;k++){
                points += dice[k];
            }
            break;
            }
        }
        return points;
    }
    
    public int fourOfAKind(int[] dice, int[] value){
        int points = 0;
        
        for (int j = 0;j < 6;j++){
            if (value[j] == 4){
            for(int k = 0;k < 5;k++){
                points += dice[k];
            }
            break;
            }
        }
        return points;
    }
    
    public int fullHouse(int[] value){
        int points = 0;
        
        for (int j = 0;j < 6;j++){
            if (value[j] == 3){
                for(int k = 0;k < 6;k++){
                    if (value[k] == 2){
                        points = 25;
                    }
                }
            }
        }
        return points;
    }
    
    public int smallStraight(int[] sorted){
        int points = 0;
        int temp = 0;
        roll.bubbleSort(sorted);
        for (int i = 0; i < 4;i++){
            if ((sorted[i+1] - sorted[i]) == 1){
                temp++;
            }
        }
        if (temp >= 3){
            points = 30;
        }
        return points;
    }
    
    public int largeStraight(int[] sorted){
        int points = 0;
        int temp = 0;
        roll.bubbleSort(sorted); 
        for (int i = 0; i < 4;i++){
            if ((sorted[i+1] - sorted[i]) == 1){
                temp++;
            }
            else{
                break;
            }
        }
        if (temp == 4){
            points = 40;
        }
        return points;
    }
    
    public int yahtzee(int[] value){
        int points = 0;
        for (int j = 0;j < 6;j++){
            if (value[j] == 5){
                points = 50;
            }
        }
        return points;
    }
    
    public int chance(int[] dice){
        int points = 0;
        for(int i = 0;i < 5;i++){
            points += dice[i];
        }
        return points;
    }
    
}
