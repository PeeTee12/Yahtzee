package exceptions;

import org.eclipse.swt.widgets.Label;

public class ServerNotFound extends RuntimeException {

	/**
	 * Serial number.
	 */
	private static final long serialVersionUID = -643461523363969538L;
	
	public ServerNotFound(Label label){
		label.setText("Please enter your nickname.");
	}

}
