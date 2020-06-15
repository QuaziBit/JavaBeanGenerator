package javabeangenerator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Date: 11-16-2017 10:00 pm
 * @author Olexandr Matveyev
 */
public class UniversalCSVReader
{
    //Storing fileName
    private String fileName = null;
    private String absoluteFilePath = null;

    //delimiter
    private char delimiter = ',';

    //List of data type and its value to generate JavaBean class
    ArrayList<JavaBean> javaBeans = new ArrayList<JavaBean>();

    private int numOfFields = 0;

    /**
     * Accepts absolute file path and custom delimiter
     * @param absoluteFilePath
     * @param delimiter
     */
    UniversalCSVReader(String absoluteFilePath, char delimiter)
    {
        this.absoluteFilePath = absoluteFilePath;
        this.delimiter = delimiter;
    }

    /**
     * Delimiter by default is ","
     * @param fileName
     */
    UniversalCSVReader(String fileName)
    {
        this.fileName = fileName;
    }

    //Reading file records
    public void readFile()
    {
        DataParser parser = new DataParser();

        //counter used to ignore first line
        int counter = 0;
        String[] fieldNames = null;

        //==============================================================//
        String tempFileName = "rsc\\csv\\" + fileName;
        File file = new File(tempFileName).getAbsoluteFile();
        //System.out.println("FILE PATH: " + file.toString());

        //==============================================================//
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        //==============================================================//

        try
        {
            //==============================================================//
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            //==============================================================//

            //*************************************************************//
            String line = null;
            while ((line = bufferedReader.readLine()) != null)
            {
                //Split input line
                String[] data = line.split(",");

                //count number of fields
                numOfFields = data.length;

                //Get fields names
                if(counter == 0)
                {
                    //Size is: (n + 1)
                    fieldNames = new String[numOfFields + 1];

                    for(int i = 0; i < data.length; i++)
                    {
                        fieldNames[i] = data[i];
                    }
                }

                if(data.length > 1 && counter > 0)
                {
                    for(int i = 0; i < data.length; i++)
                    {
                        //Set data in parser object
                        parser.setData(data[i]);


                        //Remove spaces from Field Names
                        String tmp_fieldName = null;
                        if(fieldNames[i] != null)
                        {
                            if(fieldNames[i].contains(" "))
                            {
                                tmp_fieldName = fieldNames[i].replaceAll("\\s+","");
                            }
                            else
                            {
                                tmp_fieldName = fieldNames[i];
                            }
                        }
                        //System.out.println("TEST: " + tmp_fieldName);


                        JavaBean jb = new JavaBean(parser.dataType(), tmp_fieldName, data[i]);
                        //System.out.println("index i: " + i + " --- Value: " + fieldNames[i]);
                        javaBeans.add(jb);

                    }
                    System.out.println("----------------------");
                }

                counter++;

            }

            //Close bufferedReader
            bufferedReader.close();
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public int getNumOfFields()
    {
        return numOfFields;
    }

    public ArrayList<JavaBean> getJavaBeans()
    {
        return javaBeans;
    }
}
