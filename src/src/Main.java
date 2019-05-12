package src;

/**
 * This class runs the program. The command line arguments are a set of .jpg files to be run through by
 * an IntensityReader. The IntensityReader will read them, calculate a region of interest, and log the
 * region of interest in a CSV file. 
 * @author Chami
 */
public class Main {

	/**
	 * Main method - runs the program. 
	 * @param args - set of input .jpg files. 
	 * @pre only files ending in ".jpg" are considered. 
	 */
	public static void main(String[] args) { 
		IntensityReader rdr = new IntensityReader();
		for (String arg : args) {
			if (arg.endsWith(".jpg") || arg.endsWith(".jpeg")) {
				rdr.read(arg);
				rdr.calculateRegionOfInterest();
				rdr.writeRegionOfInterestToCSV(arg.substring(0, arg.indexOf(".jpg")) + ".csv");
			}
		}
	}
	
}
