# JavaBeen Generator

## Software Project Idea
Generation of a JavaBeen class from the CSV file with the ability to determine a type of each field in a CSV file based on the provided initial value for each field

## Achieved goals
* you can run program in the terminal with the option to specify data file path as argument

* primitive data types are supported:
    * Boolean
    * Short ::: is not supported yet
    * Integer
    * Float
    * Double
    * Long ::: is not supported yet
    * Character

* non-primitive data types are supported:
    * String
    * Date
    * Color
    * User-defined ::: is not supported yet
    * Array ::: is not supported yet
    * Parameterized types ::: are not supported yet
    * Generic type ::: are not supported yet

* methods generation:
    * static method ::: is not supported yet
    * private methods ::: are not supported yet
    * Generic methods ::: are not supported yet
    * public Set method
    * public Get method

* generation of a class:
    * public constructor ::: is not supported yet
    * private constructor ::: is not supported yet
    
You can find example of CSV file in the "rsc" folder.

## How to run it
 * Download data.csv file that is in /rsc/csv/data.csv
 * Download JavaBeenGenerator.jar that is located in the jar folder
 * Terminal: java -jar JavaBeenGenerator.jar console "relative file path to the .jar file"
 * Output should be in the directory where you did run this application with the name Data.java
 * Or you can run it as GUI application
 * Here is demo: [link](https://youtu.be/LeiefK2WePw)

## Continue working on this project