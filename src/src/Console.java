package src;

import java.util.*;
import java.io.*;

/**
 * This class runs the program. The command line arguments specify the input file for which the program will
 * use to run. The file includes a set of input JPEG files and a number of rows for the region of interest.
 * The IntensityReader will read them, calculate the region of interest, and log the region of interest in 
 * a CSV file. 
 * @author Chami
 */
public final class Console {

	/**
	 * Main method - runs the program. 
	 * @param args - input file that holds the input JPEG files and the number of rows of the region of interest
	 */
	public static void main(String[] args) { 
		ErrorDisplay err = new ErrorDisplay(ErrorDisplay.OutputType.CONSOLE);
		// read region of interest row number and input JPEG files and store the latter in a LinkedList 
		List<File> files = new LinkedList<>();
		IntensityReader rdr = null;
		if (args.length == 1) {
			Scanner fileRdr = null;
			try {
				fileRdr = new Scanner(new File(args[0]));
				if (fileRdr.hasNextInt()) {
					rdr = new IntensityReader(fileRdr.nextInt());
				}		
				while (fileRdr.hasNext()) {
					String filename = fileRdr.next();
					if (filename.endsWith(".jpg") || filename.endsWith(".jpeg")) {
						files.add(new File(filename));
					}
				}			
			} 
			catch (FileNotFoundException e) {
				err.displayError("File Reading Error", "Could not read file \"" + args[0] + "\": " + e.getMessage());
			}
			finally {
				if (fileRdr != null) {
					fileRdr.close();
				}
			}
		}
		else {
			err.displayError("Program Argument Length Error", "Invalid program argument length.");
		}
		// row number not found in input file, exit program
		if (rdr == null) { 
			return;
		}
		// run JPEG files through IntensityReader
		IntensityReader.runExperiment(files.toArray(new File[files.size()]), err, rdr);
	}
	
}
