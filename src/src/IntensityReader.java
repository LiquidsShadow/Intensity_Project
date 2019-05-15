package src;

import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.Color;
import javax.imageio.ImageIO;

/**
 * Class represents an Intensity Reader - that is, given a JPEG file can provide a 2D array of intensities
 * (a pixel's intensity is the sum of the pixel's RGB values) as well as a "region of interest" that is 
 * the sums of a specified percentage of rows for each column. Furthermore, this information can be written
 * to CSV files of the user's choosing.  
 * @author Chami
 */

public class IntensityReader {
	
	// if the user doesn't specify a top and bottom these can be used in place.
	private static double DEFAULT_TOP = 0.4; 
	private static double DEFAULT_BOTTOM = 0.6;
	// default output CSV filenames
	private static String DEFAULT_ROI_NAME = "region_of_interest.csv";
	private static String DEFAULT_INTENSITY_NAME = "intensity_array.csv";
	
	private int[][] intensityArray; // holds the intensities of each pixel in the image 
	private int[] regionOfInterest; // holds the sums of a specified percentage of the rows for each column
	private double topPercent; // top percent of the region of interest 
	private double btmPercent; // bottom percent of the region of interest
	
	/**
	 * Constructs an empty IntensityReader with a specified top and bottom percentages. Use read() to initialize the data.
	 * @param topPercent - top percent of region of interest
	 * @param btmPercent - bottom percent of region of interest
	 * @throws IllegalArgumentException - (i) percents do not lie on [0, 1] or (ii) btmPercent < topPercent
	 */
	public IntensityReader(double topPercent, double btmPercent) {
		if (topPercent < 0 || topPercent > 1 || btmPercent < 0 || btmPercent > 1 || btmPercent < topPercent) {
			throw new IllegalArgumentException("Percentages are invalid (top: " + topPercent + ", bottom: " + btmPercent + "). They must lie in [0, 1] and top percent must be less than or equal to the bottom percent.");
		}
		intensityArray = null;
		regionOfInterest = null;
		this.topPercent = topPercent;
		this.btmPercent = btmPercent;
	}
	
	/**
	 * Default Constructor: Constructs an empty IntensityReader. Use read() to initialize the data. 
	 */
	public IntensityReader() {
		this(DEFAULT_TOP, DEFAULT_BOTTOM);
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
	 * Calculates the region of interest sums of the row starting from a top percent to a bottom percent
	 * as long as the intensity array has been populated.
	 * @throws IllegalArgumentException when intensities array is not populated 
	 */
	public void calculateRegionOfInterest() {
		if (intensityArray == null) {
			throw new IllegalArgumentException("Please initialize the intensities array first using \"read()\"!");
		}
		int topRow = (int)(Math.floor(intensityArray.length*topPercent));
		int bottomRow = (int)(Math.ceil(intensityArray.length*btmPercent));
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
	 * Gets the top percentage of the region of interest
	 * @return top percent of ROI
	 */
	public double getTopPercent() {
		return topPercent;
	}
	
	/**
	 * Gets the bottom percentage of the region of interest
	 * @return btm. percent of ROI
	 */
	public double getBtmPercent() {
		return btmPercent;
	}
}
