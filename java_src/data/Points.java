package data;

import client.Client;

public class Points {
	
	private int ones;
	private int twos;
	private int threes;
	private int fours;
	private int fives;
	private int sixes;
	private int threeK;
	private int fourK;
	private int full;
	private int small;
	private int large;
	private int yahtzee;
	private int chance;
	private int uppSub;
	private int uppTotal;
	private int lowTotal;
	private int score;
	
	Client client;
	
	public Points(String ones, String twos, String threes, String fours, String fives, String sixes, String threeK, String fourK, String full, String small, String large, String yahtzee, String chance){
		this.ones = Integer.parseInt(ones);
		this.twos = Integer.parseInt(twos);
		this.threes = Integer.parseInt(threes);
		this.fours = Integer.parseInt(fours);
		this.fives = Integer.parseInt(fives);
		this.sixes = Integer.parseInt(sixes);
		this.threeK = Integer.parseInt(threeK);
		this.fourK = Integer.parseInt(fourK);
		this.full = Integer.parseInt(full);
		this.small = Integer.parseInt(small);
		this.large = Integer.parseInt(large);
		this.yahtzee = Integer.parseInt(yahtzee);
		this.chance = Integer.parseInt(chance);
	}

	public int getOnes() {
		return ones;
	}

	public void setOnes(int ones) {
		this.ones = ones;
	}

	public int getTwos() {
		return twos;
	}

	public void setTwos(int twos) {
		this.twos = twos;
	}

	public int getThrees() {
		return threes;
	}

	public void setThrees(int threes) {
		this.threes = threes;
	}

	public int getFours() {
		return fours;
	}

	public void setFours(int fours) {
		this.fours = fours;
	}

	public int getFives() {
		return fives;
	}

	public void setFives(int fives) {
		this.fives = fives;
	}

	public int getSixes() {
		return sixes;
	}

	public void setSixes(int sixes) {
		this.sixes = sixes;
	}

	public int getThreeK() {
		return threeK;
	}

	public void setThreeK(int threeK) {
		this.threeK = threeK;
	}

	public int getFourK() {
		return fourK;
	}

	public void setFourK(int fourK) {
		this.fourK = fourK;
	}

	public int getFull() {
		return full;
	}

	public void setFull(int full) {
		this.full = full;
	}

	public int getSmall() {
		return small;
	}

	public void setSmall(int small) {
		this.small = small;
	}

	public int getLarge() {
		return large;
	}

	public void setLarge(int large) {
		this.large = large;
	}

	public int getYahtzee() {
		return yahtzee;
	}

	public void setYahtzee(int yahtzee) {
		this.yahtzee = yahtzee;
	}

	public int getChance() {
		return chance;
	}

	public void setChance(int chance) {
		this.chance = chance;
	}
	
	public int getUppSub() {
		return uppSub;
	}

	public void setUppSub(int uppSub) {
		this.uppSub += uppSub;
		this.uppTotal += uppSub;
	}

	public int getUppTotal() {
		return uppTotal;
	}

	public void setUppTotal(int uppTotal) {
		this.uppTotal = uppTotal;
	}

	public int getLowTotal() {
		return lowTotal;
	}

	public void setLowTotal(int lowTotal) {
		this.lowTotal += lowTotal;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int points){
		this.score += points;
	}

}
