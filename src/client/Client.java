package client;

import org.eclipse.swt.widgets.Label;

import gui.GameWindow;
import gui.Login;

import java.io.*;
import java.net.*;

public class Client extends Thread{
	
	PrintWriter output;
	BufferedReader input;
	Socket socket;
	GameWindow gameWindow;
	private final String serverError = "Server not found! Please try again later...";
	
	public Client(GameWindow gameWindow) {
		this.gameWindow = gameWindow;
		
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
		String message = null;
		
		while(true){
			message = recieveMessage(gameWindow.labelMessage, serverError);
			if (message != null){
				gameWindow.labelMessage.setText(message);
				message = null;
			}
		}
		
	}
}
