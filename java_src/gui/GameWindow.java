package gui;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.wb.swt.SWTResourceManager;

import data.Game;
import data.Player;
import data.Points;
import data.Roll;
import client.Client;
import client.Send;

public class GameWindow {

	protected Shell shlYahtzee = new Shell();
	Roll roll;
	Game game;
	Player player;
	Points points;
	public Button buttonRoll;
	public Button buttonNewGame;
	public Button buttonPlay;
	Send send;
	public Socket socket;
	public Label labelMessage;
	public Label labelNick2_1;
	public Label labelPoints2;
	public Client client;
	static Login login;
	int rolls = 0;
	private final String serverError = "Server not found! Please try again later...";
	private String opponent;
	private String dice;
	private String value;
	
	/**
	 * Metoda pro nastavení bodù do promìnných, deaktivace a aktivace tlaèítek, nastavení hodù na 0 a odeslání zprávy na server.
	 * @param radio Radio button, který se ovládá.
	 * @param label Popisek, který se ovládá.
	 * @param player Hráè, kterému se nastavují body.
	 * @param points Body, které se nastavují.
	 */
	
	public void setPoints(Points points, Player player, Send send, Label label,  Label labelMessage, Label labelBonus, Button radio, int playedPoints, String number, int Iscore){
		String message;
		String score;
		label.setText(String.valueOf(playedPoints));
		player.setPoints(Integer.parseInt(label.getText()));
		points.setScore(player.getPoints());
		score = String.valueOf(Iscore);
		message = player.getName() + " played " + score + " on " + number + ".";
		if (points.getUppSub() > 62 && !player.isBonus()){
			points.setScore(35);
			points.setUppTotal(points.getUppSub() + 35);
			labelBonus.setText("35");
			player.setBonus(true);
			int temp = Integer.parseInt(score);
			temp = temp + 35;
			score = String.valueOf(temp);
			message += " " + player.getName() + " just achieved bonus 35 points.";
		}
		radio.setEnabled(false);
		radio.setSelection(false);
		buttonPlay.setEnabled(false);
		buttonRoll.setEnabled(false);
		rolls = 0;
		send.sendMessage("PLAY," + number + "," + score + "," + message + "," + dice, labelMessage, serverError);
		//System.out.println("PLAY," + number + "," + score + "," + player.getName() + " played " + score + " on " + number + ".");
	}
	
	/**
	 * Konstruktor tøídy pøedává argumenty socket, player a opponent, vytváøí nový objekt tøídy Send.
	 */
 	public GameWindow(Socket socket, Player player, Roll roll, Points points, String opponent){
		this.player = player;
		this.roll = roll;
		this.points = points;
		this.opponent = opponent;
		this.socket = socket;
		send = new Send(socket);
	}

	/**
	 * Otevøení okna.
	 * @wbp.parser.entryPoint
	 */
	public void open() {
		Display display = Display.getDefault();
		try {
			createContents();
			shlYahtzee.setImage(SWTResourceManager.getImage(GameWindow.class, "/Six.jpg"));
			try {
				shlYahtzee.open();
				shlYahtzee.layout();
				while (!shlYahtzee.isDisposed()) {
					if (!display.readAndDispatch()) {
						display.sleep();
					}
				}
			}
			finally {
				if (!shlYahtzee.isDisposed()){
					shlYahtzee.dispose();
				}
			}
		}
		finally {
			display.dispose();
		}
		send.sendMessage("KILL,SERVER,RIGHT,NOW", labelMessage, "Server not found! Please try again later...");
		System.exit(0);
	}

