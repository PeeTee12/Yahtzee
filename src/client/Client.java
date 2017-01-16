package client;

import org.eclipse.swt.widgets.Label;
import java.io.*;
import java.net.*;

public class Client {
	
	PrintWriter output;
	BufferedReader input;
	Socket socket;
	
	public Client() {
		
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
		} /*catch (NullPointerException n) {
			System.out.println("Server not found! Please try again later...");
			//label.setText(error);
		}*/
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
		String message = "";
		try {
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			message = input.readLine();
		} catch (IOException e) {
			label.setText(error);
		}
		return message;
	}
	
	public static void main(String[] args) {
		Client client = new Client();
		while (true){
		client.connect("10.0.0.2", null, " ");
		}
	}
}
