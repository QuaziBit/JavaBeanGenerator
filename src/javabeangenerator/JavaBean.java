package javabeangenerator;

/**
 * Date: 11-16-2017 10:00 pm
 * @author Olexandr Matveyev
 */
public class JavaBean
{
    private int id = 0;
    private String dataType = null;
    private String fieldName = null;
    private String fieldValue = null;

    JavaBean(String dataType, String fieldName, String fieldValue)
    {
        this.dataType = dataType;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getDataType()
    {
        return dataType;
    }

    public String getFieldName()
    {
        return fieldName;
    }

    public String getFieldValue()
    {
        return fieldValue;
    }
}
