package client;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import data.Player;
import data.Points;
import data.Roll;
import gui.GameWindow;

import java.io.*;
import java.net.*;
import java.lang.Thread;

public class Client extends Thread{
		PrintWriter output;
		BufferedReader input;
		Socket socket;
		String server;
		int port;
		GameWindow gameWindow;
		Shell shellLogin;
		Player player;
		Roll roll;
		Points points;
		private String con;
		private String con2;
		private final String serverError = "Server not found! Please try again later...";
		
		public Client(Shell shellLogin, Socket socket, String server, int port) {
			this.shellLogin = shellLogin;
			this.socket = socket;
			this.server = server;
			this.port = port;
			
		}
		/**
		 * Metoda pro navázání spojení, vytváří socket.
		 * @param socket
		 */
		public void connect(String server, int port, Label label, String error){
			try {
				//tahle vec musi byt navazana uz nekde v konstruktoru
				socket = new Socket(server, port);
				InetAddress address = socket.getInetAddress();
				System.out.println("Connecting to: " + address.getHostAddress()+" / " + address.getHostName());
			} catch (IOException e) {
				System.out.println(serverError);
				label.setText(error);
			}
		}
		
		/**
		 * Metoda pro přijetí zprávy ze serveru.
		 * @param label Label v Game Window nebo Login, do kterého se otiskne chyba.
		 * @param error Chybové hlášení
		 * @return Vrací přijatou zprávu.
		 */
		public String recieveMessage(Label label, String error){
			char[] buffer = new char[128];
			String message = "";
			try {
				input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				input.read(buffer);
				message = String.copyValueOf(buffer);
			} catch (IOException e) {
				label.setText(error);
			}
			return message;
		}
		
		/**
		 * Metoda run v tomto vlákně. Přijímá zprávy od serveru, když nějaké přicházejí.
		 */
		public void run(){
			System.out.println("I'm running!");
			char[] buffer = new char[128];
			String message;
			String [] command = null;
			try{
				input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				while(true){
					input.read(buffer);
					
				if (buffer != null);
					message = new String(buffer);
					
					message = message.replaceAll("\0", "");
					command = message.split(",");	
					
				
					if (command[0].equals("INIT")){
						roll = new Roll();
						if (command[1].equals("RETURN")){
							player = new Player(command[2], server, port);
							con = command[3];
							points = new Points(command[4], command[5], command[6], command[7], command[8], command[9], command[10], command[11], command[12], command[13], command[14], command[15], command[16]);
							System.out.println(points.getOnes());
							points.setScore(Integer.parseInt(command[17]));
							Display.getDefault().asyncExec(new Runnable() {
								
								@Override
								public void run() {
									
									shellLogin.close();
									gameWindow = new GameWindow(socket, player, roll, points, con);
									gameWindow.socket = socket;
									gameWindow.open();
								}
							});
						}
						else {
							player = new Player(command[1], server, port);
							con = command[2];
							points = new Points("0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0");
							Display.getDefault().asyncExec(new Runnable() {
								
								@Override
								public void run() {
									
									shellLogin.close();
									gameWindow = new GameWindow(socket, player, roll, points, con);
									gameWindow.socket = socket;
									gameWindow.open();
								}
							});
						}
					}
					else if (command[0].equals("LOCK")){
						Display.getDefault().asyncExec(new Runnable() {
							
							@Override
							public void run() {
								gameWindow.buttonRoll.setEnabled(false);
								gameWindow.labelMessage.setText("Opponent's turn.");
							}
						});
					}
					else if (command[0].equals("UNLOCK")){
						Display.getDefault().asyncExec(new Runnable() {
							
							@Override
							public void run() {
								gameWindow.buttonRoll.setEnabled(true);
							}
						});
					}
					else if (command[0].equals("RES")){
						con = command[1];
						con2 = command[2];
						int score1 = Integer.parseInt(command[1]);
						int score2 = Integer.parseInt(command[2]);
						System.out.println("Your final score is: " + con + " against: " + con2);
						if (score1 > score2){
						Display.getDefault().asyncExec(new Runnable() {
							
							@Override
							public void run() {
									gameWindow.labelMessage.setText("You won! Your score was: " + con + " against: " + con2 + ".");									
								}
							
							});
						}
						else if (score1 < score2){
							Display.getDefault().asyncExec(new Runnable() {
								
								@Override
								public void run() {
									gameWindow.labelMessage.setText("You lose! Your score was: " + con + " against: " + con2 + ".");									
								}
								
								});
						}
						else {
							Display.getDefault().asyncExec(new Runnable() {
									
								@Override
								public void run() {
									gameWindow.labelMessage.setText("You tied! Your score was: " + con + " against: " + con2 + ".");									
								}
									
							});
						}
						Display.getDefault().asyncExec(new Runnable() {
							
							@Override
							public void run() {
								gameWindow.buttonRoll.setEnabled(false);
								gameWindow.buttonNewGame.setVisible(true);
							}
						});
					}
					else if (command[0].equals("SCORE")){
						con = command[1];
						Display.getDefault().asyncExec(new Runnable() {
							
							@Override
							public void run() {
								gameWindow.labelPoints2.setText(con);
							}
						});
					}
					else if (command[0].equals("ROLL")){
						int[] dice = new int[5];
						dice[0] = Integer.parseInt(command[1]);
						dice[1] = Integer.parseInt(command[2]);
						dice[2] = Integer.parseInt(command[3]);
						dice[3] = Integer.parseInt(command[4]);
						dice[4] = Integer.parseInt(command[5]);
						roll.setDice(dice);
					}
					else if (command[0].equals("PLAY")){
						command = message.split(",");
						con = command[3];
						Display.getDefault().asyncExec(new Runnable() {
							
							@Override
							public void run() {
								gameWindow.labelMessage.setText(con);								
							}
						});
					}
					else if (command[0].equals("KILL")){
						if (command[1].equals("NOW")){
							Display.getDefault().asyncExec(new Runnable() {
							
								@Override
								public void run() {
									gameWindow.labelMessage.setText("Your opponent left. Please wait...");
									gameWindow.buttonRoll.setEnabled(false);
									gameWindow.buttonPlay.setEnabled(false);
									gameWindow.buttonNewGame.setVisible(true);
								}
							});
						}
						else if (command[1].equals("CHEAT")){
							Display.getDefault().asyncExec(new Runnable() {
								
								@Override
								public void run() {
									gameWindow.labelMessage.setText("You cheated! Game over. Please quit.");
									gameWindow.buttonRoll.setEnabled(false);
									gameWindow.buttonPlay.setEnabled(false);
								}
							});
							socket.close();
						}
					}
					for(int i=0; i<buffer.length; i++){
						buffer[i] = 0;
					}
				}
			}
			catch (IOException e){
				System.out.println("Connection failed.");
				Display.getDefault().asyncExec(new Runnable() {
					
					@Override
					public void run() {
						gameWindow.labelMessage.setText("Server not found!");								
					}
				});
			}
		}
}
