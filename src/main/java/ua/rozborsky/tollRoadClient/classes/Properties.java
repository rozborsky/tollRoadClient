package ua.rozborsky.tollRoadClient.classes;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by roman on 28.01.2017.
 */
public class Properties {
    private static java.util.Properties propertie;

    static {
        String resourceName = "myconf.properties";
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        propertie = new java.util.Properties();
        try(InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
            propertie.load(resourceStream);
        }catch (IOException e){
            //todo log4j----------------
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

    public static String pathToImage() {
        return propertie.getProperty("pathToImage");
    }

    public static String systemError() {
        return propertie.getProperty("systemEror");
    }

    public static String ok() {
        return propertie.getProperty("ok");
    }

    public static String error() {
        return propertie.getProperty("error");
    }

    public static String notValid() {
        return propertie.getProperty("notValid");
    }

    public static String name() {
        return propertie.getProperty("name");
    }
}
