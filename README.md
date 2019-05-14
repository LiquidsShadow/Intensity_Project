# Intensity Project

The purpose of this project was to provide a way to take a .jpg file (or set of files) and convert them into 2D integer arrays that hold intensities of the pixels in the image. The intensities are calculated as the sum of the RGB values as of the moment. However, this could be improved upon. The data can also be written to CSV files which can be then opened in Microsoft Excel and plotted to display peaks or dips in intensity. For more, see the [documentation](https://github.com/LiquidsShadow/Intensity_Project/blob/master/src/src/IntensityReader.java) of **IntensityReader.java**.

## Getting Started

There are a few initial steps to get this program running.

### Prerequisites

This project was developed using [Java 8](https://www.java.com/en/download/) and [JDK version 1.8.0_201](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html). These are needed in order to run the Java source files through the Java Virtual Machine meant for your operating system. Lastly, you have to make sure that the JDK folder has been added to the system path, especially if you are not using an IDE. [Eclipse](https://www.eclipse.org/downloads/) does this for you, but some IDEs may not. I do not know how this is done on OSX, but for windows, this [article](https://stackoverflow.com/questions/1678520/javac-not-working-in-windows-command-prompt) was quite useful.

### Installing

The necessary Java source files are **IntensityReader.java** and **Display.java**. To use them without downloading the entire .zip file associated with the repository, you could just copy-paste their contents into empty text files on your computer with the same names. If you do *not* keep the entire Eclipse project, remove the following line from the top of both files:

```
package src;
```

**Display.java** provides a dialog window for the user to select JPEG images to run through IntensityReader. To read more about this, see the [UPDATE LINK](https://github.com/LiquidsShadow/Intensity_Project/blob/master/src/src/Main.java) in **Display.java**.

If you are not using Eclipse or another IDE and are on Windows, save (or copy) **runner.bat** to run the program for you on a set of files of your selection. If you are not using Windows, run the following commands in a UNIX command line:

```
javac IntensityReader.java
javac Display.java
java Display
```

**Note:** I would *not* use the batch file if you plan on working in an IDE such as Eclipse. If you do, it could add additional class files to your project which may cause some complications. Since I placed the source files in their own package during development, the batch file does not even find them initially.

## Test Files

I included some .jpg files that I used in testing. More can be made using Microsft Paint!

## Java Documentation

* [BufferedImage](https://docs.oracle.com/javase/7/docs/api/java/awt/image/BufferedImage.html) - used for JPEG reading.

## Authors

* **Chami Lamelas** - [LiquidsShadow](https://github.com/LiquidsShadow) - Summer 2019

## Acknowledgments

* [PurpleBooth](https://github.com/PurpleBooth) who wrote the [template](https://gist.github.com/PurpleBooth/109311bb0361f32d87a2) for this file.
* [Eclipse](https://www.eclipse.org/downloads/) was used to develop this project. The Eclipse debugger is very useful for seeing individual pixel values!
