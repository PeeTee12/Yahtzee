package data;

import java.util.Random;

import org.eclipse.swt.custom.CLabel;
import org.eclipse.wb.swt.SWTResourceManager;

/**
 *
 * @author Petr
 */
public class Roll {
    
    private int[] dice = new int[5];
    private int[] value;
    private int[] sorted = new int[5];
    private boolean clicked1;
    private boolean clicked2 = true;
    private boolean clicked3 = true;
    private boolean clicked4;
    private boolean clicked5;

    public int[] getDice() {
        return dice;
    }

    public void setDice(int[] dice) {
        this.dice = dice;
    }

    public int[] getValue() {
        return value;
    }

    public void setValue(int[] value) {
        this.value = value;
    }

    public int[] getSorted() {
        return sorted;
    }

    public void setSorted(int[] sorted) {
        this.sorted = sorted;
    }
    
    public boolean isClicked1() {
		return clicked1;
	}

	public void setClicked1(boolean clicked1) {
		this.clicked1 = clicked1;
	}

	public boolean isClicked2() {
		return clicked2;
	}

	public void setClicked2(boolean clicked2) {
		this.clicked2 = clicked2;
	}

	public boolean isClicked3() {
		return clicked3;
	}

	public void setClicked3(boolean clicked3) {
		this.clicked3 = clicked3;
	}

	public boolean isClicked4() {
		return clicked4;
	}

	public void setClicked4(boolean clicked4) {
		this.clicked4 = clicked4;
	}

	public boolean isClicked5() {
		return clicked5;
	}

	public void setClicked5(boolean clicked5) {
		this.clicked5 = clicked5;
	}

	public Roll() {
		
    }
    
    public void rollDice(){
        Random r = new Random();
        /*for(int i = 0; i < 5; i++){
        	dice[i] = r.nextInt(6)+1;
        	System.out.print(dice[i] + " ");*/
        if(!clicked1){
    		dice[0] = r.nextInt(6)+1;
    	}
    	if(!clicked2){
    		dice[1] = r.nextInt(6)+1;
    	}
    	if(!clicked3){
    		dice[2] = r.nextInt(6)+1;
    	}
    	if(!clicked4){
    		dice[3] = r.nextInt(6)+1;
    	}
    	if(!clicked5){
    		dice[4] = r.nextInt(6)+1;
    	}
    	sorted = dice;
    }
    
    public void rollDice2(){
    	int[] dice2 = new int[5];
    	Random r = new Random();
    	if(!clicked1){
    		dice2[0] = r.nextInt(6)+1;
    	}
    	else {
    		dice2[0] = dice[0];
    	}
    	if(!clicked2){
    		dice2[1] = r.nextInt(6)+1;
    	}
    	else {
    		dice2[1] = dice[1];
    	}
    	if(!clicked3){
    		dice2[2] = r.nextInt(6)+1;
    	}
    	else {
    		dice2[2] = dice[2];
    	}
    	if(!clicked4){
    		dice2[3] = r.nextInt(6)+1;
    	}
    	else {
    		dice2[3] = dice[3];
    	}
    	if(!clicked5){
    		dice2[4] = r.nextInt(6)+1;
    	}
    	else {
    		dice2[4] = dice[4];
    	}
    	System.out.println("Pole 1: " + dice[0] + " " + dice[1] + " " + dice[2] + " " + dice[3] + " " + dice[4]);
    	dice = dice2;
    	sorted = dice2;
    	System.out.println("Sorted 1: " + sorted[0] + " " + sorted[1] + " " + sorted[2] + " " + sorted[3] + " " + sorted[4]);
    	System.out.println("Pole 2: " + dice2[0] + " " + dice2[1] + " " + dice2[2] + " " + dice2[3] + " " + dice2[4]);
    	System.out.println("Pole 1: " + dice[0] + " " + dice[1] + " " + dice[2] + " " + dice[3] + " " + dice[4]);
    }
    
