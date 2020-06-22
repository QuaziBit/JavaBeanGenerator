package quazibit.javabeangenerator;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Date: 11-16-2017 10:00 pm
 * @author Olexandr Matveyev
 */
public class GenerateDataClass
{
    private final String TYPE = "@";
    private final String CLASS_NAME = "-";
    private final String FIELD_NAME = "%";
    private final String METHOD_NAME = "#";
    private final String FIELDS = "private @ % = null;\n";
    private final String SET_METHOD = "public void set#(@ %){this.% = %;}\n";
    private final String GET_METHOD = "public @ get#(){return %;}\n";
    private final String CLASS_BODY = "%;\n\npublic class -\n{\n\t#\n\t@\n}";

    private String inputCSVFile = null;

    private String className = null;
    private String csvFilePath = null;
    
    // delimiter is the field separator symbol in a data file,
    // for example in CSV it is ","
    private String delimiter = null;
    private String tagValue = null;

    // package-name is for the generate JavaBeen class
    private String packageName = null;
    private String importStatements = null;
    private String fields = null;
    private String methods = null;

    //List of data types and its value to generate JavaBean class
    ArrayList<JavaBean> javaBeans = new ArrayList<JavaBean>();

    private String logOutput = "";
    
    private UniversalCSVReader csv = null;
    
    /**
     * inputCSVFile should be in the same folder as this java class
     * @param inputCSVFile String
     * @param packageName String
     * @param className String
     */
    GenerateDataClass(String inputCSVFile, String packageName, String className)
    {
        this.inputCSVFile = inputCSVFile;
        this.packageName = packageName;
        this.className = className;
    }

    /**
     * inputCSVFile should be in the same folder as this java class
     * @param inputCSVFile String
     * @param packageName String
     * @param className String
     * @param delimiter String
     */
    GenerateDataClass(String inputCSVFile, String packageName, String className, String delimiter)
    {
        this.inputCSVFile = inputCSVFile;
        this.packageName = packageName;
        this.className = className;
        this.delimiter = delimiter;
    }

    /**
     * inputCSVFile should be in the same folder as this java class
     * @param inputCSVFile String
     * @param packageName String
     * @param className String
     * @param delimiter String
     * @param tagValue String
     */
    GenerateDataClass(String inputCSVFile, String packageName, String className, String delimiter, String tagValue)
    {
        this.inputCSVFile = inputCSVFile;
        this.packageName = packageName;
        this.className = className;
        this.delimiter = delimiter;
        this.tagValue = tagValue;
    }

    public void start()
    {
        //=====================================================================//
        //@ type
        //% field name
        //# method name

        String fields = "private @ % = null;\n";

        String setMethods = "public void set#(@ %){this.% = %;}\n";
        String getMethods = "public @ get#(){return %;}\n";

        String tmpField = "";
        String tmpGetMethod = "";
        String tmpSetMethod = "";
        //=====================================================================//

        csv = new UniversalCSVReader(inputCSVFile);
        csv.readFile();

        int numOfFields = csv.getNumOfFields();

        //Loop via all JavaBeans
        int counter = 0;
        for(JavaBean jb : csv.getJavaBeans())
        {

            if(counter < numOfFields)
            {
                //Generate fields
                tmpField += fields.replaceAll("@", jb.getDataType()).replaceAll("%", jb.getFieldName());

                //Generate get
                tmpGetMethod += getMethods.replaceAll("@", jb.getDataType()).replaceAll("#", jb.getFieldName()).replaceAll("%", jb.getFieldName());

                //Generate set
                tmpSetMethod += setMethods.replaceAll("@", jb.getDataType()).replaceAll("#", jb.getFieldName()).replaceAll("%", jb.getFieldName());
            }
            else
            {
                break;
            }

            counter++;
        }
        
        this.fields = tmpField + "\n";
        this.methods = tmpSetMethod + "\n" + tmpGetMethod + "\n";

        writeFile();
    }

    public void generateFields()
    {

    }

    public void generateSetMethods()
    {

    }

    public void generateGetMethods()
    {

    }

    private void writeFile()
    {
        String classBody = "%;\n" +
                            "\n" +
                            "public class -\n" +
                            "{\n" +
                            "\t#\n" +
                            "\t@\n" +
                            "}";



        String newClassBody_1 = classBody.replaceAll("%", packageName);
        String newClassBody_2 = newClassBody_1.replaceAll("-", className);

        String newClassBody_3 = newClassBody_2.replaceAll("@", methods);
        String newClassBody_4 = newClassBody_3.replaceAll("#", fields);

        try
        {
        	logOutput = newClassBody_4;
        	
        	String out = className + ".java";
        	
            Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(out), "utf-8"));
            writer.write(newClassBody_4);
            writer.close();
        }
        catch (Exception e)
        {
            System.out.println("Cannot write file: " + e.getMessage());
            
            logOutput = "Cannot write file: " + e.getMessage();
        }
        finally
        {
        	// Give some information to a user about successful generation.
        	System.out.println("The JavaBeen class was seccesfully generated.");
        	System.out.println("It was saved into the current dericroty.");
        	
        	logOutput += "The JavaBeen class was seccesfully generated.\n";
        	logOutput += "It was saved into the current dericroty.\n";
        }
    }

    public UniversalCSVReader getUniversalCSVReader()
    {
    	return csv;
    }
    
    public void setImportStatements(String importStatements)
    {
        this.importStatements = importStatements;
    }
    
    public String getLogOutput()
    {
    	return logOutput;
    }
}
