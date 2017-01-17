package gui;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.wb.swt.SWTResourceManager;

import data.Game;
import data.Player;
import data.Points;
import data.Roll;
import client.Client;

public class GameWindow {

	protected Shell shlYahtzee = new Shell();
	Roll roll;
	Game game;
	Player player;
	Points points;
	Button hodit;
	Button play;
	Client client;
	static Login login;
	int rolls = 0;
	private final String serverError = "Server not found! Please try again later...";
	private String message;
	
	/**
	 * Metoda pro nastavení bodù do promìnných, deaktivace a aktivace tlaèítek, nastavení hodù na 0 a odeslání zprávy na server.
	 * @param radio Radio button, který se ovládá.
	 * @param label Popisek, který se ovládá.
	 * @param player Hráè, kterému se nastavují body.
	 * @param points Body, které se nastavují.
	 */
	
	public void setPoints(Points points, Player player, Label label,  Label labelMessage, Button radio, int playedPoints){
		label.setText(String.valueOf(playedPoints));
		player.setPoints(Integer.parseInt(label.getText()));
		points.setScore(player.getPoints());
		radio.setEnabled(false);
		radio.setSelection(false);
		play.setEnabled(false);
		rolls = 0;
		hodit.setEnabled(true);
		labelMessage.setText(message);
		//client.sendMessage(message, labelMessage, serverError);
	}
	
	/**
	 * Metoda pro èekání až pøijde hráè na øadu.
	 * @param player
	 * @param hodit
	 * @param play
	 * @param dice1
	 * @param dice2
	 * @param dice3
	 * @param dice4
	 * @param dice5
	 * @param label
	 */
	public void waitForTurn(Player player, Button hodit, Button play, CLabel dice1, CLabel dice2, CLabel dice3, CLabel dice4, CLabel dice5, Label label){
		if(!player.isMyTurn()){
			hodit.setVisible(false);
			play.setVisible(false);
			dice1.setVisible(false);
			dice2.setVisible(false);
			dice3.setVisible(false);
			dice4.setVisible(false);
			dice5.setVisible(false);
			label.setVisible(true);
		}
		else {
			hodit.setVisible(true);
			play.setVisible(true);
			dice1.setVisible(true);
			dice2.setVisible(true);
			dice3.setVisible(true);
			dice4.setVisible(true);
			dice5.setVisible(true);
			label.setVisible(false);
			player.setTurn(player.getTurn() + 1);
		}
	}

