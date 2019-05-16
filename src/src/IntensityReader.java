package src;

import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.Color;
import javax.imageio.ImageIO;

/**
 * Class represents an Intensity Reader - that is, given a JPEG file can provide a 2D array of intensities
 * (a pixel's intensity is the sum of the pixel's RGB values) as well as a "region of interest" that is 
 * the sums of a specified number of rows for each column. Furthermore, this information can be written
 * to CSV files of the user's choosing.  
 * @author Chami
 */

public class IntensityReader {
	
	// default output CSV filenames
	private static String DEFAULT_ROI_NAME = "region_of_interest.csv";
	private static String DEFAULT_INTENSITY_NAME = "intensity_array.csv";
	
	private int[][] intensityArray; // holds the intensities of each pixel in the image 
	private int[] regionOfInterest; // holds the sums of a specified number of rows for each column
	// number of rows that span the region of interest. This remains the same for the object's lifetime
	// regardless of how many files are passed through it.
	private int numRows; 
	
	/**
	 * Constructs an empty IntensityReader with a specified region of interest row size. 
	 * Use read() to initialize the data.
	 * @param numRows - row size of region of interest
	 * @throws IllegalArgumentException - if numRows < 0
	 */
	public IntensityReader(int numRows) {
		if (numRows < 0) {
			throw new IllegalArgumentException("Number of rows (" + numRows + ") must be a nonnegative integer!");
		}
		intensityArray = null;
		regionOfInterest = null;
		this.numRows = numRows;
	}
		
	/**
	 * Using BufferedImage, populate the intensities array for a given JPEG file.  
	 * @param file - a JPEG file
	 * @throws IOException - if the file cannot be read
	 */
	public void read(File file) throws IOException {
		BufferedImage img = ImageIO.read(file);
		intensityArray = new int[img.getHeight()][img.getWidth()];			
		for (int i = 0; i < intensityArray.length; i++) {
			for (int j = 0; j < intensityArray[0].length; j++) {
				Color c = new Color(img.getRGB(j, i));
				intensityArray[i][j] = c.getBlue() + c.getRed() + c.getGreen();
			}
		}
	}
	
	/**
	 * Retrieves the intensity array for the user to access. 
	 * @return intensity array
	 */
	public int[][] getIntensityArray() {
		return intensityArray;
	}
	
	/**
	 * Writes the intensity array to a specified CSV file. 
	 * @param csvFile - a CSV file
	 * @throws FileNotFoundException - if csvFile cannot be written to.
	 */
	public void writeArrayToCSV(String csvFile) throws FileNotFoundException {
		PrintWriter p = null;
		try {
			p = new PrintWriter(csvFile);
			for (int i = 0; i < intensityArray.length; i++) {
				for (int j = 0; j < intensityArray[0].length - 1; j++) {
					p.print(intensityArray[i][j]+",");
				}
				p.println(intensityArray[i][intensityArray[0].length-1]);
			}
		}
		finally {
			if (p != null) {
				p.close();
			}
		}
	}
	
	/**
	 * Writes the intensity array to a default CSV file. 
	 * @throws FileNotFoundException - if writing to file fails
	 */
	public void writeArrayToCSV() throws FileNotFoundException {
		writeArrayToCSV(DEFAULT_INTENSITY_NAME);
	}
	
