
package com.epam.testapp.listener;

import com.epam.testapp.utils.ConverterStringToDate;
import java.util.Date;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

/**
 *  ServletContextListener is used to connect converter.
 * 
 */
public class ControllerServletContextListener implements ServletContextListener{
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        
        Converter converterStringToDate = new ConverterStringToDate();
        ConvertUtils.register(converterStringToDate, Date.class);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
    
}