	/**
	 * Vytvoøení obsahu okna.
	 */
	protected void createContents() {
		//shlYahtzee = new Shell();
		shlYahtzee.setMinimumSize(new Point(1041, 807));
		shlYahtzee.setSize(450, 300);
		shlYahtzee.setText("Yahtzee");
		//roll = new Roll();
		game = new Game();
		//player = new Player(login.getName());
		//points = new Points();
		player.setBonus(false);
		roll.setClicked1(false);
		roll.setClicked2(false);
		roll.setClicked3(false);
		roll.setClicked4(false);
		roll.setClicked5(false);
		
		final Button radio1 = new Button(shlYahtzee, SWT.RADIO);
		radio1.setBounds(400, 128, 37, 26);
		
		final Button radio2 = new Button(shlYahtzee, SWT.RADIO);
		radio2.setBounds(400, 192, 37, 26);
		
		final Button radio3 = new Button(shlYahtzee, SWT.RADIO);
		radio3.setBounds(400, 254, 37, 26);
		
		final Button radio4 = new Button(shlYahtzee, SWT.RADIO);
		radio4.setBounds(400, 317, 37, 26);
		
		final Button radio5 = new Button(shlYahtzee, SWT.RADIO);
		radio5.setBounds(400, 377, 37, 26);
		
		final Button radio6 = new Button(shlYahtzee, SWT.RADIO);
		radio6.setBounds(400, 440, 37, 26);
		
		final Button radio3K = new Button(shlYahtzee, SWT.RADIO);
		radio3K.setBounds(913, 128, 37, 26);
		
		final Button radio4K = new Button(shlYahtzee, SWT.RADIO);
		radio4K.setBounds(913, 192, 37, 26);
		
		final Button radioFull = new Button(shlYahtzee, SWT.RADIO);
		radioFull.setBounds(913, 254, 37, 26);
		
		final Button radioSmall = new Button(shlYahtzee, SWT.RADIO);
		radioSmall.setBounds(913, 317, 37, 26);
		
		final Button radioLarge = new Button(shlYahtzee, SWT.RADIO);
		radioLarge.setBounds(913, 377, 37, 26);
		
		final Button radioY = new Button(shlYahtzee, SWT.RADIO);
		radioY.setBounds(913, 440, 37, 26);
		
		final Button radioC = new Button(shlYahtzee, SWT.RADIO);
		radioC.setBounds(913, 499, 37, 26);
		
		
		final CLabel dice1 = new CLabel(shlYahtzee, SWT.BORDER);
		dice1.setAlignment(SWT.CENTER);
		dice1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		dice1.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 11, SWT.BOLD));
		dice1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		dice1.setBounds(225, 614, 80, 80);
		dice1.addMouseListener(new MouseListener() {			
			@Override
			public void mouseDown(MouseEvent arg0) {
				if (!roll.isClicked1()){
					dice1.setText("HOLD");
					roll.setClicked1(true);
				}
				else {
					dice1.setText("");
					roll.setClicked1(false);
				}	
			}
			@Override
			public void mouseUp(MouseEvent arg0) {
			}
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
			}
		});
			
		
		final CLabel dice2 = new CLabel(shlYahtzee, SWT.BORDER);
		dice2.setAlignment(SWT.CENTER);
		dice2.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		dice2.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 11, SWT.BOLD));
		dice2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		dice2.setBounds(347, 614, 80, 80);
		dice2.addMouseListener(new MouseListener() {			
			@Override
			public void mouseDown(MouseEvent arg0) {
				if (!roll.isClicked2()){
					dice2.setText("HOLD");
					roll.setClicked2(true);
				}
				else {
					dice2.setText("");
					roll.setClicked2(false);
				}	
			}
			@Override
			public void mouseUp(MouseEvent arg0) {
			}
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
			}
		});
		
		final CLabel dice3 = new CLabel(shlYahtzee, SWT.BORDER);
		dice3.setAlignment(SWT.CENTER);
		dice3.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		dice3.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 11, SWT.BOLD));
		dice3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		dice3.setBounds(473, 614, 80, 80);
		dice3.addMouseListener(new MouseListener() {			
			@Override
			public void mouseDown(MouseEvent arg0) {
				if (!roll.isClicked3()){
					dice3.setText("HOLD");
					roll.setClicked3(true);
				}
				else {
					dice3.setText("");
					roll.setClicked3(false);
				}	
			}
			@Override
			public void mouseUp(MouseEvent arg0) {
			}
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
			}
		});
		
		final CLabel dice4 = new CLabel(shlYahtzee, SWT.BORDER);
		dice4.setAlignment(SWT.CENTER);
		dice4.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		dice4.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 11, SWT.BOLD));
		dice4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		dice4.setBounds(599, 614, 80, 80);
		dice4.addMouseListener(new MouseListener() {			
			@Override
			public void mouseDown(MouseEvent arg0) {
				if (!roll.isClicked4()){
					dice4.setText("HOLD");
					roll.setClicked4(true);
				}
				else {
					dice4.setText("");
					roll.setClicked4(false);
				}	
			}
			@Override
			public void mouseUp(MouseEvent arg0) {
			}
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
			}
		});
		
		final CLabel dice5 = new CLabel(shlYahtzee, SWT.BORDER);
		dice5.setAlignment(SWT.CENTER);
		dice5.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		dice5.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 11, SWT.BOLD));
		dice5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		dice5.setBounds(720, 614, 80, 80);
		dice5.addMouseListener(new MouseListener() {			
			@Override
			public void mouseDown(MouseEvent arg0) {
				if (!roll.isClicked5()){
					dice5.setText("HOLD");
					roll.setClicked5(true);
				}
				else {
					dice5.setText("");
					roll.setClicked5(false);
				}	
			}
			@Override
			public void mouseUp(MouseEvent arg0) {
			}
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
			}
		});
		
		final Label label1 = new Label(shlYahtzee, SWT.NONE);
		label1.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 25, SWT.BOLD));
		label1.setAlignment(SWT.CENTER);
		label1.setBounds(451, 124, 50, 35);
		
		final Label label2 = new Label(shlYahtzee, SWT.NONE);
		label2.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 25, SWT.BOLD));
		label2.setAlignment(SWT.CENTER);
		label2.setBounds(451, 186, 50, 35);
		
		final Label label3 = new Label(shlYahtzee, SWT.NONE);
		label3.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 25, SWT.BOLD));
		label3.setAlignment(SWT.CENTER);
		label3.setBounds(451, 248, 50, 35);
		
		final Label label4 = new Label(shlYahtzee, SWT.NONE);
		label4.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 25, SWT.BOLD));
		label4.setAlignment(SWT.CENTER);
		label4.setBounds(451, 310, 50, 35);
		
		final Label label5 = new Label(shlYahtzee, SWT.NONE);
		label5.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 25, SWT.BOLD));
		label5.setAlignment(SWT.CENTER);
		label5.setBounds(451, 372, 50, 35);
		
		final Label label6 = new Label(shlYahtzee, SWT.NONE);
		label6.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 25, SWT.BOLD));
		label6.setAlignment(SWT.CENTER);
		label6.setBounds(451, 434, 50, 35);
		
		final Label label3K = new Label(shlYahtzee, SWT.NONE);
		label3K.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 25, SWT.BOLD));
		label3K.setAlignment(SWT.CENTER);
		label3K.setBounds(963, 124, 50, 35);
		
		final Label label4K = new Label(shlYahtzee, SWT.NONE);
		label4K.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 25, SWT.BOLD));
		label4K.setAlignment(SWT.CENTER);
		label4K.setBounds(963, 186, 50, 35);
		
		final Label labelFull = new Label(shlYahtzee, SWT.NONE);
		labelFull.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 25, SWT.BOLD));
		labelFull.setAlignment(SWT.CENTER);
		labelFull.setBounds(963, 248, 50, 35);
		
		final Label labelSmall = new Label(shlYahtzee, SWT.NONE);
		labelSmall.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 25, SWT.BOLD));
		labelSmall.setAlignment(SWT.CENTER);
		labelSmall.setBounds(963, 310, 50, 35);
		
		final Label labelLarge = new Label(shlYahtzee, SWT.NONE);
		labelLarge.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 25, SWT.BOLD));
		labelLarge.setAlignment(SWT.CENTER);
		labelLarge.setBounds(963, 374, 50, 35);
		
		final Label labelY = new Label(shlYahtzee, SWT.NONE);
		labelY.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 25, SWT.BOLD));
		labelY.setAlignment(SWT.CENTER);
		labelY.setBounds(963, 434, 50, 35);
		
		final Label labelC = new Label(shlYahtzee, SWT.NONE);
		labelC.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 25, SWT.BOLD));
		labelC.setAlignment(SWT.CENTER);
		labelC.setBounds(963, 496, 50, 35);
		
		final Label labelPoints1 = new Label(shlYahtzee, SWT.NONE);
		labelPoints1.setForeground(SWTResourceManager.getColor(255, 255, 255));
		labelPoints1.setBackground(SWTResourceManager.getColor(87, 55, 171));
		labelPoints1.setAlignment(SWT.CENTER);
		labelPoints1.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 40, SWT.BOLD));
		labelPoints1.setBounds(315, 10, 160, 66);
		
		labelPoints2 = new Label(shlYahtzee, SWT.NONE);
		labelPoints2.setForeground(SWTResourceManager.getColor(255, 255, 255));
		labelPoints2.setBackground(SWTResourceManager.getColor(87, 55, 171));
		labelPoints2.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 40, SWT.BOLD));
		labelPoints2.setAlignment(SWT.CENTER);
		labelPoints2.setBounds(550, 10, 160, 66);
		
		Label labelNick1 = new Label(shlYahtzee, SWT.NONE);
		labelNick1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		labelNick1.setBackground(SWTResourceManager.getColor(87, 55, 171));
		labelNick1.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 25, SWT.NORMAL));
		labelNick1.setBounds(25, 21, 260, 40);
		labelNick1.setText(player.getName());
		
		labelNick2_1 = new Label(shlYahtzee, SWT.NONE);
		labelNick2_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		labelNick2_1.setBackground(SWTResourceManager.getColor(87, 55, 171));
		labelNick2_1.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 25, SWT.NORMAL));
		labelNick2_1.setAlignment(SWT.RIGHT);
		labelNick2_1.setBounds(739, 21, 260, 40);
		labelNick2_1.setText(opponent);
		
		final Label labelUppSub = new Label(shlYahtzee, SWT.NONE);
		labelUppSub.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		labelUppSub.setBackground(SWTResourceManager.getColor(87, 55, 171));
		labelUppSub.setAlignment(SWT.CENTER);
		labelUppSub.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 14, SWT.NORMAL));
		labelUppSub.setBounds(451, 490, 50, 26);
		
		final Label labelBonus = new Label(shlYahtzee, SWT.NONE);
		labelBonus.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		labelBonus.setBackground(SWTResourceManager.getColor(87, 55, 171));
		labelBonus.setAlignment(SWT.CENTER);
		labelBonus.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 14, SWT.NORMAL));
		labelBonus.setBounds(451, 532, 50, 26);
		
		final Label labelUppTotal = new Label(shlYahtzee, SWT.NONE);
		labelUppTotal.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		labelUppTotal.setBackground(SWTResourceManager.getColor(87, 55, 171));
		labelUppTotal.setAlignment(SWT.CENTER);
		labelUppTotal.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 14, SWT.NORMAL));
		labelUppTotal.setBounds(451, 573, 50, 26);
		
		final Label labelLowTotal = new Label(shlYahtzee, SWT.NONE);
		labelLowTotal.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		labelLowTotal.setBackground(SWTResourceManager.getColor(87, 55, 171));
		labelLowTotal.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 14, SWT.NORMAL));
		labelLowTotal.setAlignment(SWT.CENTER);
		labelLowTotal.setBounds(963, 561, 50, 26);
		
		labelMessage = new Label(shlYahtzee, SWT.NONE);
		labelMessage.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		labelMessage.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 15, SWT.NORMAL));
		labelMessage.setBounds(10, 725, 796, 26);
		
		final Label labelTurn = new Label(shlYahtzee, SWT.NONE);
		labelTurn.setBackground(SWTResourceManager.getColor(250, 229, 108));
		labelTurn.setAlignment(SWT.CENTER);
		labelTurn.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 20, SWT.NORMAL));
		labelTurn.setBounds(100, 635, 824, 40);
		labelTurn.setVisible(false);

		buttonRoll = new Button(shlYahtzee, SWT.NONE);
		buttonRoll.setBackground(SWTResourceManager.getColor(240, 240, 240));
		buttonRoll.setFont(SWTResourceManager.getFont("Comic Sans MS", 14, SWT.NORMAL));
		buttonRoll.setBounds(25, 635, 160, 40);
		buttonRoll.setText("Roll");
		buttonRoll.addMouseListener(new MouseListener() {
			@Override
			public void mouseDown(MouseEvent arg0) {
				String hold = String.valueOf("ROLL," + roll.isClicked1() + "," + roll.isClicked2() + "," + roll.isClicked3() + "," + roll.isClicked4() + "," + roll.isClicked5());
				send.sendMessage(hold, labelMessage, serverError);
				//roll.rollDice();
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					labelMessage.setText(serverError);
				}
				roll.dicePicture(roll.getDice(), 0, dice1);
				roll.dicePicture(roll.getDice(), 1, dice2);
				roll.dicePicture(roll.getDice(), 2, dice3);
				roll.dicePicture(roll.getDice(), 3, dice4);
				roll.dicePicture(roll.getDice(), 4, dice5);
				roll.valueCounter(roll.getDice());
				dice = Arrays.toString(roll.getDice());
				dice = dice.replaceAll(" ", "").replace("[", "").replace("]", "");
				value = Arrays.toString(roll.getValue());
				value = value.replaceAll(" ", "").replace("[", "").replace("]", "");
				buttonPlay.setEnabled(true);
				rolls++;
				if (rolls == 3){
					buttonRoll.setEnabled(false);
				}	
				dice1.setEnabled(true);
				dice2.setEnabled(true);
				dice3.setEnabled(true);
				dice4.setEnabled(true);
				dice5.setEnabled(true);
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {				
			}
			
			@Override
			public void mouseUp(MouseEvent arg0) {				
			}
		});
		
		buttonPlay = new Button(shlYahtzee, SWT.NONE);
		buttonPlay.setText("Play");
		buttonPlay.setFont(SWTResourceManager.getFont("Comic Sans MS", 14, SWT.NORMAL));
		buttonPlay.setBackground(SWTResourceManager.getColor(240, 240, 240));
		buttonPlay.setBounds(840, 635, 160, 40);
		buttonPlay.setEnabled(false);
		buttonPlay.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseDown(MouseEvent arg0) {
				if (radio1.getSelection()){
					points.setOnes(game.ones(roll.getValue()));
					points.setUppSub(points.getOnes());
					setPoints(points, player, send, label1, labelMessage, labelBonus, radio1, points.getOnes(), "Ones", points.getOnes());
				}
				if (radio2.getSelection()){
					points.setTwos(game.twos(roll.getValue()));
					points.setUppSub(points.getTwos());
					setPoints(points, player, send, label2, labelMessage, labelBonus, radio2, points.getTwos(), "Twos", points.getTwos());
				}
				if (radio3.getSelection()){
					points.setThrees(game.threes(roll.getValue()));
					points.setUppSub(points.getThrees());
					setPoints(points, player, send, label3, labelMessage, labelBonus, radio3, points.getThrees(), "Threes", points.getThrees());
				}
				if (radio4.getSelection()){
					points.setFours(game.fours(roll.getValue()));
					points.setUppSub(points.getFours());
					setPoints(points, player, send, label4, labelMessage, labelBonus, radio4, points.getFours(), "Fours", points.getFours());
				}
				if (radio5.getSelection()){
					points.setFives(game.fives(roll.getValue()));
					points.setUppSub(points.getFives());
					setPoints(points, player, send, label5, labelMessage, labelBonus, radio5, points.getFives(), "Fives", points.getFives());
				}
				if (radio6.getSelection()){
					points.setSixes(game.sixes(roll.getValue()));
					points.setUppSub(points.getSixes());
					setPoints(points, player, send, label6, labelMessage, labelBonus, radio6, points.getSixes(), "Sixes", points.getSixes());
				}
				if (radio3K.getSelection()){
					points.setThreeK(game.threeOfAKind(roll.getDice(), roll.getValue()));
					points.setLowTotal(points.getThreeK());
					setPoints(points, player, send, label3K, labelMessage, labelBonus, radio3K, points.getThreeK(), "Three of a Kind", points.getThreeK());
				}
				if (radio4K.getSelection()){
					points.setFourK(game.fourOfAKind(roll.getDice(), roll.getValue()));
					points.setLowTotal(points.getFourK());
					setPoints(points, player, send, label4K, labelMessage, labelBonus, radio4K, points.getFourK(), "Four of a Kind", points.getFourK());
				}
				if (radioFull.getSelection()){
					points.setFull(game.fullHouse(roll.getValue()));
					points.setLowTotal(points.getFull());
					setPoints(points, player, send, labelFull, labelMessage, labelBonus, radioFull, points.getFull(), "Full House", points.getFull());
				}
				if (radioSmall.getSelection()){
					points.setSmall(game.smallStraight(roll.getDice()));
					points.setLowTotal(points.getSmall());
					setPoints(points, player, send, labelSmall, labelMessage, labelBonus, radioSmall, points.getSmall(), "Small Straight", points.getSmall());
				}
				if (radioLarge.getSelection()){
					points.setLarge(game.largeStraight(roll.getDice()));
					points.setLowTotal(points.getLarge());
					setPoints(points, player, send, labelLarge, labelMessage, labelBonus, radioLarge, points.getLarge(), "Large Straight", points.getLarge());
				}
				if (radioY.getSelection()){
					points.setYahtzee(game.yahtzee(roll.getValue()));
					points.setLowTotal(points.getYahtzee());
					setPoints(points, player, send, labelY, labelMessage, labelBonus, radioY, points.getYahtzee(), "Yahtzee", points.getYahtzee());
				}
				if (radioC.getSelection()){
					points.setChance(game.chance(roll.getDice()));
					points.setLowTotal(points.getChance());
					setPoints(points, player, send, labelC, labelMessage, labelBonus, radioC, points.getChance(), "Chance", points.getChance());
				}
				
				player.setTurn(player.getTurn() + 1);
						
				if (labelMessage.getText() == serverError){
					return;
				}				
				labelPoints1.setText(String.valueOf(points.getScore()));
				labelUppSub.setText(String.valueOf(points.getUppSub()));
				labelUppTotal.setText(String.valueOf(points.getUppTotal()));
				labelLowTotal.setText(String.valueOf(points.getLowTotal()));
				
				roll.setClicked1(false);
				roll.setClicked2(false);
				roll.setClicked3(false);
				roll.setClicked4(false);
				roll.setClicked5(false);
				dice1.setText("");
				dice2.setText("");
				dice3.setText("");
				dice4.setText("");
				dice5.setText("");
				dice1.setEnabled(false);
				dice2.setEnabled(false);
				dice3.setEnabled(false);
				dice4.setEnabled(false);
				dice5.setEnabled(false);
				
			}
			
			@Override
			public void mouseUp(MouseEvent arg0) {
				
			}
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
			}
		});
		
		buttonNewGame = new Button(shlYahtzee, SWT.NONE);
		buttonNewGame.setBounds(824, 726, 75, 25);
		buttonNewGame.setText("New Game");
		buttonNewGame.setVisible(false);
		buttonNewGame.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseDown(MouseEvent arg0) {
				Display.getDefault().asyncExec(new Runnable() {
					
					@Override
					public void run() {
						try {
							socket = new Socket(player.getServer(), player.getPort());
							InetAddress address = socket.getInetAddress();
							System.out.println("Connecting to: " + address.getHostAddress()+" / " + address.getHostName());
						} catch (IOException e) {
							System.out.println("Server not found! Please try again later...");
							labelMessage.setText(serverError);
						} catch (IllegalArgumentException i) {
							labelMessage.setText("Please enter different port between 1 and 65535.");
							return;
						}
						
						if (labelMessage.getText().equals(serverError)){
							return;
						}
						client = new Client(shlYahtzee, socket, player.getServer(), player.getPort());
						send = new Send(socket);
						send.sendMessage("INIT," + player.getName(), labelMessage, serverError);
						String message = client.recieveMessage(labelMessage, serverError);
						message = message.replaceAll("\0", "");
						if (message.equals("NAME")){
							labelMessage.setText("Waiting for player 2...");
							client.start();
							System.out.println("Thread started");
							buttonNewGame.setEnabled(false);
						}
						else {
							labelMessage.setText("This is not a Yahtzee server!");
							try {
								socket.close();
							} catch (IOException e) {
								labelMessage.setText(serverError);
							}
							return;
						}
					}
				});
			}
			@Override
			public void mouseUp(MouseEvent arg0) {	
			}
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {	
			}
		});
		
		Button buttonExit = new Button(shlYahtzee, SWT.NONE);
		buttonExit.setBounds(913, 726, 75, 25);
		buttonExit.setText("Exit");
		buttonExit.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseDown(MouseEvent arg0) {
				send.sendMessage("KILL,SERVER,RIGHT,NOW", labelMessage, "Server not found! Please try again later...");
				System.exit(1);
			}
			@Override
			public void mouseUp(MouseEvent arg0) {	
			}
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {	
			}
		});
		
		CLabel background = new CLabel(shlYahtzee, SWT.NONE);
		background.setBackground(SWTResourceManager.getImage(GameWindow.class, "/Yahtzee.jpg"));
		background.setBounds(0, 0, 1024, 768);
		background.setText("");
		
	}
}
