package src;

import java.io.*;

/**
 * This class runs the program. The command line arguments are a set of JPEG files to be run through by
 * an IntensityReader. The IntensityReader will read them, calculate a region of interest, and log the
 * region of interest in a CSV file. 
 * @author Chami
 */
public class Main {

	/**
	 * Main method - runs the program. 
	 * @param args - set of input JPEG files. 
	 * @pre only files ending in ".jpg" or ".jpeg" are considered. 
	 */
	public static void main(String[] args) { 
		IntensityReader rdr = new IntensityReader();
		for (String arg : args) {
			if (arg.endsWith(".jpg") || arg.endsWith(".jpeg")) {
				try {
					rdr.read(new File(arg));
					rdr.calculateRegionOfInterest();
					rdr.writeRegionOfInterestToCSV(arg.substring(0, arg.indexOf(".jpg")) + ".csv");
				}
				catch (FileNotFoundException e) {
					System.out.println("Error writing to output file: " + e.getMessage());
				}
				catch (IOException e) {
					System.out.println("Error reading input file \"" + arg + "\": " + e.getMessage());
				}
			}
		}
	}
	
}
