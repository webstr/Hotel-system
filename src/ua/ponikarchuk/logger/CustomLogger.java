package ua.ponikarchuk.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CustomLogger {
    private static CustomLogger customLogger = new CustomLogger();
    private static Logger logger;

    private CustomLogger() {

        logger = Logger.getLogger(CustomLogger.class);
        String log4JPropertyFile = "D:\\epam_2\\projects\\HotelSystem\\src\\ua\\ponikarchuk\\resources\\log4j.properties";
        Properties p = new Properties();

        try {
            p.load(new FileInputStream(log4JPropertyFile));
            PropertyConfigurator.configure(p);
            logger.info("Wow! I'm configured!");
        } catch (IOException e) {
            //DAMN! I'm not....
        }
    }

    public static CustomLogger getCustomLogger() {
        return customLogger;
    };

    public Logger getLogger() {
        return logger;
    }

}
