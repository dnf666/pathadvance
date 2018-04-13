package com.path.util;

import java.io.*;
import java.util.Properties;

/**
 * @author demo
 */
public class PropertiesUtil {
    public static Properties properties = new Properties();

    public static Properties propertitesUtil(String projectPath,String propertiesName) {
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(new File(projectPath+File.separator+propertiesName)));
            properties.load(new InputStreamReader(in,"utf-8"));
            return properties;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}