    public int[] valueCounter(int[] dice){
        value = new int [6];
        int dice1 = 0;
        int dice2 = 0;
        int dice3 = 0;
        int dice4 = 0;
        int dice5 = 0;
        int dice6 = 0;
        
        for(int i = 0;i < 5;i++){
            switch (dice[i]){
                case 1:
                   dice1++;
                   value[0] = dice1;
                   break;
                case 2:
                    dice2++;
                    value[1] = dice2;
                    break;
                case 3:
                    dice3++;
                    value[2] = dice3;
                    break;
                case 4:
                    dice4++;
                    value[3] = dice4;
                    break;
                case 5:
                    dice5++;
                    value[4] = dice5;
                    break;
                case 6:
                    dice6++;
                    value[5] = dice6;
                    break;
            } 
        }
        return value;
    }
    
    public int[] bubbleSort(int[] sorted){
        for (int i = 0;i < sorted.length - 1;i++){
            for (int j = 0;j < sorted.length - i - 1;j++){
            	if (sorted[j] > sorted[j+1]){
            		int temp = sorted[j];
            		sorted[j] = sorted[j+1];
                    sorted[j+1] = temp;
                }
            }
        }
        return sorted;
    }
    
    public void dicePicture(int[] dice, int i, CLabel label){
    	switch (dice[i]){
    	case 1: label.setBackground(SWTResourceManager.getImage("One.jpg"));
    	break;
    	case 2: label.setBackground(SWTResourceManager.getImage("Two.jpg"));
    	break;
    	case 3: label.setBackground(SWTResourceManager.getImage("Three.jpg"));
    	break;
    	case 4: label.setBackground(SWTResourceManager.getImage("Four.jpg"));
    	break;
    	case 5: label.setBackground(SWTResourceManager.getImage("Five.jpg"));
    	break;
    	case 6: label.setBackground(SWTResourceManager.getImage("Six.jpg"));
    	break;
    	}
    }
    
   public static void main(String []args){
       
       Roll roll = new Roll();
       Game game = new Game();
       for (int i = 0; i <3; i++){
       roll.rollDice2();
       roll.valueCounter(roll.dice);   
       //roll.bubbleSort();
       System.out.println("Sorted 1: " + roll.sorted[0] + " " + roll.sorted[1] + " " + roll.sorted[2] + " " + roll.sorted[3] + " " + roll.sorted[4]);
       System.out.println("Pole 1: " + roll.dice[0] + " " + roll.dice[1] + " " + roll.dice[2] + " " + roll.dice[3] + " " + roll.dice[4]);
       //System.out.println();
       int ones = game.ones(roll.value);
       int twos = game.twos(roll.value);
       int threes = game.threes(roll.value);
       int fours = game.fours(roll.value);
       int fives = game.fives(roll.value);
       int sixes = game.sixes(roll.value);
       int threeK = game.threeOfAKind(roll.dice, roll.value);
       int fourK = game.fourOfAKind(roll.dice, roll.value);
       int fullH = game.fullHouse(roll.value);
       int small = game.smallStraight(roll.dice);
       int large = game.largeStraight(roll.dice);
       int yahtzee = game.yahtzee(roll.value);
       int chance = game.chance(roll.dice);
       
       System.out.println("Ones: " + ones);
       System.out.println("Twos: " + twos);
       System.out.println("Threes: " + threes);
       System.out.println("Fours: " + fours);
       System.out.println("Fives: " + fives);
       System.out.println("Sixes: " + sixes);
       System.out.println("3 of a Kind: " + threeK);
       System.out.println("4 of a Kind: " + fourK);
       System.out.println("Full House: " + fullH);
       System.out.println("Small Straight: " + small);
       System.out.println("Large Straight: " + large);
       System.out.println("YAHTZEE: " + yahtzee);
       System.out.println("Chance: " + chance);
       }
       
   } 


    
    
}
