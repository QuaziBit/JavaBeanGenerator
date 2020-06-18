package javabeangenerator;

import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Date: 11-16-2017 10:00 pm
 * @author Olexandr Matveyev
 */
public class Start
{
    public static void main(String[] args)
    {   
    	GenerateDataClass gdc = null;
    	
        String fileName = null;
        String path = null;
        String filePath = null;

        // Get absolute path of the running Driver.class
        Path currentDir = Paths.get(".");
        path = currentDir.toAbsolutePath().toString();

        // Initialize URL
        URL res = null;

        if(args[0] != null)
        {
            // Combine "absolute path of the running Driver.class" and "file name"
            fileName = args[0];
            filePath = path + "/" + fileName;

            try
            {
                // Get resource from the file path
                res = Paths.get(filePath).toUri().toURL();
                
                // run JavaBeen generator
                gdc = new GenerateDataClass(res.getPath(), "package u_csv_reader", "Data");
                gdc.start();

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            System.out.printf("You did not provide complete file name!\n");
        }
    }
    
}