	/**
	 * Calculates the region of interest sums as long as the intensity array has been populated and the 
	 * row size of the region of interest is acceptable.
	 * @throws IllegalArgumentException when intensities array is not populated or number of rows in the ROI
	 * is less than the number of rows in the image's array.
	 */
	public void calculateRegionOfInterest() {
		if (intensityArray == null) {
			throw new IllegalArgumentException("Please initialize the intensities array first using \"read()\"!");
		}
		if (numRows > intensityArray.length) {
			throw new IllegalArgumentException("The specified number of rows (" +  numRows + ") in the region of interest is too large. ");
		}
		/*
		 * Since I condensed the original algorithm, I have added a pseudocode version below with a small trace.
		 * 
		 * Let h = intensityArray.length, t = top row, b = bottom row, r = number of rows
		 * t <- 0
		 * b <- 0
		 * if h is even then
		 * 		t <- floor(h/2) - floor(r/2)		(*)
		 *		b <- floor(h/2) + floor(r/2) - 1 	(*)	(~)
		 * {suppose h=8, r=4. Then t=4-2=2 and b=4+2-1=5. These are the correct bounds when r is even}
		 * 		if r is odd then					(~)
		 * 			t <- t - 1
		 * {suppose h=8, r=5. Then t=2 to b=5 is wrong because 5 rows aren't spanned. By default, say the extra row is on top, which means t becomes 2-1 so t to b span 5 rows}
		 * 
		 * if h is odd then
		 * 		t <- floor(h/2) - floor(r/2)		(*)
		 * 		b <- floor(h/2) + floor(r/2)		(*)
		 * {suppose h=7, r=5. Then t=3-2=1 and b=3+2=5. These are correct bounds when r is odd}
		 * 		if r is even then					(~)
		 * 			b <- b - 1
		 * {suppose h=7, r=6. Then t=3-3=0 and b=3+3=6. These bounds are too big as 7 rows are spanned not 6. To account for this, keep the extra row on top and decrease the bottom row.}
		 *
		 * The following lines are just a condensed version of this algorithm. Note that the lines marked (*) are very similar
		 * and can be used to initialize top, bottom at the start (instead of 0) and that there are explicit cases when top and
		 * bottom are edited after initialization which I have marked by (~).
		 */
		int topRow = intensityArray.length/2 - numRows/2;
		int bottomRow = intensityArray.length/2 + numRows/2;
		if (intensityArray.length % 2 == 0 || (intensityArray.length % 2 != 0 && numRows % 2 == 0)) {
			bottomRow--;
		}
		if (intensityArray.length % 2 == 0 && numRows % 2 != 0) {
			topRow--;
		}
		regionOfInterest = new int[intensityArray[0].length];
		for (int j = 0; j < regionOfInterest.length; j++) {
			for (int i = topRow; i <= bottomRow; i++) {
				regionOfInterest[j] += intensityArray[i][j];
			}
		}
	}
	
	/**
	 * Retrieves the region of interest for the user to access.
	 * @return region of interest
	 */
	public int[] getRegionOfInterest() {
		return regionOfInterest;
	}
	
	/**
	 * Writes the region of interest to a specified CSV file. 
	 * @param csvFile - a CSV file 
	 * @throws FileNotFoundException - if csvFile cannot be written to
	 */
	public void writeRegionOfInterestToCSV(String csvFile) throws FileNotFoundException {
		PrintWriter p = null; 
		try {
			p = new PrintWriter(csvFile);
			for (int i = 0; i < regionOfInterest.length; i++) {
				p.println(regionOfInterest[i]);
			}	
		}
		finally {
			if (p != null) {
				p.close();
			}
		}
	}
	
	/**
	 * Writes the region of interest to a default CSV file.
	 * @throws FileNotFoundException - if file writing fails
	 */
	public void writeRegionOfInterestToCSV() throws FileNotFoundException {
		writeRegionOfInterestToCSV(DEFAULT_ROI_NAME);
	}

	/**
	 * Gets the size of the region of interest in number of rows.
	 * @return ROI size in rows.
	 */
	public int getNumRows() {
		return numRows;
	}
	
	/**
	 * Runs an experiment on a set of JPEG files through a provided IntensityReader. In the even of errors
	 * occurring, the method reports to the user through a specified ErrorDisplay object.
	 * @param files - set of JPEG input files
	 * @param err - ErrorDisplay object
	 * @param rdr - an IntensityReader
	 */
	public static void runExperiment(File[] files, ErrorDisplay err, IntensityReader rdr) {
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
				err.displayError("File Writing Error", "Could not write to file: " + e.getMessage());
			}
			catch (IOException e) {
				err.displayError("File Reading Error", "Could not read file \"" + f.getName() + "\": " + e.getMessage());
			}
		}
	}
}