	/**
	 * Spuštìní aplikace.
	 * @param args
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {
		try {
			GameWindow window = new GameWindow();
			window.open();
			//login = new Login(window.shlYahtzee, SWT.NONE);
			//login.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Otevøení okna.
	 * @wbp.parser.entryPoint
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlYahtzee.setImage(SWTResourceManager.getImage("Six.jpg"));
		shlYahtzee.open();
		shlYahtzee.layout();
		while (!shlYahtzee.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Vytvoøení obsahu okna.
	 */
	protected void createContents() {
		//shlYahtzee = new Shell();
		shlYahtzee.setMinimumSize(new Point(1041, 807));
		shlYahtzee.setSize(450, 300);
		shlYahtzee.setText("Yahtzee");
		roll = new Roll();
		game = new Game();
		player = new Player("PeeTee");
		points = new Points();
		client = new Client();
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
		
		Label labelPoints2 = new Label(shlYahtzee, SWT.NONE);
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
		
		Label labelNick2 = new Label(shlYahtzee, SWT.NONE);
		labelNick2.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		labelNick2.setBackground(SWTResourceManager.getColor(87, 55, 171));
		labelNick2.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 25, SWT.NORMAL));
		labelNick2.setAlignment(SWT.RIGHT);
		labelNick2.setBounds(739, 21, 260, 40);
		
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
		
		final Label labelMessage = new Label(shlYahtzee, SWT.NONE);
		labelMessage.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		labelMessage.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 15, SWT.NORMAL));
		labelMessage.setBounds(10, 725, 940, 26);
		
		final Label labelTurn = new Label(shlYahtzee, SWT.NONE);
		labelTurn.setBackground(SWTResourceManager.getColor(250, 229, 108));
		labelTurn.setAlignment(SWT.CENTER);
		labelTurn.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 20, SWT.NORMAL));
		labelTurn.setBounds(100, 635, 824, 40);
		labelTurn.setVisible(false);

		hodit = new Button(shlYahtzee, SWT.NONE);
		hodit.setBackground(SWTResourceManager.getColor(75, 0, 130));
		hodit.setFont(SWTResourceManager.getFont("Comic Sans MS", 14, SWT.NORMAL));
		hodit.setBounds(25, 635, 160, 40);
		hodit.setText("Roll");
		hodit.addMouseListener(new MouseListener() {
			@Override
			public void mouseDown(MouseEvent arg0) {
				roll.rollDice();
				roll.dicePicture(roll.getDice(), 0, dice1);
				roll.dicePicture(roll.getDice(), 1, dice2);
				roll.dicePicture(roll.getDice(), 2, dice3);
				roll.dicePicture(roll.getDice(), 3, dice4);
				roll.dicePicture(roll.getDice(), 4, dice5);
				roll.valueCounter(roll.getDice());
				play.setEnabled(true);
				rolls++;
				if (rolls == 3){
					hodit.setEnabled(false);
				}				
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {				
			}
			
			@Override
			public void mouseUp(MouseEvent arg0) {				
			}
		});
		
		play = new Button(shlYahtzee, SWT.NONE);
		play.setText("Play");
		play.setFont(SWTResourceManager.getFont("Comic Sans MS", 14, SWT.NORMAL));
		play.setBackground(SWTResourceManager.getColor(75, 0, 130));
		play.setBounds(840, 635, 160, 40);
		play.setEnabled(false);
		play.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseDown(MouseEvent arg0) {
				if (radio1.getSelection()){
					points.setOnes(game.ones(roll.getValue()));
					points.setUppSub(points.getOnes());
					message = player.getName() + " played " + points.getOnes() + " on Ones.";
					setPoints(points, player, label1, labelMessage, radio1, points.getOnes());
				}
				if (radio2.getSelection()){
					points.setTwos(game.twos(roll.getValue()));
					points.setUppSub(points.getTwos());
					message = player.getName() + " played " + points.getTwos() + " on Twos.";
					setPoints(points, player, label2, labelMessage, radio2, points.getTwos());
				}
				if (radio3.getSelection()){
					points.setThrees(game.threes(roll.getValue()));
					points.setUppSub(points.getThrees());
					message = player.getName() + " played " + points.getThrees() + " on Threes.";
					setPoints(points, player, label3, labelMessage, radio3, points.getThrees());
				}
				if (radio4.getSelection()){
					points.setFours(game.fours(roll.getValue()));
					points.setUppSub(points.getFours());
					message = player.getName() + " played " + points.getFours() + " on Fours.";
					setPoints(points, player, label4, labelMessage, radio4, points.getFours());
				}
				if (radio5.getSelection()){
					points.setFives(game.fives(roll.getValue()));
					points.setUppSub(points.getFives());
					message = player.getName() + " played " + points.getFives() + " on Fives.";
					setPoints(points, player, label5, labelMessage, radio5, points.getFives());
				}
				if (radio6.getSelection()){
					points.setSixes(game.sixes(roll.getValue()));
					points.setUppSub(points.getSixes());
					message = player.getName() + " played " + points.getSixes() + " on Sixes.";
					setPoints(points, player, label6, labelMessage, radio6, points.getSixes());
				}
				if (radio3K.getSelection()){
					points.setThreeK(game.threeOfAKind(roll.getDice(), roll.getValue()));
					points.setLowTotal(points.getThreeK());
					message = player.getName() + " played " + points.getThreeK() + " on 3 of a Kind.";
					setPoints(points, player, label3K, labelMessage, radio3K, points.getThreeK());
				}
				if (radio4K.getSelection()){
					points.setFourK(game.fourOfAKind(roll.getDice(), roll.getValue()));
					points.setLowTotal(points.getFourK());
					message = player.getName() + " played " + points.getFourK() + " on 4 of a Kind.";
					setPoints(points, player, label4K, labelMessage, radio4K, points.getFourK());
				}
				if (radioFull.getSelection()){
					points.setFull(game.fullHouse(roll.getValue()));
					points.setLowTotal(points.getFull());
					message = player.getName() + " played " + points.getFull() + " on Full House.";
					setPoints(points, player, labelFull, labelMessage, radioFull, points.getFull());
				}
				if (radioSmall.getSelection()){
					points.setSmall(game.smallStraight(roll.getDice()));
					points.setLowTotal(points.getSmall());
					message = player.getName() + " played " + points.getSmall() + " on Small Straight.";
					setPoints(points, player, labelSmall, labelMessage, radioSmall, points.getSmall());
				}
				if (radioLarge.getSelection()){
					points.setLarge(game.largeStraight(roll.getDice()));
					points.setLowTotal(points.getLarge());
					message = player.getName() + " played " + points.getLarge() + " on Large Straight.";
					setPoints(points, player, labelLarge, labelMessage, radioLarge, points.getLarge());
				}
				if (radioY.getSelection()){
					points.setYahtzee(game.yahtzee(roll.getValue()));
					points.setLowTotal(points.getYahtzee());
					message = player.getName() + " played " + points.getYahtzee() + " on Yahtzee.";
					setPoints(points, player, labelY, labelMessage, radioY, points.getYahtzee());
				}
				if (radioC.getSelection()){
					points.setChance(game.chance(roll.getDice()));
					points.setLowTotal(points.getChance());
					message = player.getName() + " played " + points.getChance() + " on Chance.";
					setPoints(points, player, labelC, labelMessage, radioC, points.getChance());
				}
				
				player.setTurn(player.getTurn() + 1);
				if (points.getUppSub() > 62 && !player.isBonus()){
					points.setScore(35);
					points.setUppTotal(points.getUppSub() + 35);
					labelBonus.setText("35");
					player.setBonus(true);
					message += " " + player.getName() + " just achieved bonus 35 points.";
					labelMessage.setText(message);
				}
				
				if (labelMessage.getText() == serverError){
					return;
				}				
				labelPoints1.setText(String.valueOf(points.getScore()));
				//client.sendMessage(String.valueOf(points.getScore()), labelMessage, serverError);
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
				
				/*player.setMyTurn(false);
				waitForTurn(player, hodit, play, dice1, dice2, dice3, dice4, dice5, labelTurn);*/
				
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
