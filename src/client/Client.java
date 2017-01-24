package client;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

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
	private final String serverError = "Server not found! Please try again later...";
	
	public Client(GameWindow gameWindow, Shell shellLogin) {
		this.gameWindow = gameWindow;
		this.shellLogin = shellLogin;
		
	}
	/**
	 * Metoda pro navázání spojení, vytváří socket.
	 * @param socket
	 */
	public void connect(String server, Label label, String error){
		try {
			//tahle vec musi byt navazana uz nekde v konstruktoru
			socket = new Socket(server, 10001);
			InetAddress address = socket.getInetAddress();
			System.out.println("Connecting to: " + address.getHostAddress()+" / " + address.getHostName());
		} catch (IOException e) {
			System.out.println("Server not found! Please try again later...");
			label.setText(error);
		}
	}
	
	public void sendMessage(String message, Label label, String error){
		try {
			output = new PrintWriter(socket.getOutputStream());
			output.write(message);
		} catch (IOException e) {
			label.setText(error);
		}
		output.flush();
	}
	
	public String recieveMessage(Label label, String error){
		char[] buffer = new char[128];
		String message = "";
		try {
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			input.read(buffer);
			System.out.println(buffer);
			message = String.copyValueOf(buffer);
		} catch (IOException e) {
			label.setText(error);
		}
		return message;
	}
	
	public void chooseOperation(int choice, Label label){
		switch(choice){
		case 0: label.setText("Nickname already used!");
		break;
		case 1: label.setText(recieveMessage(label, ""));
		break;
		}
	}
	
	public void run(){
		Display.getDefault().asyncExec(new Runnable() {
			
		@Override
		public void run() {
						
		char[] buffer = new char[128];
		try{
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			input.read(buffer);
		
			String message;
			String [] command;
		
			while(true){
				message = new String(buffer);
				message.replaceAll("\0", "");
				command = message.split(",");
		
				//System.out.println("I'm running!");
				if (command[0].equals("Init")){
					shellLogin.close();
					gameWindow = new GameWindow();
					gameWindow.open();
					gameWindow.labelNick2.setText(command[1]);
					System.out.println(command[1]);
				}
				else if (command[0].equals("Name")){
					gameWindow.labelNick2.setText(message);
					message = null;
				}
			}
		}
		catch (IOException e){
			gameWindow.labelMessage.setText(serverError);
		}
		
			}
		});
	}
}
