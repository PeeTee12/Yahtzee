package gui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;

public class Login extends Dialog {

	protected Object result;
	protected Shell shellLogin;
	private Text textNick;
	private Text textServer;
	private String name;
	
	public String getName(){
		return name;
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
		shellLogin.setSize(450, 300);
		shellLogin.setText("Login to Yahtzee");
		shellLogin.setLayout(null);
		
		textNick = new Text(shellLogin, SWT.BORDER);
		textNick.setBounds(77, 62, 265, 21);
		
		textServer = new Text(shellLogin, SWT.BORDER);
		textServer.setBounds(77, 123, 265, 21);
		
		Button buttonJoin = new Button(shellLogin, SWT.NONE);
		buttonJoin.setBounds(77, 188, 75, 25);
		buttonJoin.setText("Join Game");
		
		Button buttonCreate = new Button(shellLogin, SWT.NONE);
		buttonCreate.setBounds(226, 188, 116, 25);
		buttonCreate.setText("Create a New Game");
		buttonCreate.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseDown(MouseEvent arg0) {
				name = textNick.getText();
				GameWindow window = new GameWindow();
				shellLogin.close();
				window.open();
			}
			
			@Override
			public void mouseUp(MouseEvent arg0) {
			}
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
			}
		});
		
		Label labelNick = new Label(shellLogin, SWT.NONE);
		labelNick.setBounds(77, 41, 116, 15);
		labelNick.setText("Enter your nickname");
		
		Label labelServer = new Label(shellLogin, SWT.NONE);
		labelServer.setBounds(77, 102, 132, 15);
		labelServer.setText("Enter server IP address");

	}
}
