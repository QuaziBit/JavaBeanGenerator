package javabeangenerator;

/**
 * Date: 11-16-2017 10:00 pm
 * @author Olexandr Matveyev
 */
public class Start
{
    public static void main(String args[])
    {
        GenerateDataClass gdc = new GenerateDataClass("data.csv", "package u_csv_reader", "Data");
        gdc.start();
    }
}
