package utils;


import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetProperties {
    private static final Logger LOGGER = Logger.getLogger(GetProperties.class);
    private Properties prop = new Properties();

    public GetProperties(){
        InputStream in = getClass().getResourceAsStream("/config.properties");
        try {
            prop.load(in);
        } catch (FileNotFoundException e) {
            LOGGER.error("The property file was not found");
        } catch (IOException e) {
            LOGGER.error("Can not read property file");
        }
    }

    public String getString(String propertyName) {
        return prop.getProperty(propertyName);
    }
}
