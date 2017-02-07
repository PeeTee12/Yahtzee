package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

public class Main {
	
	GameWindow gameWindow;
	Login login;
	static Shell shell = new Shell();
	
	public static void main(String[] args) {
		Login login = new Login(shell, SWT.NONE);
		login.open();
	}

}
