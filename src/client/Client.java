package client;

import java.io.*;
import java.net.*;

public class Client {
	
	public Client() {
		
	}
	/**
	 * Metoda pro navázání spojení, vytváří socket.
	 * @param socket
	 */
	public void connect(Socket socket){
		try {
			//tahle vec musi byt navazana uz nekde v konstruktoru
			socket = new Socket("127.0.0.1", 10001);
			InetAddress address = socket.getInetAddress();
			System.out.println("Connecting to: " + address.getHostAddress()+" / " + address.getHostName());
		} catch (IOException u){
			System.out.println("Server nenalezen!");
		}
	}
	
	public void sendMessage(String message, PrintWriter output, Socket socket){
		try {
			output = new PrintWriter(socket.getOutputStream());
			output.write(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		output.flush();
	}
	
	public String recieveMessage(BufferedReader input, Socket socket){
		String message = "";
		try {
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			message = input.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return message;
	}
}
