package utils;

import org.apache.log4j.Logger;

public class ReadArguments {
    private static final Logger LOGGER = Logger.getLogger(ReadArguments.class);

    public static String applyDefaultIfMissing(String argument, String defaultValue) {
        if (argument == null) {
            argument = defaultValue;
            LOGGER.info("Default " + defaultValue + " execution was applied since was not provided");
        }

        return argument;
    }

}
