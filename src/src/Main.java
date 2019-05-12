package src;

public class Main {

	public static void main(String[] args) { 
		IntensityReader rdr = new IntensityReader();
		for (String arg : args) {
			if (arg.endsWith(".jpg")) {
				rdr.read(arg);
				rdr.calculateRegionOfInterest();
				rdr.writeRegionOfInterestToCSV(arg.substring(0, arg.indexOf(".jpg")) + ".csv");
			}
		}
	}
	
}
