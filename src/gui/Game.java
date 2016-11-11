package gui;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;

public class Game extends ApplicationWindow {

	/**
	 * Create the application window.
	 */
	public Game() {
		super(null);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
	}

	/**
	 * Create contents of the application window.
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		
		Button btnHodit = new Button(container, SWT.NONE);
		//btnHodit.setForeground(SWTResourceManager.getColor(75, 0, 130));
		btnHodit.setBackground(SWTResourceManager.getColor(75, 0, 130));
		btnHodit.setFont(SWTResourceManager.getFont("Comic Sans MS", 14, SWT.NORMAL));
		btnHodit.setBounds(56, 635, 160, 40);
		btnHodit.setText("Hodit");
		
		Label dice1 = new Label(container, SWT.NONE);
		dice1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		dice1.setBounds(280, 617, 77, 77);
		
		Label dice2 = new Label(container, SWT.NONE);
		dice2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		dice2.setBounds(400, 617, 77, 77);
		
		Label dice3 = new Label(container, SWT.NONE);
		dice3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		dice3.setBounds(520, 617, 77, 77);
		
		Label dice4 = new Label(container, SWT.NONE);
		dice4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		dice4.setBounds(640, 617, 77, 77);
		
		Label dice5 = new Label(container, SWT.NONE);
		dice5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		dice5.setBounds(760, 617, 77, 77);
		
		Label background = new Label(container, SWT.NONE);
		background.setImage(SWTResourceManager.getImage("C:\\Users\\Petr\\Disk Google\\Drawing\\Yahtzee.jpg"));
		background.setBounds(0, 0, 1024, 737);
		
		Button radio1 = new Button(container, SWT.RADIO);
		radio1.setBounds(400, 128, 37, 26);
		
		Button radio2 = new Button(container, SWT.RADIO);
		radio2.setBounds(400, 192, 37, 26);
		
		Button radio3 = new Button(container, SWT.RADIO);
		radio3.setBounds(400, 254, 37, 26);
		
		Button radio4 = new Button(container, SWT.RADIO);
		radio4.setBounds(400, 317, 37, 26);
		
		Button radio5 = new Button(container, SWT.RADIO);
		radio5.setBounds(400, 377, 37, 26);
		
		Button radio6 = new Button(container, SWT.RADIO);
		radio6.setBounds(400, 440, 37, 26);
		
		Button radio3K = new Button(container, SWT.RADIO);
		radio3K.setBounds(913, 128, 37, 26);
		
		Button radio4K = new Button(container, SWT.RADIO);
		radio4K.setBounds(913, 192, 37, 26);
		
		Button radioFull = new Button(container, SWT.RADIO);
		radioFull.setBounds(913, 254, 37, 26);
		
		Button radioSmall = new Button(container, SWT.RADIO);
		radioSmall.setBounds(913, 317, 37, 26);
		
		Button radioLarge = new Button(container, SWT.RADIO);
		radioLarge.setBounds(913, 377, 37, 26);
		
		Button radioY = new Button(container, SWT.RADIO);
		radioY.setBounds(913, 440, 37, 26);
		
		Button radioC = new Button(container, SWT.RADIO);
		radioC.setBounds(913, 499, 37, 26);

		return container;
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Game window = new Game();
			window.setBlockOnOpen(true);
			window.open();
			Display.getCurrent().dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Configure the shell.
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		newShell.setMinimumSize(new Point(1040, 780));
		super.configureShell(newShell);
		newShell.setText("Yahtzee");
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}
}
