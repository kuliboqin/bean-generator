package com.github.kuliboqin;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertiesUtil {

    public static void initProperty(File propFile) {
        Properties prop = new Properties();
        try {
            prop.load(new FileReader(propFile));
            for (Object o : prop.keySet()) {
//                Reflections.setFieldValue(constant, o.toString(), prop.getProperty(o.toString()));
                System.out.println("key:" + o.toString()  + "   value:" + prop.getProperty(o.toString()));
                Constant.templateMap.put(o.toString(), prop.getProperty(o.toString()));
            }
            Constant.processMap();
//            System.out.println(constant.getClassName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
