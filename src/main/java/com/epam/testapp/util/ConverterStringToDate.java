
package com.epam.testapp.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.beanutils.Converter;

/**
 * ConverterStringToDate is used to converting string to date.
 * @author Dima
 */
public class ConverterStringToDate implements Converter{
    
    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ConverterStringToDate.class);
    
    @Override
    public Date convert(Class type, Object o) {
        String dateStr = (String) o;
        Date date = null;
        
        try {
            DateFormat dateFormat  = new SimpleDateFormat(DataUtil.DATE_FORMAT);
            date = dateFormat.parse(dateStr);
        } catch (ParseException ex) {
            logger.error("Date is not converted", ex);
        }
        return date;
    }   
}