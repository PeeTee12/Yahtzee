package gui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import client.Client;
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
	private String name;
	private String server;
	private final String serverError = "Server not found! Please try again later...";
	private final String nameError = "Please enter your nickname.";
	private final String emptyServerError = "Please enter server address.";
	
	Client client;
	GameWindow gameWindow;
	
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
	public Login(Shell parent, int style, GameWindow gameWindow) {
		super(parent, style);
		this.gameWindow = gameWindow;
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
		shellLogin.setSize(450, 300);
		shellLogin.setText("Login to Yahtzee");
		shellLogin.setLayout(null);
		client = new Client(gameWindow, shellLogin);
		
		textNick = new Text(shellLogin, SWT.BORDER);
		textNick.setBounds(77, 65, 265, 23);
		
		textServer = new Text(shellLogin, SWT.BORDER);
		textServer.setBounds(77, 131, 265, 23);
		
		final Label labelNick = new Label(shellLogin, SWT.NONE);
		labelNick.setBounds(77, 41, 265, 18);
		labelNick.setText("Enter your nickname");
		
		final Label labelErrorNick = new Label(shellLogin, SWT.NONE);
		labelErrorNick.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		labelErrorNick.setBounds(77, 90, 265, 15);
		
		final Label labelServer = new Label(shellLogin, SWT.NONE);
		labelServer.setBounds(77, 107, 265, 18);
		labelServer.setText("Enter server IP address");
		
		final Label labelErrorServer = new Label(shellLogin, SWT.NONE);
		labelErrorServer.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		labelErrorServer.setBounds(77, 156, 265, 15);
		
		final Label labelWaiting = new Label(shellLogin, SWT.NONE);
		labelWaiting.setBounds(10, 247, 332, 18);
		
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
				client.connect(server, labelErrorServer, serverError);
				if (labelErrorServer.getText() == serverError){
					return;
				}
				labelWaiting.setText("Waiting for player 2...");
				client.start();
				client.sendMessage(name, labelErrorServer, serverError);
				
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
