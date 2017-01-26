package gui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import client.Client;
import client.Send;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

public class Login extends Dialog {

	protected Object result;
	public Shell shellLogin;
	private Text textNick;
	private Text textServer;
	public Label labelNick;
	private String name;
	private String server;
	private int port;
	Socket socket;
	private final String serverError = "Server not found! Please try again later...";
	private final String nameError = "Please enter your nickname.";
	private final String emptyServerError = "Please enter server address and port.";

	Client client;
	Send send;
	private Text textPort;
	
	public String getName(){
		return name;
	}
	
	public String getServer(){
		return server;
	}

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public Login(Shell parent, int style) {
		super(parent, style);
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shellLogin.open();
		shellLogin.layout();
		Display display = getParent().getDisplay();
		while (!shellLogin.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shellLogin = new Shell(getParent(), SWT.CLOSE | SWT.TITLE);
		shellLogin.setImage(SWTResourceManager.getImage(Login.class, "/Six.jpg"));
		shellLogin.setSize(450, 300);
		shellLogin.setText("Login to Yahtzee");
		shellLogin.setLayout(null);
		
		textNick = new Text(shellLogin, SWT.BORDER);
		textNick.setBounds(77, 65, 265, 23);
		
		textServer = new Text(shellLogin, SWT.BORDER);
		textServer.setBounds(77, 131, 193, 23);
		
		textPort = new Text(shellLogin, SWT.BORDER);
		textPort.setBounds(284, 131, 76, 23);
		
		labelNick = new Label(shellLogin, SWT.NONE);
		labelNick.setBounds(77, 41, 265, 18);
		labelNick.setText("Enter your nickname");
		
		final Label labelErrorNick = new Label(shellLogin, SWT.NONE);
		labelErrorNick.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		labelErrorNick.setBounds(77, 90, 265, 15);
		
		final Label labelServer = new Label(shellLogin, SWT.NONE);
		labelServer.setBounds(77, 107, 193, 18);
		labelServer.setText("Enter server IP address");
		
		final Label labelErrorServer = new Label(shellLogin, SWT.NONE);
		labelErrorServer.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		labelErrorServer.setBounds(77, 156, 265, 15);
		
		Label labelPort = new Label(shellLogin, SWT.NONE);
		labelPort.setBounds(284, 107, 76, 18);
		labelPort.setText("Enter port");
		
		final Label labelWaiting = new Label(shellLogin, SWT.NONE);
		labelWaiting.setBounds(10, 229, 332, 18);
		
		Button buttonPlay = new Button(shellLogin, SWT.NONE);
		buttonPlay.setBounds(163, 187, 91, 36);
		buttonPlay.setText("Play");
		buttonPlay.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseDown(MouseEvent arg0) {
				name = textNick.getText();
				if (textNick.getText() == "") {
					labelErrorNick.setText(nameError);
					return;
				}
				else {
					labelErrorNick.setText("");
				}
				server = textServer.getText();
				if (textServer.getText() == "") {
					labelErrorServer.setText(emptyServerError);
					return;
				}
				else {
					labelErrorServer.setText("");
				}
				port = Integer.parseInt(textPort.getText());
				if (textPort.getText() == "") {
					labelErrorServer.setText(emptyServerError);
					return;
				}
				try {
					//tahle vec musi byt navazana uz nekde v konstruktoru
					socket = new Socket(server, port);
					InetAddress address = socket.getInetAddress();
					System.out.println("Connecting to: " + address.getHostAddress()+" / " + address.getHostName());
				} catch (IOException e) {
					System.out.println("Server not found! Please try again later...");
					labelErrorServer.setText(serverError);
				}
				if (labelErrorServer.getText() == serverError){
					return;
				}
				labelWaiting.setText("Waiting for player 2...");
				client = new Client(shellLogin, socket);
				send = new Send(socket);
				client.start();
				send.sendMessage("Init," + name, labelErrorServer, serverError);
				System.out.println("Thread started");
				
				/*String message = client.recieveMessage(labelErrorServer, serverError);
				System.out.println(message);
				if (message == "0"){
					labelErrorNick.setText("Nickname already used!");
					return;
				}
				else {
				}*/
			}	
			@Override
			public void mouseUp(MouseEvent arg0) {
			}
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
			}
		});

	}
}
