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
		
		/*public void sendMessage(String message, Label label, String error){
			try {
				output = new PrintWriter(socket.getOutputStream());
				output.write(message);
			} catch (IOException e) {
				label.setText(error);
			} catch (NullPointerException n){
				label.setText(error);
			}
			output.flush();
		}*/
		
		public String recieveMessage(Label label, String error){
			char[] buffer = new char[128];
			String message = "";
			try {
				input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				input.read(buffer);
				//System.out.println(buffer);
				message = String.copyValueOf(buffer);
			} catch (IOException e) {
				label.setText(error);
			}
			return message;
		}
		
		public void run(){
			//myThread();
			System.out.println("I'm running!");
			//sendMessage("Init,", null, serverError);
			//System.out.println("Message sent.");
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
					
				
					//System.out.println("I'm running!");
					if (command[0].equals("Init")){
						player = new Player(command[1]);
						con = command[2];
						Display.getDefault().asyncExec(new Runnable() {
							
							@Override
							public void run() {
								
								shellLogin.close();
								gameWindow = new GameWindow(socket, player, con);
								gameWindow.socket = socket;
								gameWindow.open();
								//gameWindow.labelNick2_1.setText(con);	
							}
						});
						//window = new Window(socket);
						//System.out.println(command[1]);
						//System.out.println(command[0]);
						//buffer = null;
					}
					else if (command[0].equals("Lock")){
						Display.getDefault().asyncExec(new Runnable() {
							
							@Override
							public void run() {
								gameWindow.buttonRoll.setEnabled(false);
								gameWindow.labelMessage.setText("Opponent's turn.");
							}
						});
					}
					else if (command[0].equals("Unlock")){
						Display.getDefault().asyncExec(new Runnable() {
							
							@Override
							public void run() {
								gameWindow.buttonRoll.setEnabled(true);
								//gameWindow.labelMessage.setText("");
							}
						});
					}
					else if (command[0].equals("Res")){
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
									gameWindow.labelMessage.setText("You won! Your score was: " + con + " against: " + con2 + ".");									
								}
								
								});
						}
						else {
							Display.getDefault().asyncExec(new Runnable() {
									
								@Override
								public void run() {
									gameWindow.labelMessage.setText("You won! Your score was: " + con + " against: " + con2 + ".");									
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
					else if (command[0].equals("Score")){
						con = command[1];
						Display.getDefault().asyncExec(new Runnable() {
							
							@Override
							public void run() {
								gameWindow.labelPoints2.setText(con);
							}
						});
					}
					else if (command[0].equals("Play")){
						command = message.split(",");
						con = command[3];
						Display.getDefault().asyncExec(new Runnable() {
							
							@Override
							public void run() {
								gameWindow.labelMessage.setText(con);								
							}
						});
					}
					else if (command[0].equals("Kill")){
						System.out.println("Game is over, someone left the game.");
						System.exit(1);
					}
					for(int i=0; i<buffer.length; i++){
						buffer[i] = 0;
					}
				}
			}
			catch (IOException e){
				//gameWindow.labelMessage.setText(serverError);
				System.out.println("Connection failed.");
				System.exit(1);
			}
		}
		
		/*public static void main(String[] args) {
			Client client = new Client(null, null);
			client.connect("10.0.0.2", null, null);
			client.sendMessage("PeeTee", null, null);
			String message = client.recieveMessage(null, null);
			System.out.println(message);
			
			while(true){
			client.sendMessage("Ahoj", null, null);
			String message2 = client.recieveMessage(null, null);
			System.out.println(message2);
			}
			
			
			client.connect("10.0.0.2", null, null);
			client.sendMessage("Krek", null, null);
			
			client.sendMessage("Ahoj", null, null);
			
			String message1 = client.recieveMessage(null, null);
			System.out.println(message1);
			
			
			Thread thread1 = new Thread(client);
			Thread thread2 = new Thread(client);
			thread1.start();
			thread2.start();
		}*/
}
