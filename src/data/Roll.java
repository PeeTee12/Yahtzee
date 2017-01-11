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
    private int[] sorted;
    private boolean clicked1;
    private boolean clicked2;
    private boolean clicked3;
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
    
    public int[] rollDice(){
        //dice = new int[5];
        Random r = new Random();
        System.out.println(clicked1 + " " + clicked2 + " " + clicked3 + " " + clicked4 + " " + clicked5);
        for(int i = 0; i < 5; i++){
        	switch(i){
        	case 0: 
        		if (!clicked1){
        			dice[0] = r.nextInt(6)+1;
        			break;
        		} else{
        			continue;
        		}
        	case 1:
        		if (!clicked2){
        			dice[1] = r.nextInt(6)+1;
        		}
        		break;
        	case 2:
        		if (!clicked3){
        			dice[2] = r.nextInt(6)+1;
        		}
        		break;
        	case 3:
        		if (!clicked4){
        			dice[3] = r.nextInt(6)+1;
        		}
        		break;
        	case 4:
        		if (!clicked5){
        			dice[4] = r.nextInt(6)+1;
        		}
        		break;
        }
            System.out.print(dice[i] + " ");
            System.out.println("i = " + i);
        }
        return dice;
    }
    
    public int[] rollDice2(int[] dice){
    	dice[0] = 6;
    	System.out.println(dice[0] + " " + dice[1] + " " + dice[2]);
    	return dice;
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
    
    public int[] bubbleSort(int[] dice){
        for (int i = 0;i < dice.length - 1;i++){
            for (int j = 0;j < dice.length - i - 1;j++){
                   if (dice[j] > dice[j+1]){
                        int temp = dice[j];
                        dice[j] = dice[j+1];
                        dice[j+1] = temp;
                    }
            }
        }
        return dice;
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
       roll.rollDice();
       roll.rollDice2(roll.dice);
       roll.valueCounter(roll.dice);   
       roll.setSorted(roll.bubbleSort(roll.dice));
       System.out.println();
       int ones = game.ones(roll.value);
       int twos = game.twos(roll.value);
       int threes = game.threes(roll.value);
       int fours = game.fours(roll.value);
       int fives = game.fives(roll.value);
       int sixes = game.sixes(roll.value);
       int threeK = game.threeOfAKind(roll.dice, roll.value);
       int fourK = game.fourOfAKind(roll.dice, roll.value);
       int fullH = game.fullHouse(roll.value);
       int small = game.smallStraight(roll.sorted);
       int large = game.largeStraight(roll.sorted);
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
