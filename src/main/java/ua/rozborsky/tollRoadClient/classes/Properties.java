package ua.rozborsky.tollRoadClient.classes;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by roman on 28.01.2017.
 */
public class Properties {
    private static java.util.Properties propertie;
    private static final Logger log = Logger.getLogger(Properties.class);

    static {
        String resourceName = "myconf.properties";
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        propertie = new java.util.Properties();
        try(InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
            propertie.load(resourceStream);
        }catch (IOException e){
            log.error(e);
        }
    }

    public static String port() {
        return propertie.getProperty("port");
    }

    public static String host() {
        return propertie.getProperty("host");
    }

    public static int r() {
        return Integer.valueOf(propertie.getProperty("r"));
    }

    public static int g() {
        return Integer.valueOf(propertie.getProperty("g"));
    }

    public static int b() {
        return Integer.valueOf(propertie.getProperty("b"));
    }

    public static int delay() {
        return Integer.valueOf(propertie.getProperty("delay"));
    }
    public static int checkPoint() {
        return Integer.valueOf(propertie.getProperty("checkPoint"));
    }

    public static String pathToImage() {
        return propertie.getProperty("pathToImage");
    }

    public static String systemError() {
        return propertie.getProperty("systemEror");
    }

    public static String name() {
        return propertie.getProperty("name");
    }

    public static String terminalMarker() {
        return propertie.getProperty("terminalMarker");
    }
}
