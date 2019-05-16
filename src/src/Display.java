package src;

import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This file provides a GUI variant of Main.java. This class provides a dialog box to select the set of 
 * JPEG files to run through IntensityReader. The IntensityReader will read them, calculate a region of 
 * interest, and log the region of interest in a CSV file. The class uses the program arguments to specify
 * the number of rows in the region of interest. 
 * @author Chami
 */
public final class Display {
	
	/**
	 * Main method - runs the program.
	 * @param args - program arguments, these hold 1 entry, the number of rows in the region of interest to
	 * be calculated by IntensityReader (see this file's documentation for more).
	 */
	public static void main(String[] args) {
		ErrorDisplay err = new ErrorDisplay(ErrorDisplay.OutputType.GUI);
		// load system look and feel if possible.
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			err.displayError("Look and Feel Error", "Could not load system look and feel. Using Java look and feel instead.");
		}
		// construct IntensityReader by parsing program arguments
		IntensityReader rdr = null; 
		if (args.length == 1) {
			try {
				rdr = new IntensityReader(Integer.parseInt(args[0]));
			}
			catch (NumberFormatException e) {
				err.displayError("Program Argument(s) Error", "\"Invalid program argument(s) (" + args[0] + ", " + args[1] + "). The argument must be an integer value.");
			}
			catch (IllegalArgumentException e) {
				err.displayError("Intensity Reader Error", e.getMessage());
			}
		}
		else {
			err.displayError("Program Argument Length Error", "Invalid program argument length.");
		}
		// program set-up failed; exit the program.
		if (rdr == null) {
			return;
		}
		// set-up and open file selection dialog for user to choose input files
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Select input JPEG Files.");
		chooser.setMultiSelectionEnabled(true);
		chooser.setCurrentDirectory(new File(System.getProperty("user.home"))); // for windows this is C:\Users\<System.getProperty("user.name")>
		chooser.setFileFilter(new FileNameExtensionFilter("JPEG Images", "jpg", "jpeg"));
		int choice = chooser.showOpenDialog(null);
		if (choice == JFileChooser.APPROVE_OPTION) {
			// run input files through IntensityReader 
			IntensityReader.runExperiment(chooser.getSelectedFiles(), err, rdr);
		}
		else if (choice == JFileChooser.ERROR_OPTION) {
			err.displayError("File Selection Error", "There was an error selecting files.");
		}
		// else { user has cancelled - do nothing } 
	}
}
