package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import org.eclipse.swt.widgets.Label;

public class Send {
	
	Socket socket;
	PrintWriter output;
	
	public Send(Socket socket){
		this.socket = socket;
	}
	
	public void sendMessage(String message, Label label, String error){
		try {
			output = new PrintWriter(socket.getOutputStream());
			output.write(message);
		} catch (IOException e) {
			e.printStackTrace();
			label.setText(error);
			System.out.println(error);
		}
		output.flush();
	}

}
