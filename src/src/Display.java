package src;

import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This file provides a GUI variant of Main.java. This class provides a dialog box to select the set of 
 * JPEG files to run through IntensityReader. The IntensityReader will read them, calculate a region of 
 * interest, and log the region of interest in a CSV file. 
 * @author Chami
 */
public class Display {
	
	/**
	 * Main method - runs the program.
	 * @param args - program arguments which are not used. 
	 */
	public static void main(String[] args) {		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			JOptionPane.showMessageDialog(null, "There was an error loading the system's look and feel.", "Error!", JOptionPane.ERROR_MESSAGE);
		}
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Select input JPEG Files.");
		chooser.setMultiSelectionEnabled(true);
		chooser.setCurrentDirectory(new File(System.getProperty("user.home"))); // for windows this is C:\Users\<System.getProperty("user.name")>
		chooser.setFileFilter(new FileNameExtensionFilter("JPEG Images", "jpg", "jpeg"));
		int choice = chooser.showOpenDialog(null);
		if (choice == JFileChooser.APPROVE_OPTION) {
			File[] files = chooser.getSelectedFiles();
			IntensityReader rdr = new IntensityReader();
			for (File f : files) {
				rdr.read(f);
				rdr.calculateRegionOfInterest();
				int split = f.getAbsolutePath().indexOf(".jpg");
				if (split < 0) {
					split = f.getAbsolutePath().indexOf(".jpeg");
				}
				rdr.writeRegionOfInterestToCSV(f.getAbsolutePath().substring(0, split) + ".csv");
			}
		}
		else if (choice == JFileChooser.ERROR_OPTION) {
			JOptionPane.showMessageDialog(null, "There was an error selecting files.", "Error!", JOptionPane.ERROR_MESSAGE);
		}
		// else { user has cancelled - do nothing } 
	}
}
