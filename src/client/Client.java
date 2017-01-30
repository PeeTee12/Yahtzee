package client;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import data.Player;
import gui.GameWindow;

import java.io.*;
import java.net.*;
import java.lang.Thread;

public class Client extends Thread{
		PrintWriter output;
		BufferedReader input;
		Socket socket;
		GameWindow gameWindow;
		Shell shellLogin;
		Player player;
		private String con;
		private String con2;
		private final String serverError = "Server not found! Please try again later...";
		
		public Client(Shell shellLogin, Socket socket) {
			this.shellLogin = shellLogin;
			this.socket = socket;
			
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
						player = new Player(command[1]);
						con = command[2];
						Display.getDefault().asyncExec(new Runnable() {
							
							@Override
							public void run() {
								
								shellLogin.close();
								gameWindow = new GameWindow(socket, player, con);
								gameWindow.socket = socket;
								gameWindow.open();
							}
						});
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
						System.out.println(con + " " + con2);
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
							System.out.println("Your opponent forfeited. Game over.");
							Display.getDefault().asyncExec(new Runnable() {
							
								@Override
								public void run() {
									gameWindow.labelMessage.setText("Your opponent forfeited. Game over.");
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
