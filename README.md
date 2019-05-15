# Intensity Project

The purpose of this project was to provide a way to take a .jpg file (or set of files) and convert them into 2D integer arrays that hold intensities of the pixels in the image. The intensities are calculated as the sum of the RGB values as of the moment. However, this could be improved upon. A region of interest can be calculated by specifying a top percent and bottom percent in the image represented as an array. This data can then be written to CSV files which can be then opened in Microsoft Excel and plotted to display peaks or dips in intensity. For more, see the [documentation](https://github.com/LiquidsShadow/Intensity_Project/blob/master/src/src/IntensityReader.java) of **IntensityReader.java**.

## Getting Started

There are a few initial steps to get this program running.

### Prerequisites

This project was developed using [Java 8](https://www.java.com/en/download/) and [JDK version 1.8.0_201](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html). These are needed in order to run the Java source files through the Java Virtual Machine meant for your operating system. Lastly, you have to make sure that the JDK folder has been added to the system path, especially if you are not using an IDE. [Eclipse](https://www.eclipse.org/downloads/) does this for you, but some IDEs may not. I do not know how this is done on OSX, but for windows, this [UPDATE LINK](google.com) was quite useful.

### Installing & Using GUI Version (up-to-date & recommended)

The necessary Java source files are **IntensityReader.java** and **Display.java**. To use them without downloading the entire .zip file associated with the repository, you could just copy-paste their contents into empty text files on your computer with the same names. If you do *not* keep the entire Eclipse project, remove the following line from the top of both files:

```
package src;
```

**Display.java** provides a dialog window for the user to select JPEG images to run through IntensityReader. To read more about this, see the [UPDATE LINK](https://github.com/LiquidsShadow/Intensity_Project/blob/master/src/src/Main.java) in **Display.java**.

If you are not using Eclipse or another IDE and are on Windows, save (or copy) **gui_runner.bat** to run the program for you on a set of files that you will select. If you are not using Windows, run the following commands in a UNIX command line:

```
javac IntensityReader.java
javac Display.java
java Display
```

If you wish to change the percentages that bound the region of interest, add 2 floating point numbers after "Display" in line 4 (or its respective command in a UNIX command line). These will be the top *then* bottom percentages. For example, if the region is the middle 1/5 of the image, that is from 40% to 60%, line 4 (or its respective command in a UNIX command line) would look like:

```
java Display 0.4 0.6
```

If you wish to keep the batch files and source files in separate directories, the batch file must be edited. For example, if I was again running the program on the text files mentioned above, but I moved the source files to "C:\\Users\\Chami\SourceFiles\\", lines 2-4 of **gui_runner.bat** (or your commands in a UNIX command line) would look like this:

```
javac C:\\Users\\Chami\\SourceFiles\\IntensityReader.java
javac C:\\Users\\Chami\\SourceFiles\\Display.java
java C:\\Users\\Chami\\SourceFiles\\Display
```

If you keep the batch file and source files in the same directory, the only edits you will have to make to the batch file are for the percentage inputs which is done just on line 4 (or its respective command in a UNIX command line) as outlined above.

### Installing & Using Console Version (NOT up-to-date & NOT recommended)

**WARNING** selecting region of interest bounds is *not* supported!

The necessary Java source files are **IntensityReader.java** and **Main.java**. To use them without downloading the entire .zip file associated with the repository, you could just copy-paste their contents into empty text files on your computer with the same names. If you do *not* keep the entire Eclipse project, remove the following line from the top of both files:

```
package src;
```

**Main.java** runs the program and displays outputs in the operating system's console/command line.

If you are not using Eclipse or another IDE and are on Windows, save (or copy) **console_runner.bat** to run the program for you on a set of files that you will select. If you are not using Windows, run the following commands in a UNIX command line:

```
javac IntensityReader.java
javac Main.java
java Main
```

To add input files, add their full path names separated by spaces after the word "Main". For example, if I wish to run the program on test1.jpg, test2.jpg, and test3.jpg that all resided in the folder "C:\\Users\\Chami\\TestImages\\", line 4 (or its respective command in a UNIX command line) would look like:

```
java Main C:\\Users\\Chami\\TestImages\\test1.jpg C:\\Users\\Chami\\TestImages\\test2.jpg C:\\Users\\Chami\\TestImages\\test3.jpg
```

Do not put spaces in the path names, only between them. If you wish to keep the batch files and source files in separate directories, the batch file must be edited. For example, if I was again running the program on the text files mentioned above, but I moved the source files to "C:\\Users\\Chami\SourceFiles\\", lines 2-4 of **console_runner.bat** (or your commands in a UNIX command line) would look like this:

```
javac C:\\Users\\Chami\\SourceFiles\\IntensityReader.java
javac C:\\Users\\Chami\\SourceFiles\\Main.java
java C:\\Users\\Chami\\SourceFiles\\Main
```

If you keep the batch file and source files in the same directory, the only edits you will have to make to the batch file are for the input files which is done just on line 4 (or its respective command in a UNIX command line) as outlined above.

**Note:** I would *not* use the batch files if you plan on working in an IDE such as Eclipse. If you do, it could add additional class files to your project which may cause some complications. Since I placed the source files in their own package during development, the batch file does not even find them initially.

## Test Files

I included some .jpg files that I used in testing. More can be made using Microsft Paint!

## Java Documentation

* [BufferedImage](https://docs.oracle.com/javase/7/docs/api/java/awt/image/BufferedImage.html) - used for JPEG reading.

## Authors

* **Chami Lamelas** - [LiquidsShadow](https://github.com/LiquidsShadow) - Summer 2019

## Acknowledgments

* [PurpleBooth](https://github.com/PurpleBooth) who wrote the [template](https://gist.github.com/PurpleBooth/109311bb0361f32d87a2) for this file.
* [Eclipse](https://www.eclipse.org/downloads/) was used to develop this project. The Eclipse debugger is very useful for seeing individual pixel values!
