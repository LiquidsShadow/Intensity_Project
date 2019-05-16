package src;

import javax.swing.JOptionPane;

/**
 * Since this program has 2 versions, the console and GUI version, this class provides the ability to reuse
 * error display code in both Console.java and Display.java. Use OutputType.CONSOLE to display to System.out
 * and OutputType.GUI to display to a JOptionPane message dialog.
 * @author Chami
 */
public class ErrorDisplay {
	
	// 2 allowed output types for 2 versions of program.
	public enum OutputType {
		CONSOLE, GUI
	}
	
	private OutputType type; // current output type of this object
	
	/**
	 * Constructs an ErrorDisplay object to be used with a certain OutputType.
	 * @param type - an OutputType
	 */
	public ErrorDisplay(OutputType type) {
		this.type = type;
	}
	
	/**
	 * Default Constructor: constructs an ErrorDisplay object with OutputType.CONSOLE
	 */
	public ErrorDisplay() {
		this(OutputType.CONSOLE);
	}

	/**
	 * Retrieves the OutputType of this object
	 * @return object's OutputType
	 */
	public OutputType getType() {
		return type;
	}

	/**
	 * Allows the user to update the OutputType of this object
	 * @param type - new OutputType
	 */
	public void setType(OutputType type) {
		this.type = type;
	}
	
	/**
	 * Displays an error message with a title based on the OutputType of this object.
	 * @param title - error message's title
	 * @param msg - the error message
	 */
	public void displayError(String title, String msg) {
		if (type == OutputType.CONSOLE) {
			System.out.println(title + ": " + msg);
		}
		else if (type == OutputType.GUI) {
			JOptionPane.showMessageDialog(null, msg, title, JOptionPane.ERROR_MESSAGE);
		}
	}
}
