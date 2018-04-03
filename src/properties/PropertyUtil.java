/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package properties;

import engmonapp.EngMonApp;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Mohammed
 */
public class PropertyUtil {

    static Hashtable<String, String> engMonProp = new Hashtable<>();

    //
    private static void loadProperties() {
        String userPath = java.lang.System.getProperty("user.dir");
        File dir = new File(userPath);
        String projectPath = dir.getName();
        String projectFolder = new File(userPath).getName();
        String projectPropertyFile = userPath + File.separator + projectFolder + ".properties";
        java.util.Properties prop = new java.util.Properties();
        try {
            prop.load(new FileInputStream(projectPropertyFile));

            for (String key : prop.stringPropertyNames()) {

                String value = prop.getProperty(key);
               // System.out.println(key + " => " + value);
                engMonProp.put(key, value);
            }
        } catch (Exception ex) {
            Logger.getLogger(EngMonApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static String readSingleProperties(String key){
        loadProperties();
        String value=engMonProp.get(key);
        return value;
    }
}