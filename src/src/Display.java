package src;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Display {

	public static void main(String[] args) {
		JFileChooser chooser = new JFileChooser();
		chooser.setMultiSelectionEnabled(true);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG Images", "jpg", "jpeg");
		chooser.addChoosableFileFilter(filter);
		int choice = chooser.showOpenDialog(null);
		if (choice == JFileChooser.APPROVE_OPTION) {
			File[] files = chooser.getSelectedFiles();
			IntensityReader rdr = new IntensityReader();
			for (File f : files) {
				rdr.read(f);
				rdr.calculateRegionOfInterest();
				int split = f.getName().indexOf(".jpg");
				if (split == -1) {
					split = f.getName().indexOf(".jpeg");
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
