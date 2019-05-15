package src;

import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This file provides a GUI variant of Main.java. This class provides a dialog box to select the set of 
 * JPEG files to run through IntensityReader. The IntensityReader will read them, calculate a region of 
 * interest, and log the region of interest in a CSV file. The class uses the program arguments to specify
 * the top and bottom percentages of the image (represented as an array) that will be the bounds of the
 * region of interest.
 * @author Chami
 */
public class Display {
	
	/**
	 * Main method - runs the program.
	 * @param args - program arguments, these hold the top and bottom percentages for the region of interest
	 * calculated by IntensityReader (see this file's documentation for more). They are read top first then
	 * bottom, and must be of the double variable type. 
	 */
	public static void main(String[] args) {
		// load system look and feel if possible.
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			JOptionPane.showMessageDialog(null, "There was an error loading the system's look and feel. Using Java look and feel instead.", "Error!", JOptionPane.ERROR_MESSAGE);
		}
		// construct IntensityReader by parsing program arguments
		IntensityReader rdr = null; 
		if (args.length == 2) {
			try {
				rdr = new IntensityReader(Double.parseDouble(args[0]), Double.parseDouble(args[1]));
			}
			catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Invalid program argument inputs (" + args[0] + ", " + args[1] +"). The arguments must be valid double values.\nUsing default top and bottom percentages instead.", "Error!", JOptionPane.ERROR_MESSAGE);
				rdr = new IntensityReader();
			}
			catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog(null, e.getMessage() + "\nUsing default top and bottom percentages instead.", "Error!", JOptionPane.ERROR_MESSAGE);
				rdr = new IntensityReader();
			}
		}
		else if (args.length > 0) {
			JOptionPane.showMessageDialog(null, "Invalid program argument length (" + args.length + ").\nUsing default top and bottom percentages instead.", "Error!", JOptionPane.ERROR_MESSAGE);
			rdr = new IntensityReader();
		}
		else {
			rdr = new IntensityReader();
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
			File[] files = chooser.getSelectedFiles();
			for (File f : files) {
				try {
					rdr.read(f);
					rdr.calculateRegionOfInterest();
					int split = f.getAbsolutePath().indexOf(".jpg");
					if (split < 0) {
						split = f.getAbsolutePath().indexOf(".jpeg");
					}
					rdr.writeRegionOfInterestToCSV(f.getAbsolutePath().substring(0, split) + ".csv");
				}
				catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(null, "Could not write to file: " + e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
				}
				catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Could not read file \"" + f.getName() + "\": " + e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		else if (choice == JFileChooser.ERROR_OPTION) {
			JOptionPane.showMessageDialog(null, "There was an error selecting files.", "Error!", JOptionPane.ERROR_MESSAGE);
		}
		// else { user has cancelled - do nothing } 
	}
}
