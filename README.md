# Intensity Project

**NOTE:** Due to development still being in progress, this file is not fully complete and some links are not correct (denoted "UPDATE LINK").

The purpose of this project was to provide a way to take a .jpg file (or set of files) and convert them into 2D integer arrays that hold intensities of the pixels in the image. The intensities are calculated as the sum of the RGB values as of the moment. However, this could be improved upon. A region of interest can be calculated by specifying the number of rows in the region. This data can then be written to CSV files which can be then opened in Microsoft Excel and plotted to display peaks or dips in intensity. For more, see the [documentation](https://github.com/LiquidsShadow/Intensity_Project/blob/master/src/src/IntensityReader.java) of **IntensityReader.java**.

## Getting Started

There are a few initial steps to get this program running.

### Prerequisites

This project was developed using [Java 8](https://www.java.com/en/download/) and [JDK version 1.8.0_201](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html). These are needed in order to run the Java source files through the Java Virtual Machine meant for your operating system. Lastly, you have to make sure that the JDK folder has been added to the system path, especially if you are not using an IDE. [Eclipse](https://www.eclipse.org/downloads/) does this for you, but some IDEs may not. I do not know how this is done on OSX, but for windows, this [UPDATE LINK](google.com) was quite useful.

### Installing & Using GUI Version

The necessary Java source files are **ErrorDisplay.java**, **IntensityReader.java**, and **Display.java**. To use them without downloading the entire .zip file associated with the repository, you could just copy-paste their contents into empty text files on your computer with the same names. If you do *not* keep the entire Eclipse project, remove the following line from the top of both files:

```
package src;
```

**Display.java** provides a dialog window for the user to select JPEG images to run through IntensityReader. To read more about this, see the [UPDATE LINK](https://github.com/LiquidsShadow/Intensity_Project/blob/master/src/src/Main.java) in **Display.java**.

If you are not using Eclipse or another IDE and are on Windows, save (or copy) **gui_runner.bat** to run the program for you on a set of files that you will select. If you are not using Windows, run the following commands in a UNIX command line:

```
javac ErrorDisplay.java
javac IntensityReader.java
javac Display.java
java Display
```

To specify the number of rows (say 10), change line 5 of **gui_runner.bat** (or the respective command in a UNIX command line) to the following:

```
java Display 10
```

If you wish to keep the batch files and source files in separate directories, the batch file must be edited. For example, if I was again running the program on the text files mentioned above, but I moved the source files to "C:\\Users\\Chami\SourceFiles\\", lines 2-5 of **gui_runner.bat** (or your commands in a UNIX command line) would look like this:

```
javac C:\\Users\\Chami\\SourceFiles\\ErrorDisplay.java
javac C:\\Users\\Chami\\SourceFiles\\IntensityReader.java
javac C:\\Users\\Chami\\SourceFiles\\Display.java
java C:\\Users\\Chami\\SourceFiles\\Display
```

If you keep the batch file and source files in the same directory, the only edits you will have to make to the batch file are for the region of interest row number which is done just on line 5 (or its respective command in a UNIX command line) as outlined above.

### Installing & Using Console Version

The necessary Java source files are **ErrorDisplay.java**, **IntensityReader.java**, and **Console.java**. To use them without downloading the entire .zip file associated with the repository, you could just copy-paste their contents into empty text files on your computer with the same names. If you do *not* keep the entire Eclipse project, remove the following line from the top of both files:

```
package src;
```

**Console.java** runs the program and displays outputs in the operating system's console/command line.

If you are not using Eclipse or another IDE and are on Windows, save (or copy) **console_runner.bat** to run the program for you on a set of files that you will select. If you are not using Windows, run the following commands in a UNIX command line:

```
javac ErrorDisplay.java
javac IntensityReader.java
javac Console.java
java Console
```

The input file for the console version should have first the number of rows and then the file names (following a space or return). The file names should *not* contain spaces but they must be separated by either spaces or returns. For example, if I was to run the program with a region of interest of 30 rows on "test1.jpg", "test2.jpg", and "test3.jpg" which all resided in C:\\Users\\Chami\\TestImages, then the input file would look like:

```
30
C:\\Users\\Chami\\TestImages\\test1.jpg
C:\\Users\\Chami\\TestImages\\test2.jpg
C:\\Users\\Chami\\TestImages\\test3.jpg
```

Say I named this file "inputs.txt" and it was located in the directory "C:\\Users\\Chami\\Desktop", then line 5 of **console_runner.bat** would look like:

```
java Console C:\\Users\\Chami\\inputs.txt
```

If you wish to keep the batch files and source files in separate directories, the batch file must be edited. The edits are outlined in the instructions for the GUI version above. However, if you keep the batch file and source files in the same directory, the only edits you will have to make to the batch file are for the input files which is done just on line 5 (or its respective command in a UNIX command line) as outlined above.

**Note:** I would *not* use the batch files if you plan on working in an IDE such as Eclipse. If you do, it could add additional class files to your project which may cause some complications. Since I placed the source files in their own package during development, the batch file does not even find them initially.

## Test Files

I included some JPEG files that I used in testing. More can be made using Microsft Paint!

## Java Documentation

* [BufferedImage](https://docs.oracle.com/javase/7/docs/api/java/awt/image/BufferedImage.html) - used for JPEG reading.

## Authors

* **Chami Lamelas** - [LiquidsShadow](https://github.com/LiquidsShadow) - Summer 2019

## Acknowledgments

* [PurpleBooth](https://github.com/PurpleBooth) who wrote the [template](https://gist.github.com/PurpleBooth/109311bb0361f32d87a2) for this file.
* [Eclipse](https://www.eclipse.org/downloads/) was used to develop this project. The Eclipse debugger is very useful for seeing individual pixel values!
