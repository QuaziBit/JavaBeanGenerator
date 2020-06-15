package javabeangenerator;

import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date: 11-16-2017 10:00 pm
 * @author Olexandr Matveyev
 */
public class DataParser
{
    private String data = null;

    /**
     * Default constructor
     */
    DataParser()
    {

    }

    /**
     * Initialize DataParser Object with data
     * @param data String
     */
    DataParser(String data)
    {
        this.data = data;
    }

    /**
     * Test data and get its type
     * @return
     */
    public String dataType()
    {
        String result = null;

        try
        {
            if(data.length() == 1 && CharacterParse())
            {
                result = "Character";
            }
            else if(data.equals("false") || data.equals("true") && BooleanParse())
            {
                result = "Boolean";
            }
            else if(data.matches(".*\\d+.*") && !data.contains("-") && !data.contains("/") && !data.contains("\\"))
            {
                //Numerical tests
                if(!data.contains(".") && IntegerParse())
                {
                    result = "Integer";
                }
                else if(data.contains(".") && FloatParse())
                {
                    result = "Float";
                }
                else if(data.contains(".") && DoubleParse())
                {
                    result = "Double";
                }
                /*
                else if(LongParse())
                {
                    result = "Long";
                }
                */
            }
            else if(DateParse())
            {
                result = "Date";
            }
            else if(ColorParse())
            {
                result = "Color";
            }
            else if(StringParse())
            {
                result = "String";
            }
        }
        catch (NumberFormatException e)
        {
            System.out.println("Parsing error: " + e.getMessage());
        }

        return result;
    }

    /**
     * Test is data is Character
     * @return boolean
     */
    private boolean CharacterParse()
    {
        boolean isParsable = false;
        Character tmp = null;

        try
        {
            tmp = data.charAt(0);
            isParsable = true;
        }
        catch (NumberFormatException e)
        {
            System.out.println("[CharacterParse]: Parsing error: " + e.getMessage());
        }

        return isParsable;
    }

    /**
     * Test is data is String
     * @return boolean
     */
    private boolean StringParse()
    {
        boolean isParsable = false;
        String tmp = null;

        try
        {
            tmp = new String(data);
            isParsable = true;
        }
        catch (NumberFormatException e)
        {
            System.out.println("[StringParse]: Parsing error: " + e.getMessage());
        }

        return isParsable;
    }

    /**
     * Test is data is Boolean
     * @return boolean
     */
    private boolean BooleanParse()
    {
        boolean isParsable = false;
        boolean tmp = false;

        try
        {
            tmp = Boolean.parseBoolean(data);
            isParsable = true;
        }
        catch (NumberFormatException e)
        {
            System.out.println("[BooleanParse]: Parsing error: " + e.getMessage());
        }

        return isParsable;
    }

    /**
     * Test is data is Integer
     * @return boolean
     */
    private boolean IntegerParse()
    {
        boolean isParsable = false;
        Integer tmp = null;

        try
        {
            tmp = Integer.parseInt(data);
            isParsable = true;
        }
        catch (NumberFormatException e)
        {
            System.out.println("[IntegerParse]: Parsing error: " + e.getMessage());
        }

        return isParsable;
    }

    /**
     * Test is data is Float
     * @return boolean
     */
    private boolean FloatParse()
    {
        boolean isParsable = false;
        float tmp;

        try
        {
            tmp = Float.valueOf(data);
            isParsable = true;
        }
        catch (NumberFormatException e)
        {
            System.out.println("[FloatParse]: Parsing error: " + e.getMessage());
        }

        return isParsable;
    }

    /**
     * Test is data is Double
     * @return boolean
     */
    private boolean DoubleParse()
    {
        boolean isParsable = false;
        Double tmp = null;

        try
        {
            tmp = Double.parseDouble(data);
            isParsable = true;
        }
        catch (NumberFormatException e)
        {
            System.out.println("[DoubleParse]: Parsing error: " + e.getMessage());
        }

        return isParsable;
    }

    /**
     * Test is data is Long
     * @return boolean
     */
    private boolean LongParse()
    {
        boolean isParsable = false;
        Long tmp = null;

        try
        {
            tmp = Long.parseLong(data);
            isParsable = true;
        }
        catch (NumberFormatException e)
        {
            System.out.println("[LongParse]: Parsing error: " + e.getMessage());
        }

        return isParsable;
    }

    /**
     * Test is data is Date
     * @return boolean
     */
    private boolean DateParse()
    {
        boolean isParsable = false;
        DateFormat df = new SimpleDateFormat("mm-dd-yyyy");
        Date date = null;

        try
        {
            date = df.parse(data);
            isParsable = true;
        }
        catch (ParseException e)
        {
            System.out.println("[LongParse]: Parsing error: " + e.getMessage());
        }

        return isParsable;
    }

    /**
     * Test is data is DateTime
     * @return boolean
     */
    private boolean DateTimeParse()
    {
        return false;
    }

    /**
     * Test is data is Color
     * @return boolean
     */
    private boolean ColorParse()
    {
        boolean isParsable = false;
        Color color = null;

        try
        {
            data = data.toLowerCase();
            color = (Color)Color.class.getField(data).get(null);
            isParsable = true;
        }
        catch (IllegalAccessException e)
        {
            System.out.println("[1] [LongParse]: Parsing error: " + e.getMessage());
        }
        catch (NoSuchFieldException e)
        {
            System.out.println("[2] [LongParse]: Parsing error: " + e.getMessage());
        }

        return isParsable;
    }

    /**
     * Set data
     * @param data String
     */
    public void setData(String data)
    {
        this.data = data;
    }
}
