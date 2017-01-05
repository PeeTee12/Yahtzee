package data;

import java.util.Random;

/**
 *
 * @author Petr
 */
public class Roll {
    
    private int[] dice;
    private int[] value;
    private int[] sorted;

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
    
    public Roll() {
    
    }
    
    public void rollDice(){
        dice = new int[5];
        Random r = new Random();
        for(int i = 0; i < 5; i++){
            dice[i] = r.nextInt(6)+1;
            System.out.print(dice[i] + " ");
        }
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
                   if (dice[j] < dice[j+1]){
                        int temp = dice[j];
                        dice[j] = dice[j+1];
                        dice[j+1] = temp;
                    }
            }
        }
        return dice;
    }
    
    public void dicePicture(int[] dice){
    	for (int i = 0;i < 5;i++){
    		switch (dice[i]){
    		case 1: ;
    		case 2: ;
    		case 3: ;
    		case 4: ;
    		case 5: ;
    		case 6: ;
    		}
    	}
    }
    
   public static void main(String []args){
       
       Roll roll = new Roll();
       Game game = new Game();
       roll.rollDice();
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
