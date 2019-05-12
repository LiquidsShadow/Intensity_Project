package src;

import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.Color;
import javax.imageio.ImageIO;

/**
 * Class represents an Intensity Reader - that is, given a .jpg file can provide a 2D array of intensities
 * (a pixel's intensity is the sum of the pixel's RGB values) as well as a "region of interest" that is 
 * the sums of the middle 1/5 of the rows for each column. Furthermore, this information can be written
 * to CSV files of the user's choosing.  
 * @author Chami
 */

public class IntensityReader {

	private int[][] intensityArray; // holds the intensities of each pixel in the image 
	private int[] regionOfInterest; // holds the sums of the middle 1/5 of the rows for each column
	
	/**
	 * Constructs an empty Intensity Reader. Use read() to initialize the data. 
	 */
	public IntensityReader() {
		intensityArray = null;
		regionOfInterest = null;
	}
	
	/**
	 * Using BufferedImage, populate the intensities array for a given .jpg file.  
	 * @param filename - a .jpg file
	 */
	public void read(String filename) {
		try {
			BufferedImage img = ImageIO.read(new File(filename));
			intensityArray = new int[img.getHeight()][img.getWidth()];			
			for (int i = 0; i < intensityArray.length; i++) {
				for (int j = 0; j < intensityArray[0].length; j++) {
					Color c = new Color(img.getRGB(j, i));
					intensityArray[i][j] = c.getBlue() + c.getRed() + c.getGreen();
				}
			}
		}
		catch (IOException e) {
			System.out.println("Error opening file! " + e.getMessage());
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
	 */
	public void writeArrayToCSV(String csvFile) {
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
		catch (FileNotFoundException f) {
			System.out.println("Error writing to file! " + csvFile + " > " + f.getMessage());
		}
		finally {
			if (p != null) {
				p.close();
			}
		}
	}
	
	/**
	 * Writes the intensity array to a default CSV file. 
	 */
	public void writeArrayToCSV() {
		writeArrayToCSV("intensity_array.csv");
	}
	
	/**
	 * Calculates the region of interest (sums of middle 1/5 rows for each column) as long as the intensity
	 * array has been populated. 
	 * @throws IllegalArgumentException when array is not populated.
	 */
	public void calculateRegionOfInterest() {
		if (intensityArray == null) {
			throw new IllegalArgumentException("Please initialize the intensities first using \"read()\"!");
		}
		int topRow = (int)(Math.floor(intensityArray.length*0.4));
		int bottomRow = (int)(Math.ceil(intensityArray.length*0.6));
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
	 */
	public void writeRegionOfInterestToCSV(String csvFile) {
		PrintWriter p = null; 
		try {
			p = new PrintWriter(csvFile);
			for (int i = 0; i < regionOfInterest.length; i++) {
				p.println(regionOfInterest[i]);
			}	
		}
		catch(FileNotFoundException f) {
			System.out.println("error writing to file: " + csvFile + " > " + f.getMessage());
		}
		finally {
			if (p != null) {
				p.close();
			}
		}
	}
	
	/**
	 * Writes the region of interest to a default CSV file.
	 */
	public void writeRegionOfInterestToCSV() {
		writeRegionOfInterestToCSV("region_of_interest.csv");
	}
}
